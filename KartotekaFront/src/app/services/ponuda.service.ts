import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { UserService } from './user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Ponuda } from '../models/ponuda';
import { catchError } from 'rxjs/operators';
import { Observable, of, pipe } from 'rxjs';
import { from } from 'rxjs';
import { map } from 'rxjs/operators';


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
    return this.http.post(ponudeURL,ponuda,httpOptions).pipe(
      map(
      result => {
        this.snackBar.open("Offer sent!", "", {
          duration: 3000,
        });
    },
      error =>{
        this.snackBar.open("Error!!", "", {
          duration: 3000,
        });
      }));
  }

  getOglasForUser(idOglasa:Number):Observable<Ponuda>{
    return this.http.get<Ponuda>(ponudeURL + "/"+ idOglasa,httpOptions).pipe(
      catchError(this.handleError<Ponuda>()));
  }

  deletePonuda(idOglasa:Number){
    console.log("Delete: "+idOglasa);
    return this.http.delete<Ponuda>(ponudeURL + "/" + idOglasa, httpOptions).subscribe(param => {
      this.snackBar.open("Item deleted!", "", {
        duration: 2000,
      });
    });
  }


  delPonuda(idOglasa:Number){
    console.log("Delete: "+idOglasa);
    return this.http.delete<Ponuda>(ponudeURL + "/" + idOglasa, httpOptions).pipe(
      map(response =>{
        this.snackBar.open("Item deleted!", "", {
          duration: 2000,
        });
      }));
  }

  acceptPonuda(idPonude:Number){
    this.http.get(ponudeURL + "/accept/"+ idPonude,httpOptions).subscribe(param => {
      this.snackBar.open("Offer accepted!", "", {
        duration: 2000,
      });
    });
  }

  getPonudeOglasa(idOglasa:Number):Observable<Ponuda[]>{
    return this.http.get<Ponuda[]>(ponudeURL + "/oglas/"+ idOglasa,httpOptions).pipe(
      catchError(this.handleError<Ponuda[]>()));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      return of(result as T);
    };
  }
}
