import { Component, OnInit, Input } from '@angular/core';
import { Oglas } from '../models/oglas';
import { OglasService } from '../services/oglas.service';
import { ActivatedRoute } from '@angular/router';
import { UploadService } from '../services/upload.service';
import { UserService } from '../services/user.service';
import { MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-oglas-editovanje',
  templateUrl: './oglas-editovanje.component.html',
  styleUrls: ['./oglas-editovanje.component.css']
})
export class OglasEditovanjeComponent implements OnInit {
  @Input() id:number;
  constructor(private oglasService : OglasService,
    private route: ActivatedRoute,
    private uploadService: UploadService,
    private userSrvice: UserService,
    private snackBar : MatSnackBar,
    private http:HttpClient,
    private fb: FormBuilder) { 
      this.startDate = new Date();
      this.startDate = this.dateSeter(this.startDate);
      
      this.milisecDate = this.startDate.getTime();

      this.oglas = new Oglas();
    }
  oglas: Oglas;
  ulazId:number;
  private sub: any;
  startDate: any;
  file: File;
  form: FormGroup;
  events: string[] = [];
  milisecDate: number;
  datum:Date;
  today = new Date().toJSON().split('T')[0];

  private dateSeter(inputDate:Date):Date{
    console.log("inputDateU:"+inputDate.getTime());
    inputDate.setDate(inputDate.getDate().valueOf()+1);
    inputDate.setHours(0);
    inputDate.setMinutes(0);
    inputDate.setMinutes(0);
    inputDate.setSeconds(0);
    inputDate.setMilliseconds(0);
    inputDate.setTime(inputDate.getTime()-1);
    console.log("inputDateI:"+inputDate.getTime());
    console.log(inputDate.toLocaleString());
    
    return inputDate;
  }
  set humanDate(e){
    e = e.split('-');
    
    let d = new Date(Date.UTC(e[0], e[1]-1, e[2]));
    this.startDate.setFullYear(d.getUTCFullYear(), d.getUTCMonth(), d.getUTCDate());
    this.milisecDate = d.getTime();
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
  get humanDate(){
    return this.startDate.toISOString().substring(0, 10);
  }

  NameFormControl = new FormControl('', [
    
  ]);

  DescriptionFormControl = new FormControl('', [
  ]);


  ngOnInit() {
    this.sub = this.route.params.subscribe(p=>{
      this.ulazId = +p['id'];
    });

    this.oglasService.getOglas(this.ulazId).subscribe(p=>{this.oglas = p;
      console.log(this.oglas);
      this.startDate = new Date(this.oglas.datum);
      this.NameFormControl = new FormControl(this.oglas.naziv, []);
    
      this.DescriptionFormControl = new FormControl(this.oglas.opis, []);
    });
    
  }
  saveOglas(){
    this.oglas.datum = this.startDate.getTime().valueOf();
    this.oglas.naziv = this.NameFormControl.value;
    this.oglas.opis = this.DescriptionFormControl.value;
    let error : boolean = false;
    error = this.NameFormControl.hasError('required');

    if (error)
    {
      this.snackBar.open("You must enter Name!","", {
        duration: 3000,
      });
    }else{
      this.oglasService.addOglas(this.oglas,this.file);
    }
    console.log(this.oglas);
  }



}
