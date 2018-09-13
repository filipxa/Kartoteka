import { Component, OnInit } from '@angular/core';
import { User } from '../models/user'
import { UserService } from '../services/user.service'
import { FormControl, FormGroupDirective, NgForm, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router'

import { MatSnackBar } from '@angular/material';
import { Observable, of } from 'rxjs';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.touched));
  }
}

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  regExpValidator(nameRe: RegExp): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const forbidden = nameRe.test(control.value);
      return forbidden ? null : {'regexp': {value: control.value}};
    };
  }

  constructor(private userService: UserService, private router: Router,private snackBar : MatSnackBar) { }

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
  private onRegisterClick() : void
  {
    
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
        tip : "korisnik",
        activated: false,
        tel: this.telFormControl.value,
        listaLokala: new Array(),
        adresa: this.adresaFormControl.value
      };



      this.userService.register(user);

    }

  }

  matcher = new MyErrorStateMatcher();

  ngOnInit() {
    this.userService.redirectIfNotLogged("");
  }

}
