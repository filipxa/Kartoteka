import { Injectable } from '@angular/core';
import { Karta } from '../models/karta';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router'
import { UserService } from './user.service';
import { User } from '../models/user';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const getByUserId : string = "http://localhost:8080/api/karta/user/";
const cancelById : string = "http://localhost:8080/api/karta/cancel/";
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
   let loggedUser = this.userService.getLoggedUser();
   
    return this.http.get<Array<Karta>>(getByUserId + this.userService.loggedUser.email, httpOptions);
  }

}
