import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { UserService } from './user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Ponuda } from '../models/ponuda';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true
};

const ponudeURL : string = "http://localhost:8080/api/ponude";

@Injectable({
  providedIn: 'root'
})
export class PonudaService {

  private loggedUser : User;

  constructor(private userService: UserService, 
    private http: HttpClient, 
    private snackBar : MatSnackBar, 
    private router: Router ) { }

  save(ponuda : Ponuda){
    this.http.post(ponudeURL,ponuda,httpOptions).subscribe(
      result => {
        this.snackBar.open("Offer sent!", "", {
          duration: 3000,
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
