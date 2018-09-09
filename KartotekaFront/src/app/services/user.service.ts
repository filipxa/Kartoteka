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

  private static loggedUser: User;
  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router) { }

  public getLoggedUser(): Observable<User> {
    if (!UserService.loggedUser) {
      return this.http.get<User>(loginURL, httpOptions).pipe(
        this.saveLoggedUser);
    } else {
      return of( UserService.loggedUser);
    }
  }

  public getUserLogged(): User{
    return UserService.loggedUser;
  }

  

  private saveLoggedUser(data :Observable<User>){
    data.subscribe(result => {
       UserService.loggedUser = result;
    });
    return data;
  }

  public redirectIfNotLogged (adress : String){
    this.getLoggedUser().subscribe(data=>{
     if(data==null){
      this.router.navigate(["/"+adress])
      } 
    });
  }


  public resetLoggedUser(user:User){
    //this.loggedUser = user;
    
  }

  logIn(email: string, password: string): void {

    let sendData = {};
    sendData["username"] = email;
    sendData["password"]= password;

    this.http.post<User>(loginURL, sendData, httpOptions).subscribe(result => {
      if (result != undefined) {
        UserService.loggedUser = result;
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
    console.log(UserService.loggedUser);
    return this.http.get<Array<User>>(getFriendsUrl + UserService.loggedUser.email, httpOptions);
  }


  logOut(): void {
    UserService.loggedUser = null;
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
