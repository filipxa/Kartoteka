import { Component, OnInit, Input } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { UserService } from '../services/user.service';
import { RekvizitService } from '../services/rekvizit.service';

@Component({
  selector: 'app-rekvizit-kartica',
  templateUrl: './rekvizit-kartica.component.html',
  styleUrls: ['./rekvizit-kartica.component.css']
})
export class RekvizitKarticaComponent implements OnInit {
  @Input() rekvizit: Rekvizit;
  constructor(private rekvizitService: RekvizitService, private userSrvice: UserService) { }
  imagePath: String;
  altPhoto: String;
  bookRekvizit(){
    this.rekvizit.rezervisano = true;
    this.rekvizitService.bookRekvizit(this.rekvizit);
  }
  ngOnInit() {
    this.imagePath = "http://localhost:8080/api/downloadFile/rekviziti/"+this.rekvizit.idRekvizita;
    this.altPhoto = "https://www.freeiconspng.com/uploads/no-image-icon-4.png";
    //this.imagePath = "1";
  }

}
