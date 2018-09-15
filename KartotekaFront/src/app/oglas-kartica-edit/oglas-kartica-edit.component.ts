import { Component, OnInit, Input } from '@angular/core';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { Router } from '@angular/router';
import { Ponuda } from '../models/ponuda';
import { PonudaService } from '../services/ponuda.service';

@Component({
  selector: 'app-oglas-kartica-edit',
  templateUrl: './oglas-kartica-edit.component.html',
  styleUrls: ['./oglas-kartica-edit.component.css']
})
export class OglasKarticaEditComponent implements OnInit {
  @Input() oglas: Oglas;
  constructor(private oglasService:OglasService, 
    private router: Router,
    private ponudaService: PonudaService) { 
      this.prikazPonuda = false;
      this.prikazIspis = "See offers";
    }

  ponude: Ponuda[];
  imagePath: String;
  altPhoto: String;
  prikazPonuda:boolean;
  
  prikazIspis:String;

  offersOglas(){
    if(this.prikazPonuda == true){
      this.prikazPonuda = false;
      this.prikazIspis = "See offers";
    }
    else{
      this.prikazPonuda = true;
      this.prikazIspis = "Hide offers";
      this.ponudaService.getPonudeOglasa(this.oglas.idOglasa).subscribe(
        x=>{this.initPonude(x)}
      );
    }
    
  }

  editOglas(){
    console.log("editOglas: "+this.oglas);
    this.router.navigate(['/ogalsEdit',this.oglas.idOglasa]);
    //this.oglas.odobren = true;
    //this.oglasService.acceptOglas(this.oglas.idOglasa);
    //this.router.navigate(['/rekvizitEdit',this.oglas]);
  }

  deleteOglas(){
    this.oglas.prodat = true;
    console.log(this.oglas);
    this.oglasService.deleteOglas(this.oglas.idOglasa);
    //this.oglasService.deleteRekvizit(this.oglas);
  }
  initPonude(ponude: Ponuda[]){
    this.ponude = ponude;
  }
  vreme:string;
  ngOnInit() {
    let datee = new Date();
    datee.setTime(this.oglas.datum);
    this.vreme = datee.toLocaleString();
    this.imagePath = "http://localhost:8080/api/downloadFile/oglasi/"+this.oglas.idOglasa;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
    console.log("sta");
  }


}

