import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Lokal } from '../models/lokal';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Sala } from '../models/sala';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class LokalService {


  constructor(  private http: HttpClient,private snackBar: MatSnackBar,private router:Router) { }
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

  public save(lokal:Lokal,file:File){
    let sendData = new FormData();
    sendData.append('file', file);
    sendData.append('lokal',new Blob([JSON.stringify(lokal)], {type : 'application/json'}) );
    console.log(sendData);
    this.http.post("http://localhost:8080/api/lokal/", sendData).subscribe(
      data => { 
        //console.log(data);
        this.snackBar.open("Item added!", "", {
          duration: 2000,
        }); 
        this.router.navigate(["/"]);
      },
      error => {
        this.snackBar.open("ERROR!", "", {
          duration: 2000,
        });
      }
    );
  }
  updateLokal(lokal: Lokal)
  {
    return this.http.post<any>("http://localhost:8080/api/lokal/update", lokal, httpOptions);
  }

  updateSala(sala : Sala)
  {
    console.log("updateSala");
    
    return this.http.post("http://localhost:8080/api/sala/update", sala, httpOptions).subscribe(x=>{
      console.log(x);
    });

  }

}
