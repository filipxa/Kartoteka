import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router'



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const registerUrl: string = "http://localhost:8080/api/user/register";
const getByEmailUrl: string = "http://localhost:8080/api/user/";
const getFriendsUrl: string = "http://localhost:8080/api/user/friends/";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  public loggedUser: User;
  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router) { }

  public getLoggedUser(): User {
    if (!this.loggedUser) {
      if (sessionStorage.getItem("loggedUser") !== null) {
        this.loggedUser=JSON.parse(sessionStorage.getItem("loggedUser"));
      }
      return this.loggedUser;
    } else {
      return this.loggedUser;
    }
  }

  private setLoggedUser(): void {
    sessionStorage.setItem("loggedUser", JSON.stringify(this.loggedUser));
  }

  logIn(email: string, password: string): void {

    this.http.get<User>(getByEmailUrl + email, httpOptions).subscribe(result => {

      if (result != undefined) {
        this.loggedUser = result;
        this.setLoggedUser();
        this.router.navigate(["/"]);
      } else {
        this.snackBar.open("Wrong email or password !", "", {
          duration: 3000,
        });
      }
    });
  }

  public getFriends(): Observable<Array<User>> {
    console.log(this.loggedUser);
    return this.http.get<Array<User>>(getFriendsUrl + this.loggedUser.email, httpOptions);
  }


  logOut(): void {
    this.loggedUser = null;
    sessionStorage.removeItem("loggedUser");
    this.router.navigate(["/"]);

  }

  register(user: User) {

    this.http.post<string>(registerUrl, user, httpOptions).subscribe(result => {
      if (result == "succes") {
        this.snackBar.open("Register successfully! Chek your email form activation link.", "", {
          duration: 5000,
        });
        this.router.navigate(["/"]);

      } else if (result == "email") {
        this.snackBar.open("User with " + user.email.trim() + " already exists!", "", {
          duration: 3000,
        });
      }
    });

  }




}
