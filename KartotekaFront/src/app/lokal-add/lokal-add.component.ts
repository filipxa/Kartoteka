import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { Lokal } from '../models/lokal';
import { Repertoar } from '../models/repertoar';
import { Sala } from '../models/sala';
import { LokalService } from '../services/lokal.service';

@Component({
  selector: 'app-lokal-add',
  templateUrl: './lokal-add.component.html',
  styleUrls: ['./lokal-add.component.css']
})
export class LokalAddComponent implements OnInit {

  constructor(private router: Router,
    private snackBar : MatSnackBar,
    private lokalService:LokalService) { }

  file: File;//ovaj file treba napuniti sa slikom pogledati u add-rekvizit kako radi
  nameFormControl = new FormControl('', [
    Validators.required
  ]);
  descriptionFormControl = new FormControl('', [
    Validators.required
  ]);
  addressFormControl = new FormControl('', [
    Validators.required
  ]);
  lokalFormKontrol= new FormControl('', [
    Validators.required
  ]);
  private onSave(){
    let lokal: Lokal ={
      id : -1,
      isPozoriste : this.lokalFormKontrol.value,
      repertoar : new Repertoar(),
      naziv : this.nameFormControl.value,
      adresa : this.addressFormControl.value,
      promotivniOpis : this.descriptionFormControl.value,
      sala : new Array<Sala>()
    }
    console.log(lokal);
    this.lokalService.save(lokal,this.file);
  }
  ngOnInit() {
  }

}
