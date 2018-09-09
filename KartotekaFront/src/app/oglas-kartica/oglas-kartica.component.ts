import { Component, OnInit, Input } from '@angular/core';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-oglas-kartica',
  templateUrl: './oglas-kartica.component.html',
  styleUrls: ['./oglas-kartica.component.css']
})
export class OglasKarticaComponent implements OnInit {
  @Input() oglas: Oglas;
  constructor(private oglasService:OglasService, private router: Router) { }

  imagePath: String;
  altPhoto: String;
  
  offerOglas(id){
    console.log("FALI SERVIS ZA OFFER"+this.oglas);
    //KADA SE NAPRAVI BEKEND ZA PONUDU PA SERVIS ZA PONUDU ONDA TU DODATI
  }


  ngOnInit() {
    this.imagePath = "http://localhost:8080/api/downloadFile/oglasi/"+this.oglas.idOglasa;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
  }

}
