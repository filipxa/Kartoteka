import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-home-page-visitor',
  templateUrl: './home-page-visitor.component.html',
  styleUrls: ['./home-page-visitor.component.css']
})
export class HomePageVisitorComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  forward(tip)
  {
    this.router.navigate(['/lokal/'+tip]);
  }

}
