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
import { Location } from '@angular/common';
import { IzvedbaPravljenjeNoveComponent } from '../izvedba-pravljenje-nove/izvedba-pravljenje-nove.component';

@Component({
  selector: 'app-lokal-profile',
  templateUrl: './lokal-profile.component.html',
  styleUrls: ['./lokal-profile.component.css']
})
export class LokalProfileComponent implements OnInit {

  idLokala : string;
  ID: number;
  lokal: Lokal;
  podaci: any = [];
  profilLokala: any = [];
  repertoarLista: any = [];
  admin: boolean;


  saleLokala: Sala[];
  saleSaIzvedbama: Sala[];
  saleZaIzmenu: Sala[];




  constructor(
    private lokalService: LokalService, 
    private route: ActivatedRoute, 
    private userService: UserService, 
    public dialog: MatDialog, 
    private router: Router,
    private location: Location) {


    
    
    
    this.ID = parseInt(this.route.snapshot.paramMap.get('id'));
    this.idLokala = this.ID.toString();
    this.lokal = new Lokal();
    lokalService.getLokal(this.ID).subscribe(data => {
      this.lokal = data;
      this.repertoarLista = this.popuniRepertoarLokala();
      // popunjava podatke za prikaz profila lokala
      this.popuniProfilLokala();
      this.pokupiSaleLokal();
      console.log("sale lokala");
      console.log(this.saleLokala);

      // console.log("PODACI");
      
      // console.log(this.userService.getLoggedIn());
      // console.log(this.userService.getLoggedIn().tip == 'adminBioskop');
      // console.log(this.userService.getLoggedIn().tip == 'adminPozoriste');
      // console.log(this.userService.getLoggedIn().listaLokala.includes(this.idLokala));
      
      
      this.popuniSaleUKojimaImaIzvedbe();
      console.log("Sale Sa izvedbana");
      console.log(this.saleSaIzvedbama);
      
      
      this.popuniSaleZaIzmenu();
      console.log("Sale za izmenu");
      
      console.log(this.saleZaIzmenu);
      
      this.isAdmin();



    });
  }


  dodajIzvedbu(){

    this.router.navigate(['lokal/novaIzvedba/' + this.lokal.id]);


  }


  popuniSaleZaIzmenu(){
    // sve sale iz saleLokala koje se ne nalaze u saleIzmenaProvera su za prikaz i mogu se menjati
    this.saleZaIzmenu = new Array(); 
    this.saleLokala.forEach(element => {
      
      this.saleSaIzvedbama.forEach(e2 => {
        if(e2.idSale!=element.idSale){
         
          this.saleZaIzmenu.push(element);
        }
      });
    });
  
    
  }

  pokupiSaleLokal() {
    this.saleLokala = [];
    
    this.lokal.sala.forEach(element => {      
      this.saleLokala.push(element);
    });
  }

  // puni samo sale u kojima nema nijedne izvedbe
  popuniSaleUKojimaImaIzvedbe() {
    let mapaSvih: Map<Naslov, Map<Sala, Array<Izvedba>>> = Repertoar.extractSalaIZvedbe(this.lokal.repertoar);
    
    this.saleSaIzvedbama = [];

    mapaSvih.forEach(
      (mapa: Map<Sala, Izvedba[]>, naslov: Naslov) => {
        mapa.forEach((iz: Izvedba[], sala: Sala) => {
         if(iz != []){
            this.saleSaIzvedbama.push(sala);
         }
        });
      });
  }


  popuniRepertoarLokala(): any {
    // pokupiti sve diff naslove
    let naslovi: Naslov[] = [];

    naslovi = Repertoar.getNaslovi(this.lokal.repertoar);


    let repPrikaz: any = [];
    repPrikaz = Repertoar.getRepertoarZaSpisakNaslova(naslovi);
    return repPrikaz;

  }
  popuniProfilLokala() {

    this.profilLokala.push({
      idLokala: this.lokal.id,
      nazivLokala: this.lokal.naziv,
      adresaLokala: this.lokal.adresa,
      promotivniOpis: this.lokal.promotivniOpis

    })
  }

  editProfile(): void {
    const dialogRef = this.dialog.open(LokalEditProfileComponent, {
      width: '400px',
      data: this.lokal
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.lokal = new Lokal();
        this.lokalService.getLokal(this.ID).subscribe(data => {
          this.lokal = data;
        });
      }

    });
  }

  ConfSale(idSale): void {

    const dialogRef = this.dialog.open(SalaEditComponent, {
      width: '400px',
      data: this.lokal.id + "_" + idSale
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
      if (result) {

      }
    });
  }


  ngOnInit() {


  }



  getSalaTerminiCene(salaIzvedba: Map<Sala, Izvedba[]>): Map<String, TerminCena[]> {
    let salaTerminiCene: Map<String, TerminCena[]> = new Map<String, Array<TerminCena>>();
    salaIzvedba.forEach(
      (izvedbe: Izvedba[], sala: Sala) => {
        let listaTerminiCene: Array<TerminCena> = new Array<TerminCena>();

        izvedbe.forEach((value: Izvedba, index: number) => {
          let terminCena: TerminCena;
          terminCena = new TerminCena();
          terminCena.termin = value.datum + value.termin;
          terminCena.cena = value.cene;
          listaTerminiCene.push(terminCena);
        });
        salaTerminiCene.set(sala.naziv, listaTerminiCene);

      });
    return salaTerminiCene;
  }

  getKarteNaPopustuZaNaslov(idNaslova: number) {

    let mapa: Map<Izvedba, Karta[]> = new Map<Izvedba, Array<Karta>>();
    mapa = Repertoar.extractPopustKarteZaNaslov(this.lokal.repertoar, idNaslova);
    let podaci = [];

    mapa.forEach((karte: Karta[], izvedba: Izvedba) => {
      podaci.push({
        idIzvedbe: izvedba.idIzvedba,
        karte: izvedba.karte,
        termin: izvedba.termin,
        naslov: izvedba.naslov.naziv,
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

        let salaTerminiCene: Map<String, TerminCena[]> = this.getSalaTerminiCene(salaIzvedba);

        podaci.push({
          idFilma: naslov.id,
          nazivFilma: naslov.naziv,
          glumci: naslov.glumci,
          zanr: naslov.zanr,
          trajanje: naslov.trajanje,
          ocena: naslov.ocena,
          salaTerminiCene: salaTerminiCene,
          opis: naslov.opis

        })
      });
    this.podaci = podaci;

  }


  isAdmin() {
    this.userService.getLoggedUserAPI().subscribe(data => {

      if (data && data.listaLokala.includes(this.lokal.id.toString()))
        this.admin = true;
      else
        this.admin = false;

    });
  }

  details(id) {
    this.router.navigate(['/naslovProfil/' + id + "/" + this.lokal.id]);
  }

}


