import { Component, OnInit, Input } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { ActivatedRoute } from '@angular/router';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { RekvizitService } from '../services/rekvizit.service';

@Component({
  selector: 'app-rekvizit-edit-admin',
  templateUrl: './rekvizit-edit-admin.component.html',
  styleUrls: ['./rekvizit-edit-admin.component.css']
})
export class RekvizitEditAdminComponent implements OnInit {
  @Input() rekvizit: Rekvizit;
  constructor(private rekvizitService: RekvizitService,private route: ActivatedRoute,private snackBar : MatSnackBar) { }

  idRekvizita: number;
  naziv: string;
  opis: string;
  cena: number;
  rezervisano : boolean;
  re: Rekvizit;
  private sub: any;

  TestFormControl= new FormControl('caoss');

  NameFormControl = new FormControl('', [
    Validators.minLength(1),Validators.nullValidator
  ]);
  PriceFormControl = new FormControl('', [
    Validators.minLength(1)
  ]);
  DescriptionFormControl = new FormControl('', [
  ]);

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.idRekvizita = +params['idRekvizita']; 
      this.naziv = params['naziv'];
      this.opis = params['opis'];
      this.cena = +params['cena'];
      this.rezervisano = params['rezervisano'];
      if(params['rezervisano']==='false'){
        this.rezervisano = false;
      }else{ this.rezervisano = true}
      //console.log(this.r.idRekvizita);
      
   });


   let rr : Rekvizit = {
      idRekvizita :this.idRekvizita, 
      naziv:this.naziv,
      opis:this.opis,
      cena:this.cena,
      rezervisano:this.rezervisano
    };
    console.log(this.rezervisano);
    this.rekvizit = rr;
  }
  valuechangeNaziv(newValue) {

    this.naziv = newValue;
    console.log(newValue);
  }

  valuechangeCena(newValue) {
    this.cena = newValue;
    console.log(newValue);
  }
  private addRekvizit(): void {
    let error : boolean = false;
    error = 
       this.NameFormControl.hasError('minLength') 
    || this.naziv == ""
    || this.cena == null
    || this.cena == 0
    || this.NameFormControl.hasError('nullValidator')
    || this.PriceFormControl.hasError('required');

    if (error)
    {
      this.snackBar.open("You must enter Name and Price!","", {
        duration: 3000,
      });
    }else{
      console.log(this.naziv);
      let rekvizit : Rekvizit = {
        idRekvizita: this.idRekvizita,
        naziv: this.NameFormControl.value,
        cena: this.PriceFormControl.value,
        opis: this.DescriptionFormControl.value,
        rezervisano: false
      };
      var input = document.getElementById('testic');
      console.log();
      if(this.NameFormControl.value === ""){
        rekvizit.naziv =this.naziv;
      }
      if(this.PriceFormControl.value === ""){
        rekvizit.cena =this.cena;
      }
      if(this.DescriptionFormControl.value === ""){
        rekvizit.opis =this.opis;
      }
      console.log(rekvizit);
      this.rekvizitService.editRekvizit(rekvizit);
    }
  }

}
