import { Component, OnInit } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { RekvizitService } from '../services/rekvizit.service';
import {RekvizitKarticaComponent} from '../rekvizit-kartica/rekvizit-kartica.component';

@Component({
  selector: 'app-fanzone',
  templateUrl: './fanzone.component.html',
  styleUrls: ['./fanzone.component.css']
})
export class FanzoneComponent implements OnInit {

  rekviziti: Rekvizit[];


  constructor(private rekvizitService: RekvizitService) {}

  getRekvizit(): void{
    this.rekvizitService.getRekvizti().subscribe(rekviziti => this.initRekvizit(rekviziti));
  }

  initRekvizit(rekviziti: Rekvizit[]){
    this.rekviziti = rekviziti;
  }
  

  ngOnInit() {
  }

}
