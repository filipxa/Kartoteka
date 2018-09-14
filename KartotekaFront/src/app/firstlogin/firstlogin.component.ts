import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { User } from '../models/user';

@Component({
  selector: 'app-firstlogin',
  templateUrl: './firstlogin.component.html',
  styleUrls: ['./firstlogin.component.css']
})
export class FirstloginComponent implements OnInit {

  constructor(private userService: UserService, 
    private router: Router,
    private snackBar : MatSnackBar) { }

  passwordFormControl = new FormControl('', [
    Validators.required
  ]);

  passwordRFormControl = new FormControl('', [
    Validators.required
  ]);
  loggedUser : User;

  private onRegisterClick() : void
  {
    console.log(this.loggedUser);
    let poruka : string = "";
    let error : boolean = false;
    error =  this.passwordFormControl.hasError('required') 
    || this.passwordRFormControl.hasError('required') 
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
        id : this.loggedUser.id,
        email: this.loggedUser.email,
        name: this.loggedUser.name,
        lName: this.loggedUser.lName,
        password : this.passwordFormControl.value,
        tip : this.loggedUser.tip,
        activated: true,
        tel: this.loggedUser.tel,
        listaLokala: this.loggedUser.listaLokala,
        adresa: this.loggedUser.adresa
      };
      console.log(user);
      this.userService.register(user);

    }

  }



  ngOnInit() {
    this.userService.getLoggedUserAPI().subscribe(x=>{
      this.loggedUser=x;
    });
    console.log(this.loggedUser);
  }

}
