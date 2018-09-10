import { Component, OnInit } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { RekvizitService } from '../services/rekvizit.service';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';

@Component({
  selector: 'app-rekvizit-lista-admin',
  templateUrl: './rekvizit-lista-admin.component.html',
  styleUrls: ['./rekvizit-lista-admin.component.css']
})
export class RekvizitListaAdminComponent implements OnInit {

  rekviziti: Rekvizit[];
  oglasi: Oglas[];

  constructor(private rekvizitService: RekvizitService, private oglasSrevice:OglasService) {}

  getRekvizit(): void{
    this.rekvizitService.getRekvizti().subscribe(rekviziti => this.initRekvizit(rekviziti));
  }

  initRekvizit(rekviziti: Rekvizit[]){
    this.rekviziti = rekviziti;
  }

  getOglas(): void{
    this.oglasSrevice.getOglasi().subscribe(o => this.initOglasi(o));
  }

  initOglasi(oglasi: Oglas[]){
    this.oglasi = oglasi;
  }

  ngOnInit() {
    this.getRekvizit();
    this.getOglas();
  }

}
