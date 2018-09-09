import { Component, OnInit, Input } from '@angular/core';
import { LokalService } from '../services/lokal.service';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { Lokal } from '../models/lokal';

@Component({
  selector: 'app-lokal-profile',
  templateUrl: './lokal-profile.component.html',
  styleUrls: ['./lokal-profile.component.css']
})
export class LokalProfileComponent implements OnInit {

  constructor(
    private lokalService: LokalService,
    private route: ActivatedRoute
  ) { }

  lokal: Lokal;
  
  ngOnInit() {
    this.getLokal();
  }


  getLokal(): void {

    const id = +this.route.snapshot.paramMap.get('id');
    this.lokalService.getLokal(id)
      .subscribe(lokal => this.initLokal(lokal));
  }

  initLokal(lokal: Lokal) {

    this.lokal = lokal;
  }



 








}
