import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserService } from '../services/user.service'
import { User } from '../models/user'

@Component({
  selector: 'my-nav',
  templateUrl: './my-nav.component.html',
  styleUrls: ['./my-nav.component.css']
})
export class MyNavComponent {
    ngOnInit() {
           this.userService.getLoggedUserAPI();
    }
  constructor(private breakpointObserver: BreakpointObserver, private userService : UserService) {}

  logOut(){
    this.userService.logOut();
  }
  
  }
