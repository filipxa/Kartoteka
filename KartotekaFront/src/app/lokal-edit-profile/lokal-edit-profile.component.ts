import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';

@Component({
  selector: 'app-lokal-edit-profile',
  templateUrl: './lokal-edit-profile.component.html',
  styleUrls: ['./lokal-edit-profile.component.css']
})
export class LokalEditProfileComponent implements OnInit {

  lokal: Lokal;

  constructor(public dialogRef: MatDialogRef<LokalEditProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Lokal, private localService: LokalService) {
        this.lokal = data;
     }

  ngOnInit() {
  }

  
  cancel(): void {
    this.dialogRef.close(false);
  }

  chooseFile()
  {
  }

  submitChange()
  {
    this.localService.updateLokal(this.lokal).subscribe(data =>{
      console.log(data);
    });
    //pozove servis koji ce da izmeni taj lokal u bazi
    this.dialogRef.close(true);
  }
}
