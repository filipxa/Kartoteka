import { Component, OnInit, Input } from '@angular/core';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { Ponuda } from '../models/ponuda';
import { PonudaService } from '../services/ponuda.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-oglas-kartica',
  templateUrl: './oglas-kartica.component.html',
  styleUrls: ['./oglas-kartica.component.css']
})
export class OglasKarticaComponent implements OnInit {
  @Input() oglas: Oglas;
  constructor(private oglasService:OglasService, 
    private router: Router,
    private snackBar : MatSnackBar,
    private ponudaService: PonudaService) { this.ponuda = new Ponuda(); }

  imagePath: String;
  altPhoto: String;
  ponuda:Ponuda;
  staraPonuda:Ponuda;
  offerOglas(){
    if(this.PriceFormControl.value > 0 && this.PriceFormControl.value !="undefined"){
      if(this.staraPonuda != undefined && this.staraPonuda.id>-1){
        this.ponuda = this.staraPonuda;
      }
      else{
        this.ponuda.id = -1;
      }
      this.PriceFormControl.updateOn;
      this.ponuda.oglasID = this.oglas.idOglasa;
      this.ponuda.cena = this.PriceFormControl.value;
      this.ponudaService.save(this.ponuda).subscribe(x=>{
        this.getPonda();
      });
    }
  }

  cancelOfferOglas(){
    // dodati ovabestenje za korisnika
    //prebaciti nesto u folse da se sakrije sa fronta oma
    //testirati
    console.log(this.oglas.idOglasa);
    this.PriceFormControl.updateOn;
    this.ponudaService.delPonuda(this.oglas.idOglasa).subscribe(x=>{
      this.getPonda();
    });
     
    
  }
  PriceFormControl = new FormControl('', [
    Validators.required
  ]);
  vreme:String;
  getPonda(): void{
    this.ponudaService.getOglasForUser(this.oglas.idOglasa).subscribe(
      ponuda => this.initPonuda(ponuda));
  }

  initPonuda(ponuda: Ponuda){
    this.staraPonuda = ponuda;
    console.log(this.staraPonuda);
  }
  nDate:Date;
  nMil:Number;
  ngOnInit() {
    this.nDate = new Date();
    this.nMil = this.nDate.getTime().valueOf();
    let datee = new Date();
    console.log("new Date():"+datee.toLocaleString());
    datee.setTime(this.oglas.datum);
    this.vreme = datee.toLocaleString();
    this.imagePath = "http://localhost:8080/api/downloadFile/oglasi/"+this.oglas.idOglasa;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
    this.getPonda();
  }

}
