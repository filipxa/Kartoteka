import { User } from "./user";
import { Izvedba } from "./izvedba";
import { Sediste } from "./sediste";

export class Karta{
    idKarte : number;
    izvedba : Izvedba;
    sediste : Sediste;
    korisnik : User;
    popust : number;
    cena : number;
    tip : string;
    lokalNaziv : string;
    constructor(){

    } 
    public static  getTermin(ticket :Karta) : Date{

        return new Date(ticket.izvedba.termin);
    }

    public getTermin() : Date{

        return new Date(this.izvedba.termin);
    }
    public static isCancable(ticket: Karta) : boolean{
      
        let termin = new Date(ticket.izvedba.termin);
        let end = new Date();
        end = new Date(end.getTime() - 30*60000 );
        return end < termin;
      
    }

    public static getLokalName(ticket: Karta) : string{
        return ticket.izvedba.sala.lokal.naziv;

    }
    
}