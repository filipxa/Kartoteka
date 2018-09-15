import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { ValidatorFn, AbstractControl, FormControl, Validators, FormGroupDirective, NgForm, FormArray } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MatSnackBar, ErrorStateMatcher } from '@angular/material';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.touched));
  }
}

@Component({
  selector: 'app-root-admin-add',
  templateUrl: './root-admin-add.component.html',
  styleUrls: ['./root-admin-add.component.css']
})

export class RootAdminAddComponent implements OnInit {

  regExpValidator(nameRe: RegExp): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const forbidden = nameRe.test(control.value);
      return forbidden ? null : {'regexp': {value: control.value}};
    };
  }

  constructor(private userService: UserService, 
    private router: Router,
    private snackBar : MatSnackBar,
    private lokalSerive:LokalService) {this.listaLokala= new Array() }
  
  listaLokala:string[];
  labelAdmin:string;
  pozirsta:Lokal[];
  bioskopi:Lokal[];
  
  passwordFormControl = new FormControl('', [
    Validators.required
  ]);

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  passwordRFormControl = new FormControl('', [
    Validators.required
  ]);

  lNameFormControl = new FormControl('', [
    Validators.required
  ]);

  fNameFormControl = new FormControl('', [
    Validators.required
  ]);

  telFormControl = new FormControl('', [
    this.regExpValidator(/^[0-9]*$/)
  ]);
  adresaFormControl = new FormControl('', []);
  adminFormKontrol = new FormControl('fan',[]);
  lokaliFormControl = new FormControl(false, []);

  lokaliChange(lokal:Lokal){
    
    if(this.listaLokala.includes(lokal.id.toString())){
      
      var index = this.listaLokala.indexOf(lokal.id.toString());
      if (index > -1) {
        this.listaLokala.splice(index, 1);
      }
      console.log("Izbacen:"+lokal.naziv);
    }
    else{
      this.listaLokala.push(lokal.id.toString());
      console.log("Ubacen:"+lokal.naziv);
    }
  }

  private onRegisterClick() : void
  {
    console.log(this.labelAdmin);
    console.log(this.lokaliFormControl);
    let poruka : string = "";
    let error : boolean = false;
    error = 
       this.fNameFormControl.hasError('required') 
    || this.lNameFormControl.hasError('required') 
    || this.passwordFormControl.hasError('required') 
    || this.passwordRFormControl.hasError('required') 
    || this.emailFormControl.hasError('required')
    || this.emailFormControl.hasError('email');
    
    if (error)
    {
      this.snackBar.open("You must enter all fields correctly!","", {
        duration: 3000,
      });
    } else if (this.passwordFormControl.value!=this.passwordRFormControl.value)
    {
      this.snackBar.open("You must enter matching passwords!","", {
        duration: 3000,
      });
    }  else{
      
      let user : User = {
        id : -1,
        email: this.emailFormControl.value,
        name: this.fNameFormControl.value,
        lName: this.lNameFormControl.value,
        password : this.passwordFormControl.value,
        tip : this.adminFormKontrol.value,
        activated: false,
        tel: this.telFormControl.value,
        listaLokala: this.listaLokala,
        adresa: this.adresaFormControl.value
      };
      console.log(user);
      this.userService.register(user);

    }

  }

  matcher = new MyErrorStateMatcher();

  ngOnInit() {
    this.userService.redirectIfNotLogged("");
    this.lokalSerive.getCinemas().subscribe(c=>{
      this.bioskopi = c;
    });
    this.lokalSerive.getTheatres().subscribe(t=>{
      this.pozirsta = t;
    })
  }
}