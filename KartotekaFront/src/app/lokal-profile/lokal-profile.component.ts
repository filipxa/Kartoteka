import { Component, OnInit, Input } from '@angular/core';
import { LokalService } from '../services/lokal.service';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { Lokal } from '../models/lokal';
import { UserService } from '../services/user.service';
import { Repertoar } from '../models/repertoar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { LokalEditProfileComponent } from '../lokal-edit-profile/lokal-edit-profile.component';

@Component({
  selector: 'app-lokal-profile',
  templateUrl: './lokal-profile.component.html',
  styleUrls: ['./lokal-profile.component.css']
})
export class LokalProfileComponent implements OnInit {

  ID: number;
  lokal: Lokal;

  admin: boolean;

  projections: Repertoar = new Repertoar();

  constructor(private lokalService: LokalService, private route: ActivatedRoute, private userService: UserService, public dialog: MatDialog) {

    this.isAdmin();

    this.ID = parseInt(this.route.snapshot.paramMap.get('id'));
    this.lokal = new Lokal();
    lokalService.getLokal(this.ID).subscribe(data => {
      this.lokal = data;
    });
    /* 
        SERVIS ZA KUPLJENJE REPERTOARA
        this.lokalService.getRepertoars().subscribe(data =>{
    
          this.projections = data;
          
    
          mogucnost 2
    
          moracu da pokupim izvedbe pa da ih dodelim u this.projection jer projections ima niz izvedbi, a nisam siguran da li ce mi response sa servera automatski povezati sve to 
        }); */
    //data iznad ce da ima id (nije bitan) i listu projekcija tj listu izvedbi, proveri da li ce lepo da dodeli ovom projection taj data
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(LokalEditProfileComponent, {
      width: '400px',
      data: this.lokal
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {/* 
        window.location.reload(); */
        this.lokal = new Lokal();
        this.lokalService.getLokal(this.ID).subscribe(data => {
          this.lokal = data;
        });
      }

    });
  }


  ngOnInit() {
  }

  isAdmin() {
    this.userService.getLoggedUserAPI().subscribe(data => {
      if (data.tip === "fan")
        this.admin = true;
      else
        this.admin = false;
    });
  }

}
