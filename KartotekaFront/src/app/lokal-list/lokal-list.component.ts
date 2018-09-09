import { Component, OnInit } from '@angular/core';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';
import { RouterModule, Routes, Router, ActivatedRoute } from '@angular/router'

@Component({
  selector: 'app-lokal-list',
  templateUrl: './lokal-list.component.html',
  styleUrls: ['./lokal-list.component.css']
})
export class LokalListComponent implements OnInit {

  type: string = "";

  locals: Lokal[] = [];

  constructor(private localService: LokalService, private router: Router, private route: ActivatedRoute) {

    this.type = this.route.snapshot.paramMap.get('type');

    if (this.route.snapshot.paramMap.get('type') === 'cinemas') {
      this.getCinemas();
    }
    else {
      this.getTheatres();
    }
  }

  getCinemas(): void {
    this.localService.getCinemas().subscribe(locals => {
      console.log(locals);
      
      this.locals = locals;
    });
  }

  getTheatres(): void {
    this.localService.getTheatres().subscribe(locals => {
      this.locals = locals;
    });
  }
  /* 
    initCinemas(cinemas : Lokal[]) {
     this.cinemas = cinemas;
    } */

  ngOnInit() {
    this.getCinemas();

  }

  profilBioskopa(id) {
    this.router.navigate(['lokal/profil/' + id]);
  }

}
