import { Component, OnInit } from '@angular/core';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';
import { RouterModule, Routes, Router, ActivatedRoute } from '@angular/router'
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-lokal-list',
  templateUrl: './lokal-list.component.html',
  styleUrls: ['./lokal-list.component.css']
})
export class LokalListComponent implements OnInit {

  type: string = "";

  locals: Lokal[];

  constructor(
    private localService: LokalService, 
    private router: Router,
    private route: ActivatedRoute,
    private userService : UserService  
  ) {

    this.type = this.route.snapshot.paramMap.get('type');

    if (this.route.snapshot.paramMap.get('type') === 'cinemas') {
     
      
      this.getCinemas();
    }
    else {
      this.getTheatres();
    }
  }

  getCinemas(): void {
    this.locals = []
    this.localService.getCinemas().subscribe(locals => {
      
      this.locals = locals;
    });
  }

  getTheatres(): void {
    this.locals = []
    this.localService.getTheatres().subscribe(locals => {
      this.locals = locals;
    });
  }
 

  ngOnInit() {
    this.userService.getLoggedUserAPI();

  }

  profilBioskopa(id) {
    this.router.navigate(['lokal/profil/' + id]);
  }

}
