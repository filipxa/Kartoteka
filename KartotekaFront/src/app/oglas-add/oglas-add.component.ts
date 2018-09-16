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
      this.startDate = this.dateSeter(this.startDate);
    }

  startDate: any;
  dayDate: Date;
  file: File;
  form: FormGroup;

  ngOnInit() {
      this.dayDate = this.startDate;
      this.dateSeter(this.startDate);
  }

  private dateSeter(inputDate:Date):Date{
    console.log("inputDateU:"+inputDate.getTime());
    inputDate.setDate(inputDate.getDate().valueOf()+1);
    inputDate.setHours(0);
    inputDate.setMinutes(0);
    inputDate.setMinutes(0);
    inputDate.setSeconds(0);
    inputDate.setMilliseconds(0);
    inputDate.setTime(inputDate.getTime()-1000);
    console.log("inputDateI:"+inputDate.getTime());
    console.log("aaaa"+inputDate.toLocaleString());
    
    return inputDate;
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
    this.startDate.setTime(this.startDate.getTime().valueOf()-1000);

    console.log("HumanDate->startDate:"+this.startDate.toLocaleString());
    
  }

  get humanDate(){
    return this.startDate.toISOString().substring(0, 10);
  }


  private addOglas(): void {

    let error : boolean = false;
    error = 
       this.NameFormControl.hasError('required');
       console.log("->startDate:"+this.startDate.toLocaleString());
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
        datum: this.startDate.getTime().valueOf(),
        prodat: false,
        odobren:false,
        postavioID: this.userSrvice.getLoggedIn().id
      };
      this.oglasService.addOglas(oglas,this.file);
    }

    
  }

}
