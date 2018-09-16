import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { LokalService } from '../services/lokal.service';
import { Lokal } from '../models/lokal';
import { Repertoar } from '../models/repertoar';
import { Izvedba } from '../models/izvedba';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Naslov } from '../models/naslov';
import { MatStepper, MAT_CHIPS_DEFAULT_OPTIONS, MatStep, MatCheckbox, MatCheckboxChange, MatSelectChange, MatSelect, MatSnackBar } from '@angular/material';
import { StepperSelectionEvent, CdkStep } from '@angular/cdk/stepper';
import { Sediste } from '../models/sediste';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { TicketService } from '../services/ticket.service';



@Component({
  selector: 'app-lokal-reserve',
  templateUrl: './lokal-reserve.component.html',
  styleUrls: ['./lokal-reserve.component.css']
})
export class LokalReserveComponent implements OnInit {

  @ViewChild('stepper') stepper;


  @Input() lokal: Lokal;

  naslovi: Naslov[];
  naslovIzvedbeMap: Map<Naslov, Izvedba[]> = new Map<Naslov, Izvedba[]>();
  izvedbe: Array<Izvedba> = new Array<Izvedba>();
  izvedbaTime: Array<Izvedba> = new Array<Izvedba>();
  formGroups: Array<FormGroup> = new Array<FormGroup>();
  seats: Array<Array<Sediste>> = new Array<Array<Sediste>>();
  seatsForReservation: Array<Sediste> = new Array<Sediste>();
  
  izvedba: Izvedba;

  friends: Array<User> = new Array<User>();

  friendsRes: Array<User> = new Array<User>();

  stepsTaken: Map<number, CdkStep> = new Map<number, CdkStep>();

  titleFormGroup: FormGroup;
  dateFormGroup: FormGroup;
  timeFormGroup: FormGroup;
  seatsFormGroup: FormGroup;
  friendsFormGroup: FormGroup;
  currentStep: MatStep;


  constructor(private route: ActivatedRoute,
    private lokalService: LokalService,
    private location: Location,
    private _formBuilder: FormBuilder,
    private userService: UserService,
    private ticketService: TicketService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }


  initLokal(lokal: Lokal): any {

    this.lokal = lokal;
    this.naslovIzvedbeMap = Repertoar.extractNasloviIzvedbeFilterDate(lokal.repertoar);
    this.naslovi = Array.from(this.naslovIzvedbeMap.keys());

  }

  ngOnInit() {
    this.userService.redirectIfNotLogged("");


    this.titleFormGroup = this._formBuilder.group({
      titleCtrl: ['', Validators.required]
    });
    this.dateFormGroup = this._formBuilder.group({
      dateCtrl: ['', Validators.required]
    });
    this.timeFormGroup = this._formBuilder.group({
      timeCtrl: ['', Validators.required]
    });
    this.friendsFormGroup = this._formBuilder.group({
      friendsCtrl: ['']
    });
    this.getLokal();
  }

  getDates() {
    let naslov = this.titleFormGroup.get("titleCtrl").value;
    this.izvedbe = this.naslovIzvedbeMap.get(naslov);
    this.izvedbe.filter(i => i.datum == i.datum);
  }


  getTimes() {
    let izvedba = this.dateFormGroup.get("dateCtrl").value;
    this.izvedbaTime = this.izvedbe.filter(i => i.datum == izvedba.datum);

  }

  getSeats(): void {
    this.izvedba = this.timeFormGroup.get("timeCtrl").value;
    this.ticketService.getIzvedbaTickets(this.izvedba.idIzvedba).subscribe(data=>{
      this.seats=Sediste.getSeats(this.izvedba,data);
    });
    this.seatsForReservation = new Array<Sediste>();

  }

  getLokal(): void {

    const id = +this.route.snapshot.paramMap.get('id');
    this.lokalService.getLokal(id)
      .subscribe(lokal => {
        this.initLokal(lokal);
      });
  }

  getFriends(): void {
    this.userService.getFriends().subscribe(f => this.friends = f)
  }




  seatClick(event: MatCheckboxChange) {
    if (event.checked) {
      //@ts-ignore
      this.seatsForReservation.push(event.source.value);


    } else {
      //@ts-ignore
      var index = this.seatsForReservation.indexOf(event.source.value);
      if (index > -1) {
        this.seatsForReservation.splice(index, 1);
      }

    }
    this.stepsTaken.get(3).completed = this.seatsForReservation.length > 0;
  }

  stepChanged(event: StepperSelectionEvent): void {
    let currentStep = event.selectedIndex;
    this.stepsTaken.set(currentStep, event.selectedStep);
    if (event.selectedIndex < event.previouslySelectedIndex) {
      for (let idx of Array.from(this.stepsTaken.keys())) {
        if (currentStep < idx) {
          this.stepsTaken.get(idx).reset();
        }
      }
    }
    if (currentStep == 1) {
      this.getDates();
    } else if (currentStep == 2) {
      this.getTimes();
    } else if (currentStep == 3) {
      this.getSeats();
      this.stepsTaken.get(3).completed = false;
    } else if (currentStep == 4) {
      this.getFriends();
    }


  }

  onRezClick(){
    this.ticketService.reserve(this.seatsForReservation, this.friendsRes);

  }

  friendsChange(event: MatSelectChange) {
    let fCtrl: MatSelect = event.source;
    if (event.source.value.length >= this.seatsForReservation.length) {
      this.snackBar.open("You can not invite more friends then seat reservations!", "", {
        duration: 5000,
      });
      var index = event.source.value.pop();
      fCtrl.writeValue(this.friendsRes);
    } else {
      this.friendsRes = fCtrl.value;
    }
  }

}
