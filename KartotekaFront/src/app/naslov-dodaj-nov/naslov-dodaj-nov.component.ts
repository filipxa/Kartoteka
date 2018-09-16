import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '../../../node_modules/@angular/material';
import { LokalService } from '../services/lokal.service';
import { Naslov } from '../models/naslov';
import { Osoba } from '../models/osoba';

@Component({
  selector: 'app-naslov-dodaj-nov',
  templateUrl: './naslov-dodaj-nov.component.html',
  styleUrls: ['./naslov-dodaj-nov.component.css']
})
export class NaslovDodajNovComponent implements OnInit {

  // podaci o naslovu
  naziv : string;
  zanr : string;
  opis: string;

  naslov: Naslov;
  sviReziseri : Osoba[];

  slika: File;

  constructor(public dialogRef: MatDialogRef<NaslovDodajNovComponent>, @Inject(MAT_DIALOG_DATA) public data: string) {
    
    this.naslov= new Naslov();
    console.log(this.naslov);
    this.pokupiSveRezisere();
    
  }

  valuechangeIMG(event) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        this.slika = fileList[0];
        console.log(this.slika);
    }
    console.log("cao");
  }

  

  ngOnInit() {
  }

  dodajGlumca(){
    
  }

  dodajRezisera(){

  }

  pokupiSveRezisere(){
    this.sviReziseri = [];
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
 

}
