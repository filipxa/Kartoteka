import { Component, OnInit } from '@angular/core';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit {
  


  cinemas : Lokal[];
  
  constructor(private localService : LokalService) { }

  getCinemas() : void{
    this.localService.getCinemas().subscribe(cinemas => this.initCinemas(cinemas));
  }

  initCinemas(cinemas : Lokal[]) {
   this.cinemas = cinemas;
  }

  ngOnInit() {
    this.getCinemas();
  }

}
