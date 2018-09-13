import { Component, OnInit, Input } from '@angular/core';
import { LokalService } from '../services/lokal.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { Lokal } from '../models/lokal';
import { UserService } from '../services/user.service';
import { Repertoar } from '../models/repertoar';
import { MatDialog } from '@angular/material';
import { LokalEditProfileComponent } from '../lokal-edit-profile/lokal-edit-profile.component';
import { Naslov } from '../models/naslov';
import { Izvedba } from '../models/izvedba';
import { Sala } from '../models/sala';
import { TerminCena } from '../models/terminCena';
import { Karta } from '../models/karta';
import { SalaEditComponent } from '../sala-edit/sala-edit.component';

@Component({
  selector: 'app-lokal-profile',
  templateUrl: './lokal-profile.component.html',
  styleUrls: ['./lokal-profile.component.css']
})
export class LokalProfileComponent implements OnInit {

  ID: number;
  lokal: Lokal;
  podaci : any = [];
  profilLokala : any = [];
  repertoarLista : any = [];


  admin: boolean;



  constructor(private lokalService: LokalService, private route: ActivatedRoute, private userService: UserService, public dialog: MatDialog, private router: Router) {

    this.isAdmin();

    this.ID = parseInt(this.route.snapshot.paramMap.get('id'));
    this.lokal = new Lokal();
    lokalService.getLokal(this.ID).subscribe(data => {
      this.lokal = data;
      // this.popuniPodatkeZaPrikaz();
      
      // console.log(this.podaci);

      // popunjava podatke za prikaz profila lokala
      this.popuniProfilLokala();
      console.log(this.profilLokala);
      
      // popunjava reperoar za prikaz
      this.repertoarLista = this.popuniRepertoarLokala();
      console.log(this.repertoarLista);
    });

    
   
    
  }

 
  popuniRepertoarLokala() : any{
     // pokupiti sve diff naslove
     let naslovi : Naslov[] = [];
     console.log(this.lokal);
     
     naslovi = Repertoar.getNaslovi(this.lokal.repertoar);
     
     let repPrikaz : any = [];
     repPrikaz = Repertoar.getRepertoarZaSpisakNaslova(naslovi);
     return repPrikaz;
    
  }
  popuniProfilLokala(){
   
    this.profilLokala.push({
      idLokala : this.lokal.id,
      nazivLokala : this.lokal.naziv,
      adresaLokala : this.lokal.adresa,
      promotivniOpis : this.lokal.promotivniOpis

    })
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

  ConfSale(sala): void {
    const dialogRef = this.dialog.open(SalaEditComponent, {
      width: '400px',
      data: sala
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
      }
    });
  }


  ngOnInit() {
   
    
  }

  

  getSalaTerminiCene(salaIzvedba : Map<Sala, Izvedba[]>) : Map<String, TerminCena[]> {
    let salaTerminiCene : Map<String,TerminCena[]>  =  new Map<String, Array<TerminCena>>();
    salaIzvedba.forEach(
      (izvedbe: Izvedba[], sala : Sala)=>
      {
        let listaTerminiCene : Array<TerminCena> = new Array<TerminCena>();

        izvedbe.forEach((value : Izvedba, index: number)=>{
          let terminCena : TerminCena;
          terminCena = new TerminCena();
          terminCena.termin = value.datum +  value.termin;
          terminCena.cena = value.cene;
          listaTerminiCene.push(terminCena);
        });
        salaTerminiCene.set(sala.naziv, listaTerminiCene);
    
      });
      return salaTerminiCene;
  }

  getKarteNaPopustuZaNaslov(idNaslova : number){

    let mapa: Map<Izvedba, Karta[]> = new Map<Izvedba, Array<Karta>>();
    mapa = Repertoar.extractPopustKarteZaNaslov(this.lokal.repertoar, idNaslova);
    let podaci = [];
    
    mapa.forEach((karte : Karta[], izvedba : Izvedba) => {
      podaci.push({
        idIzvedbe : izvedba.idIzvedba,
        karte : izvedba.karte,
        termin : izvedba.termin, 
        naslov : izvedba.naslov.naziv,
      });

      // pokupim sve razlicite termine 
      // let datumi : Date[] = new Array<Date>();
      // if(!datumi.includes(key.termin)){
      //   datumi.push(key.termin);
      // }

      // napunim za svaki termin listu karata
      // let terminKarte : Map<Date, Karta[]> = new Map<Date, Karta[]>();
      // datumi.forEach(termin => {
      // });

      });
    return podaci;
  }


  // profil predstave/filma
  popuniPodatkeZaPrikaz() {

    let mapaSvih: Map<Naslov, Map<Sala, Array<Izvedba>>> = Repertoar.extractSalaIZvedbe(this.lokal.repertoar);
    let podaci = [];
    
    mapaSvih.forEach(
      (salaIzvedba: Map<Sala,
         Izvedba[]>, naslov: Naslov) => {

          let salaTerminiCene : Map<String, TerminCena[]>  = this.getSalaTerminiCene(salaIzvedba);

          podaci.push({
            idFilma : naslov.id,
            nazivFilma : naslov.naziv,
            glumci : naslov.glumci,
            zanr : naslov.zanr,
            trajanje : naslov.trajanje,
            ocena : naslov.ocena,
            salaTerminiCene : salaTerminiCene,
            opis : naslov.opis

          })
      });
    this.podaci = podaci;
    
  }


  isAdmin() {
    this.userService.getLoggedUserAPI().subscribe(data => {
     
        if ( data  && (data.tip === "adminBioskop" || data.tip === "adminPozoriste" ))
          this.admin = true;
        else
          this.admin = false;
      
    });
  }

  details(id)
  {
    this.router.navigate(['/naslov/' + id]);
  }

}


