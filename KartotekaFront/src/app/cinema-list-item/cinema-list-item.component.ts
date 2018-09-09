import { Component, OnInit, Input } from '@angular/core';
import { Lokal } from '../models/lokal';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-cinema-list-item',
  templateUrl: './cinema-list-item.component.html',
  styleUrls: ['./cinema-list-item.component.css']
})
export class CinemaListItemComponent implements OnInit {

  @Input() cinema : Lokal;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onClick(){
    this.router.navigate(["lokal/profil/" + this.cinema.id])
  }

}
