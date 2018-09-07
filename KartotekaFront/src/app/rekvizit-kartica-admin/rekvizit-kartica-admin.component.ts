import { Component, OnInit, Input } from '@angular/core';
import { Rekvizit } from '../models/rekvizit';
import { RekvizitService } from '../services/rekvizit.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-rekvizit-kartica-admin',
  templateUrl: './rekvizit-kartica-admin.component.html',
  styleUrls: ['./rekvizit-kartica-admin.component.css']
})
export class RekvizitKarticaAdminComponent implements OnInit {
  @Input() rekvizit: Rekvizit;

  constructor(private rekvizitService: RekvizitService, private userSrvice: UserService,private router: Router) { }

  editRekvizit(id){
    console.log(id);
    
    this.router.navigate(['/rekvizitEdit',this.rekvizit]);
  }

  deleteRekvizit(){
    this.rekvizit.rezervisano=true;
    this.rekvizitService.deleteRekvizit(this.rekvizit);
  }

  ngOnInit() {
  }


}
