import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service'
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {Router} from "@angular/router";


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && ( control.touched ));
  }
}

@Component({
  selector: 'app-login-small',
  templateUrl: './login-small.component.html',
  styleUrls: ['./login-small.component.css']
})
export class LoginSmallComponent implements OnInit {

  constructor(private userService : UserService, private router: Router) { }
  onClick(username: string, password : string){
   if(username.trim().length>0 && password.trim().length>0){
    this.userService.logIn(username.trim(), password.trim());
   }
      
  }
  
  passwordFormControl = new FormControl('', [
    Validators.required
  ]);

  usernameFormControl = new FormControl('', [
    Validators.required
  ]);

  matcher = new MyErrorStateMatcher();
  ngOnInit() {
    
  }

}
