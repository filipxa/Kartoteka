import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Oglas } from '../models/oglas';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true
};
@Injectable({
  providedIn: 'root'
})
export class OglasService {

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router:Router) { }
  private allOglasiUrl: string = "http://localhost:8080/api/oglasi";

  addOglas(oglas: Oglas, file: File) {
    let sendData = new FormData();
    sendData.append('file', file);
    sendData.append('oglas',new Blob([JSON.stringify(oglas)], {type : 'application/json'}) );
    console.log(sendData);
    this.http.post(this.allOglasiUrl, sendData
    ).subscribe(
      data => { 
        console.log(data);
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
  
  getOglasi(): Observable<Oglas[]> {
    return this.http.get<Oglas[]>(this.allOglasiUrl).pipe(
      catchError(this.handleError<Oglas[]>()));
  }

  acceptOglas(id){
    this.http.get(this.allOglasiUrl+"/accept/"+id).subscribe(param => {
      this.snackBar.open("Item accepted!", "", {
        duration: 2000,
      });
    });
  }


  deleteOglas(id){
    this.http.delete(this.allOglasiUrl + "/" + id).subscribe(param => {
      this.snackBar.open("Item deleted!", "", {
        duration: 2000,
      });
    });
  }
  getOglas(id):Observable<Oglas>{
    return this.http.get<Oglas>(this.allOglasiUrl+"/"+id).pipe(
      catchError(this.handleError<Oglas>()));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      return of(result as T);
    };
  }
}
