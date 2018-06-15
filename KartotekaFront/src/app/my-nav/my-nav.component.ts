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
  loggedUser : User;
  
  
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );
    
    getLoggedUser() : void {
      this.userService.geLoggedUser()
      .subscribe(user => this.loggedUser = user);
    }


    ngOnInit() {
       this.getLoggedUser();
    }

  constructor(private breakpointObserver: BreakpointObserver, private userService : UserService) {}
  
  }
