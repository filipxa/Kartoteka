import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { RekvizitService } from '../services/rekvizit.service';
import { FormControl, Validators } from '@angular/forms';
import { Rekvizit } from '../models/rekvizit';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-add-rekvizit',
  templateUrl: './add-rekvizit.component.html',
  styleUrls: ['./add-rekvizit.component.css']
})
export class AddRekvizitComponent implements OnInit {

  constructor(private rekvizitService: RekvizitService, private userSrvice: UserService,private snackBar : MatSnackBar) { }

  ngOnInit() {
  }

  NameFormControl = new FormControl('', [
    Validators.required
  ]);
  PriceFormControl = new FormControl('', [
    Validators.required
  ]);
  DescriptionFormControl = new FormControl('', [
  ]);

  private addRekvizit(): void {
    let error : boolean = false;
    error = 
       this.NameFormControl.hasError('required') 
    || this.PriceFormControl.hasError('required');

    if (error)
    {
      this.snackBar.open("You must enter Name and Price!","", {
        duration: 3000,
      });
    }else{
      let rekvizit : Rekvizit = {
        idRekvizita: -1,
        naziv: this.NameFormControl.value,
        cena: this.PriceFormControl.value,
        opis: this.DescriptionFormControl.value,
        rezervisano: false
        
      };
      console.log(rekvizit);
      this.rekvizitService.addRekvizit(rekvizit);
    }

    
  }

}
