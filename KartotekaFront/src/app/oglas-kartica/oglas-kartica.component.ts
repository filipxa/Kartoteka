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
  offerOglas(){
    if(this.PriceFormControl.value != 0 && this.PriceFormControl.value !="undefined"){
      this.ponuda.oglasID = this.oglas.idOglasa;
      this.ponuda.cena = this.PriceFormControl.value;
      this.ponudaService.save(this.ponuda);
    }
     
    //KADA SE NAPRAVI BEKEND ZA PONUDU PA SERVIS ZA PONUDU ONDA TU DODATI
  }
  PriceFormControl = new FormControl('', [
    Validators.required
  ]);

  ngOnInit() {
    this.imagePath = "http://localhost:8080/api/downloadFile/oglasi/"+this.oglas.idOglasa;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
  }

}
