import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-naslov-profile',
  templateUrl: './naslov-profile.component.html',
  styleUrls: ['./naslov-profile.component.css']
})
export class NaslovProfileComponent implements OnInit {
  ID: number;
  film: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.ID = parseInt(this.route.snapshot.paramMap.get('id'));
  }

}
