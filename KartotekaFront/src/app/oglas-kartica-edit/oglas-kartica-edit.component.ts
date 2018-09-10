import { Component, OnInit, Input } from '@angular/core';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-oglas-kartica-edit',
  templateUrl: './oglas-kartica-edit.component.html',
  styleUrls: ['./oglas-kartica-edit.component.css']
})
export class OglasKarticaEditComponent implements OnInit {
  @Input() oglas: Oglas;
  constructor(private oglasService:OglasService, private router: Router) { }

  imagePath: String;
  altPhoto: String;
  
  offersOglas(){
    console.log("offersOglas: "+this.oglas);
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

  ngOnInit() {
    this.imagePath = "http://localhost:8080/api/downloadFile/oglasi/"+this.oglas.idOglasa;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
    console.log("sta");
  }


}

