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
  selector: 'app-edit-profile-admin-fan',
  templateUrl: './edit-profile-admin-fan.component.html',
  styleUrls: ['./edit-profile-admin-fan.component.css']
})
export class EditProfileAdminFanComponent implements OnInit {

  regExpValidator(nameRe: RegExp): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const forbidden = nameRe.test(control.value);
      return forbidden ? null : { 'regexp': { value: control.value } };
    };
  }

  constructor(private userService: UserService, private router: Router, private snackBar: MatSnackBar) { }
  oldUser: User = this.userService.getUserLogged();
  user: User = {
    id: this.oldUser.id,
    email: this.oldUser.email,
    name: this.oldUser.name,
    lName: this.oldUser.lName,
    password: this.oldUser.password,
    tip: this.oldUser.tip,
    activated: false,
    tel: this.oldUser.tel,
    adresa: this.oldUser.adresa
  };


  passwordFormControl = new FormControl('', [
    Validators.required
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


  valuechangeName(newValue) {
    this.user.name = newValue;
    console.log(newValue);
    
  }
  valuechangeLName(newValue) {
    this.user.lName = newValue;
    console.log(newValue);
  }
  valuechangeTel(newValue) {
    this.user.tel = newValue;
    console.log(newValue);
  }
  valuechangeAdresa(newValue) {
    this.user.adresa = newValue;
    console.log(newValue);
  }
  private onSaveClick(): void {

    let poruka: string = "";
    let error: boolean = false;
    error =
      this.user.name == ""
      || this.user.lName == "";

    if (error) {
      this.snackBar.open("You must enter all fields correctly!", "", {
        duration: 3000,
      });
    } else if (this.passwordFormControl.value != this.passwordRFormControl.value) {
      this.snackBar.open("You must enter matching passwords!", "", {
        duration: 3000,
      });
    } else {
      if (!(this.passwordFormControl.value == "" || this.passwordFormControl.value == "undefined")) {
        this.user.password = this.passwordFormControl.value;
      }
      
      this.user.activated = true;
      this.userService.register(this.user);
      this.userService.resetLoggedUser(this.user);
    }

  }

  matcher = new MyErrorStateMatcher();

  ngOnInit() {
    if (this.oldUser == null) {
      this.router.navigate(["/"]);
    }

  }

}
