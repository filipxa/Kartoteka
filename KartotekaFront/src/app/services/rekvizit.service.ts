import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Rekvizit } from '../models/rekvizit';
import { Observable } from 'rxjs/internal/Observable';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RekvizitService {
  
  constructor(private http: HttpClient) { }
  private allRekvizitiUrl : string = "http://localhost:8080/api/rekvizti";

  getRekvizti(): Observable<Rekvizit[]>{

    return this.http.get<Rekvizit[]>(this.allRekvizitiUrl).pipe(
      catchError(this.handleError<Rekvizit[]>()));
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
