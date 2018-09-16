import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { SkalaService } from '../services/skala.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Skala } from '../models/skala';

@Component({
  selector: 'app-skala-add-root',
  templateUrl: './skala-add-root.component.html',
  styleUrls: ['./skala-add-root.component.css']
})
export class SkalaAddRootComponent implements OnInit {

  
  constructor(private router: Router,
    private snackBar : MatSnackBar,
  private skalaService: SkalaService) { this.skala = new Skala()}


  skala: Skala;

  ngOnInit() {
    this.skalaService.get().subscribe(x=>{
      console.log(x);
      this.skala = x;
      this.BPFormControl = new FormControl(this.skala.bronzaGranica, [
        Validators.required
      ]);
      this.BSFormControl = new FormControl(this.skala.bronzaPopust, [
        Validators.required
      ]);
      this.SPFormControl = new FormControl(this.skala.srebroGranica, [
        Validators.required
      ]);
      this.SSFormControl = new FormControl(this.skala.srebroPopust, [
        Validators.required
      ]);
      this.GPFormControl = new FormControl(this.skala.zlatoGranica, [
        Validators.required
      ]);
      this.GSFormControl = new FormControl(this.skala.zlatoPopust, [
        Validators.required
      ]);
    });
  }

  BPFormControl = new FormControl('', [
    Validators.required
  ]);
  BSFormControl = new FormControl('', [
    Validators.required
  ]);
  SPFormControl = new FormControl('', [
    Validators.required
  ]);
  SSFormControl = new FormControl('', [
    Validators.required
  ]);
  GPFormControl = new FormControl('', [
    Validators.required
  ]);
  GSFormControl = new FormControl('', [
    Validators.required
  ]);

  
  onSave(){
    this.skala.bronzaGranica = this.BPFormControl.value;
    this.skala.bronzaPopust = this.BSFormControl.value;

    this.skala.srebroGranica = this.SPFormControl.value;
    this.skala.srebroPopust = this.SSFormControl.value;

    this.skala.zlatoGranica = this.GPFormControl.value;
    this.skala.zlatoPopust = this.GSFormControl.value;
    
    console.log(this.skala);
    this.skalaService.save(this.skala);
    
  }
}
