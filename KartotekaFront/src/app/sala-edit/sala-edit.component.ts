import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { LokalService } from '../services/lokal.service';

@Component({
  selector: 'app-sala-edit',
  templateUrl: './sala-edit.component.html',
  styleUrls: ['./sala-edit.component.css']
})
export class SalaEditComponent implements OnInit {

  prima: any;

  constructor(public dialogRef: MatDialogRef<SalaEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private localService: LokalService) {
        this.prima = data;
     }

  ngOnInit() {
  }

}
