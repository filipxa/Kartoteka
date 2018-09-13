import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { Repertoar } from '../models/repertoar';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';
import { Naslov } from '../models/naslov';
import { Izvedba } from '../models/izvedba';
import { Sala } from '../models/sala';

@Component({
  selector: 'app-naslov-profile',
  templateUrl: './naslov-profile.component.html',
  styleUrls: ['./naslov-profile.component.css']
})
export class NaslovProfileComponent implements OnInit {
  idNaslova: number;
  idLokal: number;
  lokal: Lokal;
  naslov: Naslov;
  saleTerminiPodaci: any[];


  constructor(private route: ActivatedRoute, private lokalService: LokalService, private router: Router) {

  };

  ngOnInit() {
    this.idNaslova = parseInt(this.route.snapshot.paramMap.get('idNaslova'));
    this.idLokal = parseInt(this.route.snapshot.paramMap.get('idLokala'));
    this.lokalService.getLokal(this.idLokal).subscribe(data => {
      this.lokal = data;
      this.naslov = Repertoar.getNaslov(this.lokal.repertoar, this.idNaslova);

      if (this.naslov == null) {
        alert("Doslo je do greske!");
        this.router.navigate(['/']);
      }
      this.getSaleTermini();
      console.log(this.saleTerminiPodaci);

    });
  }

 
  public getSaleTermini() {

    let izvedbe: Map<Sala, Izvedba[]> = Repertoar.extractSalaIZvedbe(this.lokal.repertoar).get(this.naslov);
    this.saleTerminiPodaci = [];

    izvedbe.forEach(
      (izvedbe: Izvedba[], sala: Sala) => {
        this.saleTerminiPodaci.push({
          sala: sala,
          izvedbe: izvedbe
        });
      }
    );

  }

}
