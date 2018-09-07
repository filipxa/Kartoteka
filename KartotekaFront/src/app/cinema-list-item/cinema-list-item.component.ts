import { Component, OnInit, Input } from '@angular/core';
import { Lokal } from '../models/lokal';

@Component({
  selector: 'app-cinema-list-item',
  templateUrl: './cinema-list-item.component.html',
  styleUrls: ['./cinema-list-item.component.css']
})
export class CinemaListItemComponent implements OnInit {

  @Input() cinema : Lokal;

  constructor() { }

  ngOnInit() {
  }

}
