import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router'



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true
};


const registerUrl: string = "http://localhost:8080/api/user/register";
const getByEmailUrl: string = "http://localhost:8080/api/user/";
const loginURL: string = "http://localhost:8080/api/user/logged";
const getFriendsUrl: string = "http://localhost:8080/api/user/friends/";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  public loggedUser: User;
  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router) { }

  public getLoggedUser(): Observable<User> {
    if (!this.loggedUser) {
      this.http.get<User>(loginURL, httpOptions).subscribe(result => {
        this.loggedUser = result;
      });
     
      return of(this.loggedUser);
    } else {
      return of(this.loggedUser);
    }
  }


  public resetLoggedUser(user:User){
    this.loggedUser = user;
  }

  logIn(email: string, password: string): void {

    let sendData = {};
    sendData["username"] = email;
    sendData["password"]= password;

    this.http.post<User>(loginURL, sendData, httpOptions).subscribe(result => {
      if (result != undefined) {
        this.loggedUser = result;
        this.router.navigate(["/"]);
      } else {
        this.snackBar.open("Wrong email or password !", "", {
          duration: 3000,
        });
      }
    }, error => {
      this.snackBar.open("Bad login!", "", {
        duration: 3000,
      });

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
