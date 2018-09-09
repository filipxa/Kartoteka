import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { RekvizitService } from '../services/rekvizit.service';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Rekvizit } from '../models/rekvizit';
import { MatSnackBar } from '@angular/material';
import { UploadService } from '../services/upload.service';
import { HttpClient } from  '@angular/common/http';

import {Observable} from "rxjs";
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/internal/operators/map';
@Component({
  selector: 'app-add-rekvizit',
  templateUrl: './add-rekvizit.component.html',
  styleUrls: ['./add-rekvizit.component.css']
})
export class AddRekvizitComponent implements OnInit {
  constructor(private uploadService: UploadService,
    private rekvizitService: RekvizitService,
    private userSrvice: UserService,
    private snackBar : MatSnackBar,
    private http:HttpClient,
    private fb: FormBuilder) { }

  file: File;
  form: FormGroup;

  ngOnInit() {
  }

  valuechangeIMG(event) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        this.file = fileList[0];
        console.log(this.file);
    }
    console.log("cao");
  }

  createForm() {
    this.form = this.fb.group({
      file_upload: null
    });
  }

  NameFormControl = new FormControl('', [
    Validators.required
  ]);
  PriceFormControl = new FormControl('', [
    Validators.required
  ]);
  DescriptionFormControl = new FormControl('', [
  ]);

  private addRekvizit(): void {
    let error : boolean = false;
    error = 
       this.NameFormControl.hasError('required') 
    || this.PriceFormControl.hasError('required');

    if (error)
    {
      this.snackBar.open("You must enter Name and Price!","", {
        duration: 3000,
      });
    }else{
      let rekvizit : Rekvizit = {
        idRekvizita: -1,
        naziv: this.NameFormControl.value,
        cena: this.PriceFormControl.value,
        opis: this.DescriptionFormControl.value,
        rezervisano: false
        
      };
      this.rekvizitService.addRekvizit(rekvizit,this.file);
    }

    
  }

}
