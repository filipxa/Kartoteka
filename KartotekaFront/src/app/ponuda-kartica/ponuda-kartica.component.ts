import { Component, OnInit, Input } from '@angular/core';
import { Ponuda } from '../models/ponuda';
import { PonudaService } from '../services/ponuda.service';
import { Router } from '@angular/router';
import { FanzoneComponent } from '../fanzone/fanzone.component';

@Component({
  selector: 'app-ponuda-kartica',
  templateUrl: './ponuda-kartica.component.html',
  styleUrls: ['./ponuda-kartica.component.css']
})
export class PonudaKarticaComponent implements OnInit {
  @Input() ponuda:Ponuda;

  constructor(private ponudaService:PonudaService,private router:Router) { }

  acceptPonuda(){
    this.ponuda.prihvacena=true;
    this.ponudaService.acceptPonuda(this.ponuda.id);
    this.router.navigate(['/']);
    window.location.reload();
  }

  ngOnInit() {
  }

}