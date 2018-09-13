import { Component, OnInit } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { RekvizitService } from '../services/rekvizit.service';
import {RekvizitKarticaComponent} from '../rekvizit-kartica/rekvizit-kartica.component';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-fanzone',
  templateUrl: './fanzone.component.html',
  styleUrls: ['./fanzone.component.css']
})
export class FanzoneComponent implements OnInit {

  rekviziti: Rekvizit[];
  oglasi: Oglas[];
  mojiOglasi:Oglas[];

  constructor(private rekvizitService: RekvizitService, private oglasSrevice:OglasService, private userSrvice : UserService) {
    this.mojiOglasi = new Array();
  }

  public getRekvizit(): void{
    this.rekvizitService.getRekvizti().subscribe(rekviziti => this.initRekvizit(rekviziti));
  }

  initRekvizit(rekviziti: Rekvizit[]){
    this.rekviziti = rekviziti;
  }

  public getOglas(): void{
    this.oglasSrevice.getOglasi().subscribe(o => this.initOglasi(o));
  }

  initOglasi(oglasi: Oglas[]){
    this.oglasi = oglasi;
    
    oglasi.forEach(o => {
      // if(o.postavioID==this.userSrvice.getUserLogged().id){
        this.mojiOglasi.push(o);
      // }
    });
    console.log(this.mojiOglasi);
  }

  public ngOnInit() {
    this.getRekvizit();
    this.getOglas();
  }

}
