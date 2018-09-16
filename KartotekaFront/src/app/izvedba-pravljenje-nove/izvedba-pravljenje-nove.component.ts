import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '../../../node_modules/@angular/material';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';
import { Izvedba } from '../models/izvedba';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { UserService } from '../services/user.service';
import { Naslov } from '../models/naslov';
import { Osoba } from '../models/osoba';
import { NaslovDodajNovComponent } from '../naslov-dodaj-nov/naslov-dodaj-nov.component';

@Component({
  selector: 'app-izvedba-pravljenje-nove',
  templateUrl: './izvedba-pravljenje-nove.component.html',
  styleUrls: ['./izvedba-pravljenje-nove.component.css']
})
export class IzvedbaPravljenjeNoveComponent implements OnInit {

  idLokala: number;
  lokal: Lokal;
  novaIzvedba: Izvedba;
  naslovi : Naslov[];
  sviReziseri : Osoba[];

  constructor(
    private route: ActivatedRoute,
    private localService: LokalService,
    public dialog: MatDialog, 
    private userService : UserService
  ) {
    //this.userService.redirectIfNotLogged("");
    this.idLokala = parseInt(this.route.snapshot.paramMap.get('idLokala'));
    localService.getLokal(this.idLokala).subscribe(data => {
      this.lokal = data;
      console.log(this.lokal.sala);
      this.pokupiSveNasloveLokala();
      console.log(this.naslovi);
      
      this.novaIzvedba = new Izvedba();
      this.novaIzvedba.isFilm = !this.lokal.isPozoriste;

    });
  }

  addIzvedba(){
    
  }


  dodajNaslov(){

    const dialogRef = this.dialog.open(NaslovDodajNovComponent, {
      width: '400px',
      data: this.lokal.id + "_" 
        });

    dialogRef.afterClosed().subscribe(result => {
      //this.novaIzvedba.glumci.push(result);
    });
  }

  pokupiSveNasloveLokala(){
    this.naslovi = [];
    this.lokal.repertoar.izvedbe.forEach((izvedba: Izvedba ) =>{
      if(!this.naslovi.includes(izvedba.naslov)){
        this.naslovi.push(izvedba.naslov);
      }
    });
  }

  postaviNaslov(naslov){
    this.novaIzvedba.naslov = naslov;

    
  }

  postaviSalu(sala){
    this.novaIzvedba.sala = sala;    
  }

 

  ngOnInit() {
  }


  submitChange() {

    // // treba promeniti da salje -1, jer je nov, pa da napravi u backendu
    // this.localService.updateLokal(this.lokal).subscribe(data => {
    //   console.log(data);
    // });

  }

}
