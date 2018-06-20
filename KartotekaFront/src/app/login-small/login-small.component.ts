import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service'
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {Router} from "@angular/router";




@Component({
  selector: 'app-login-small',
  templateUrl: './login-small.component.html',
  styleUrls: ['./login-small.component.css']
})
export class LoginSmallComponent implements OnInit {

  constructor(private userService : UserService, private router: Router) { }
  
  
  passwordFormControl = new FormControl('', [
    Validators.required
  ]);

  usernameFormControl = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  onClick(){
    let username = this.usernameFormControl.value;
    let password = this.passwordFormControl.value;
    this.passwordFormControl.markAsTouched();
    this.usernameFormControl.markAsTouched(); 
   if(username.trim().length>0 && password.trim().length>0){
    this.userService.logIn(username.trim(), password.trim());
   }
      
  }
  onKey(event: any) { // without type info
    if(event.key == "Enter"){
      this.onClick();
    }
  }
  


  ngOnInit() {
    
  }

}
