import { Component, OnInit } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { RekvizitService } from '../services/rekvizit.service';

@Component({
  selector: 'app-rekvizit-lista-admin',
  templateUrl: './rekvizit-lista-admin.component.html',
  styleUrls: ['./rekvizit-lista-admin.component.css']
})
export class RekvizitListaAdminComponent implements OnInit {

  rekviziti: Rekvizit[];


  constructor(private rekvizitService: RekvizitService) {}

  getRekvizit(): void{
    this.rekvizitService.getRekvizti().subscribe(rekviziti => this.initRekvizit(rekviziti));
  }

  initRekvizit(rekviziti: Rekvizit[]){
    this.rekviziti = rekviziti;
  }
  

  ngOnInit() {
    this.getRekvizit();
  }

}
