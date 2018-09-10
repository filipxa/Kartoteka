import { Injectable } from '@angular/core';
import { Karta } from '../models/karta';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router'
import { UserService } from './user.service';
import { User } from '../models/user';
import { Sediste } from '../models/sediste';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true
};


const getLoggedUserTicketURL : string = "http://localhost:8080/api/karta/user/";
const cancelById : string = "http://localhost:8080/api/karta/cancel/";
const reserveTicketUrl : string = "http://localhost:8080/api/karta/rez";
@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private loggedUser : User;

  constructor(private userService: UserService, private http: HttpClient, private snackBar : MatSnackBar, private router: Router ) {
    
   }

  cancelTicket(ticket : Karta)  {
   ticket.tip="slobodno";
  this.http.get(cancelById + ticket.idKarte, httpOptions).subscribe(param=> 
    {
      this.snackBar.open("Ticket canceled !","", {
        duration: 2000,
      });
  });
  }

  getLoggedUserTickets() : Observable<Array<Karta>>{
   let loggedUser = this.userService.getLoggedIn();
   
    return this.http.get<Array<Karta>>(getLoggedUserTicketURL , httpOptions);
  }

  reserve(sedista : Array<Sediste>, prijatelji : Array<User>) {
    let sendData = {};
    sendData["seats"] = sedista;
    sendData["friends"] = prijatelji;
    this.http.post(reserveTicketUrl, sendData, httpOptions).subscribe(
      result => {
        this.snackBar.open("Successfull! Reservation email will be sent to your adress.", "", {
          duration: 5000,
        });
        this.router.navigate(["/"]);
    },
      error =>{
        this.snackBar.open("Error!!", "", {
          duration: 3000,
        });
      });
  }


}
