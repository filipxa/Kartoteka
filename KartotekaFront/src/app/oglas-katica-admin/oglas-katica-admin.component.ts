import { Component, OnInit, Input } from '@angular/core';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-oglas-katica-admin',
  templateUrl: './oglas-katica-admin.component.html',
  styleUrls: ['./oglas-katica-admin.component.css']
})
export class OglasKaticaAdminComponent implements OnInit {
  @Input() oglas: Oglas;
  constructor(private oglasService:OglasService, private router: Router) { }

  imagePath: String;
  altPhoto: String;
  
  acceptOglas(id){
    console.log(this.oglas);
    this.oglas.odobren = true;
    this.oglasService.acceptOglas(this.oglas.idOglasa);
    //this.router.navigate(['/rekvizitEdit',this.oglas]);
  }

  deleteOglas(){
    this.oglas.prodat = true;
    console.log(this.oglas);
    this.oglasService.deleteOglas(this.oglas.idOglasa);
    //this.oglasService.deleteRekvizit(this.oglas);
  }
  vreme:string;
  ngOnInit() {    
    let datee = new Date();
    datee.setTime(this.oglas.datum);
    this.vreme = datee.toLocaleString();
    this.imagePath = "http://localhost:8080/api/downloadFile/oglasi/"+this.oglas.idOglasa;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
  }


}
