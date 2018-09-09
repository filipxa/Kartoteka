import { Component, OnInit } from '@angular/core';
import { UploadService } from '../services/upload.service';
import { RekvizitService } from '../services/rekvizit.service';
import { UserService } from '../services/user.service';
import { MatSnackBar, MatDatepickerInputEvent } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Rekvizit } from '../models/rekvizit';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';

@Component({
  selector: 'app-oglas-add',
  templateUrl: './oglas-add.component.html',
  styleUrls: ['./oglas-add.component.css']
})
export class OglasAddComponent implements OnInit {

  constructor(private uploadService: UploadService,
    private oglasService: OglasService,
    private userSrvice: UserService,
    private snackBar : MatSnackBar,
    private http:HttpClient,
    private fb: FormBuilder) { 
      this.startDate = new Date();
      this.milisecDate = this.startDate.getTime();
    }

  startDate: any;
  file: File;
  form: FormGroup;

  ngOnInit() {
  }
  today = new Date().toJSON().split('T')[0];
  valuechangeIMG(event) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        this.file = fileList[0];
        console.log(this.file);
    }
    console.log("cao");
  }
  events: string[] = [];
  milisecDate: number;

  createForm() {
    this.form = this.fb.group({
      file_upload: null
    });
  }

  NameFormControl = new FormControl('', [
    Validators.required
  ]);

  DescriptionFormControl = new FormControl('', [
  ]);

  datum:Date;

  set humanDate(e){
    e = e.split('-');
    
    let d = new Date(Date.UTC(e[0], e[1]-1, e[2]));
    this.startDate.setFullYear(d.getUTCFullYear(), d.getUTCMonth(), d.getUTCDate());
    this.milisecDate = d.getTime();
  }

  get humanDate(){
    return this.startDate.toISOString().substring(0, 10);
  }


  private addOglas(): void {
    console.log(this.milisecDate);
    let error : boolean = false;
    error = 
       this.NameFormControl.hasError('required');

    if (error)
    {
      this.snackBar.open("You must enter Name!","", {
        duration: 3000,
      });
    }else{
      let oglas : Oglas = {
        idOglasa: -1,
        naziv: this.NameFormControl.value,
        opis: this.DescriptionFormControl.value,
        datum: this.milisecDate,
        prodat: false,
        odobren:false,
        postavioID: this.userSrvice.getUserLogged().id
      };
      this.oglasService.addOglas(oglas,this.file);
    }

    
  }

}
