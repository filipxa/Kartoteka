import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { LokalService } from '../services/lokal.service';
import { Sala } from '../models/sala';
import { Lokal } from '../models/lokal';

@Component({
  selector: 'app-sala-edit',
  templateUrl: './sala-edit.component.html',
  styleUrls: ['./sala-edit.component.css']
})
export class SalaEditComponent implements OnInit {

  idLokala: number;
  lokal: Lokal;
  idSale: number;
  sala: Sala;



  constructor(public dialogRef: MatDialogRef<SalaEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string, private localService: LokalService) {
    let prima: string = data;
    let podaci: string[] = [];
    console.log(prima);
    
    podaci = prima.split("_");
    this.idLokala = Number(podaci[0]);
    localService.getLokal(this.idLokala).subscribe(data => {
      this.lokal = data;
      console.log(this.lokal);
      this.idSale = Number(podaci[1]);
      this.vratiSalu(this.idSale);
    });
   
    
  }

  private vratiSalu(idSale){
    let sale : Sala[] = this.lokal.sala;
   
    sale.forEach(element => {
      
      if(element.idSale == idSale){
        this.sala = element;
      }
    });
  }

  ngOnInit() {
  }

}
