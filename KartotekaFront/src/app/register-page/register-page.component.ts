import { Component, OnInit } from '@angular/core';
import { User } from '../models/user'
import { UserService } from '../services/user.service'
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
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

  private onRegisterClick(fName : string, lName : string, email : string, password : string, password2 : string) : void
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
    } else if (password.trim()!=password2.trim())
    {
      this.snackBar.open("You must enter matching passwords!","", {
        duration: 3000,
      });
    }  else{
      this.userService.register(fName, lName, email, password, "korisnik");

    }

  }

  matcher = new MyErrorStateMatcher();

  ngOnInit() {
    if (this.userService.loggedUser != null) {;
      this.router.navigate(["/"]);
    }

  }

}
