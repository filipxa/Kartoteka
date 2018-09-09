import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Lokal } from '../models/lokal';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class LokalService {


  constructor(  private http: HttpClient) { }
  private lokaliSerachUrl : string = "http://localhost:8080/api/lokal/search/";
  private lokalByIdUrls : string = "http://localhost:8080/api/lokal/byId/";
  private allCinemasUrl : string = "http://localhost:8080/api/lokal/cinemas";
  private allTheatresUrl: string = "http://localhost:8080/api/lokal/theatres";

  searchLokali(term: string): Observable<Lokal[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Lokal[]>(this.lokaliSerachUrl + term, httpOptions);
  }

  getLokal(id: number): Observable<Lokal> {
    return this.http.get<Lokal>(this.lokalByIdUrls + id).pipe(
      catchError(this.handleError<Lokal>(`getLokal id=${id}`))
    );
  }
  

  getCinemas(): Observable<Lokal[]> {
    return this.http.get<Lokal[]>(this.allCinemasUrl).pipe(
      catchError(this.handleError<Lokal[]>('getCinemas'))
    );
  }

  getTheatres(): Observable<Lokal[]> {
    return this.http.get<Lokal[]>(this.allTheatresUrl).pipe(
      catchError(this.handleError<Lokal[]>('getTheatres'))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  updateLokal(lokal: Lokal)
  {
    return this.http.post<any>("http://localhost:8080/api/lokal/update", lokal, httpOptions);
  }

}
