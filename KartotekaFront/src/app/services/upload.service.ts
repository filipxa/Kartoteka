import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Rekvizit } from '../models/rekvizit';
import { MatSnackBar } from '@angular/material';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'multipart/form-data' })
};

@Injectable({
  providedIn: 'root'
})


export class UploadService {

  constructor(private http:HttpClient, private snackBar: MatSnackBar) { }

  private rekvizitUploadURL: string = "http://localhost:8080/api/uploadImage/rekvizit";


  rekvizitUploadImg(file: File, id:number){
    let sendData = new FormData();
    sendData.append('file',file);
    sendData.append('idd',"1233");
    this.http.post(this.rekvizitUploadURL +"/"+ id, sendData
    ).subscribe(data => console.log(data));
    console.log("Prosao rekvizitUploadImg");
  }
}
