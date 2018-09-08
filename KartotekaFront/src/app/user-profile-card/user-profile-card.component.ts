import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-profile-card',
  templateUrl: './user-profile-card.component.html',
  styleUrls: ['./user-profile-card.component.css']
})
export class UserProfileCardComponent implements OnInit {
  loggedUser : User;
  constructor(private userService : UserService) { }

  ngOnInit() {
    this.userService.getLoggedUser().subscribe(user=> this.loggedUser=user);
  }

}
