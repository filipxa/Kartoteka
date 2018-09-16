import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Skala } from '../models/skala';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true
};
@Injectable({
  providedIn: 'root'
})
export class SkalaService {

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router) { }
  private skalaUrl : string = "http://localhost:8080/api/skala";

  get():Observable<Skala>{
    return this.http.get<Skala>(this.skalaUrl,httpOptions).pipe(
      catchError(this.handleError<Skala>()));
  }

  save(skala: Skala){
    this.http.post(this.skalaUrl,skala,httpOptions).subscribe(
      result => {
        this.snackBar.open("Saved!", "", {
          duration: 2000,
        });
        this.router.navigate(["/"]);
    },
      error =>{
        this.snackBar.open("Error!!", "", {
          duration: 3000,
        });
      });
  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      return of(result as T);
    };
  }
}
