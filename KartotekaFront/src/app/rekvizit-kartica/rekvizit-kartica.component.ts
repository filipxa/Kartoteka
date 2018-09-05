import { Component, OnInit, Input } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { UserService } from '../services/user.service';
@Component({
  selector: 'app-rekvizit-kartica',
  templateUrl: './rekvizit-kartica.component.html',
  styleUrls: ['./rekvizit-kartica.component.css']
})
export class RekvizitKarticaComponent implements OnInit {
  @Input() rekvizit: Rekvizit;
  constructor() { }

  ngOnInit() {
  }

}
