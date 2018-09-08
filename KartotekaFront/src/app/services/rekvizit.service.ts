import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Rekvizit } from '../models/rekvizit';
import { Observable } from 'rxjs/internal/Observable';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true
};

@Injectable({
  providedIn: 'root'
})
export class RekvizitService {

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router:Router) { }
  private allRekvizitiUrl: string = "http://localhost:8080/api/rekviziti";

  getRekvizti(): Observable<Rekvizit[]> {

    return this.http.get<Rekvizit[]>(this.allRekvizitiUrl).pipe(
      catchError(this.handleError<Rekvizit[]>()));
  }

  bookRekvizit(rekvizit: Rekvizit) {
    this.http.post<Rekvizit>(this.allRekvizitiUrl + "/book", rekvizit, httpOptions).subscribe(param => {
      this.snackBar.open("Item reserved!", "", {
        duration: 2000,
      });
    });
  }

  addRekvizit(rekvizit: Rekvizit, file: File) {
    let sendData = new FormData();
    sendData.append('file', file);
    sendData.append('rekvizit',new Blob([JSON.stringify(rekvizit)], {type : 'application/json'}) );
    console.log(sendData);
    this.http.post(this.allRekvizitiUrl, sendData
    ).subscribe(
      data => { 
        console.log(data);
        this.snackBar.open("Item added!", "", {
          duration: 2000,
        }); 
        this.router.navigate(["/rekvizitAllEdit"]);
      },
      error => {
        this.snackBar.open("ERROR!", "", {
          duration: 2000,
        });
      }
    );
  }
  editRekvizit(rekvizit: Rekvizit, file: File) {
    let sendData = new FormData();
    sendData.append('file', file);
    sendData.append('rekvizit',new Blob([JSON.stringify(rekvizit)], {type : 'application/json'}) );
    console.log(sendData);
    this.http.post<Rekvizit>(this.allRekvizitiUrl, sendData).subscribe(param => {
      this.snackBar.open("Item changed!", "", {
        duration: 2000,
      });
    });
  }

  deleteRekvizit(rekvizit: Rekvizit) {
    var idS = String(rekvizit.idRekvizita);
    this.http.delete<Rekvizit>(this.allRekvizitiUrl + "/" + idS, httpOptions).subscribe(param => {
      this.snackBar.open("Item deleted!", "", {
        duration: 2000,
      });
    });
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
