import { Izvedba } from "./izvedba";
import { Naslov } from "./naslov";
import { Sala } from "./sala";
import { Karta } from "./karta";

export class Repertoar {

    id: number;
    izvedbe: Array<Izvedba>;


    public static getNaslovi(repertoar: Repertoar): Naslov[] {
        let rets: Naslov[] = [];
        let nazivi: number[] = [];

        for (let izvedba of repertoar.izvedbe) {
            if (!nazivi.includes(izvedba.naslov.id)) {
                nazivi.push(izvedba.naslov.id);
                rets.push(izvedba.naslov);
            }
        }
        return rets;
    }

    public static getNaslov(repertoar : Repertoar, idNaslova : number) : Naslov{
        
        for (let izvedba of repertoar.izvedbe) {
            if (izvedba.naslov.id === idNaslova) {
                return izvedba.naslov;
            }
        }
        return null;
    }

    // vraca repertoar u vidu liste sa info za prikaz
    public static getRepertoarZaSpisakNaslova(naslovi: Naslov[]): any {

        // lista za repertoarPrikaz
        let repPrikaz: any = [];

        // pokupiti repertoar za lokal sa osnovnim informacijama
        naslovi.forEach(function (naslov) {

            repPrikaz.push({
                // DODAJ SLIKU
                slika: "https://i.pinimg.com/originals/2e/22/e5/2e22e58a343d9a1c81713f4caa12f60b.jpg",
                idNaslova: naslov.id,
                naziv: naslov.naziv,
                glumci: naslov.glumci,
                zanr: naslov.zanr,
                reziser: naslov.reziser,
                opis: naslov.opis

            })
        });
        return repPrikaz;
    }

    static extractPopustKarteZaNaslov(rep: Repertoar, idNaslova: number): Map<Izvedba, Array<Karta>> {
        let rets: Map<Izvedba, Karta[]> = new Map<Izvedba, Karta[]>();
        for (let izvedba of rep.izvedbe) {
            if (idNaslova === izvedba.naslov.id) {
                let kartePopust: Array<Karta> = new Array<Karta>();
                izvedba.karte.forEach(karta => {
                    if (karta.popust != 0) {
                        kartePopust.push(karta);
                    }
                });
                rets.set(izvedba, kartePopust);
            }
        }
        return rets;
    }


    static extractNasloviIzvedbe(rep: Repertoar): Map<Naslov, Izvedba[]> {
        let rets: Map<Naslov, Izvedba[]> = new Map<Naslov, Izvedba[]>();
        for (let izvedba of rep.izvedbe) {
            if (!(rets.has(izvedba.naslov))) {
                rets.set(izvedba.naslov, new Array<Izvedba>());
            }
            let dt = new Date(izvedba.termin);
            izvedba.datum = dt.toDateString();
            let hours = "" + dt.getHours();
            while (hours.length < 2) {
                hours = "0" + hours;
            }
            let minutes = "" + dt.getMinutes();
            while (minutes.length < 2) {
                minutes = "0" + minutes;
            }
            izvedba.termin = "" + hours + ":" + minutes;
            rets.get(izvedba.naslov).push(izvedba);
        }
        return rets;
    }


    static extractNasloviIzvedbeFilterDate(rep: Repertoar): Map<Naslov, Izvedba[]> {
        let rets: Map<Naslov, Izvedba[]> = new Map<Naslov, Izvedba[]>();
        for (let izvedba of rep.izvedbe) {
          
            let dt = new Date(izvedba.termin);


            let end = new Date();
            end = new Date(end.getTime() - 30*60000); 
            if(end > dt){
                continue;
            }
            if (!(rets.has(izvedba.naslov))) {
                rets.set(izvedba.naslov, new Array<Izvedba>());
            }

            izvedba.datum = dt.toDateString();
            let hours = "" + dt.getHours();
            while (hours.length < 2) {
                hours = "0" + hours;
            }
            let minutes = "" + dt.getMinutes();
            while (minutes.length < 2) {
                minutes = "0" + minutes;
            }
            izvedba.termin = "" + hours + ":" + minutes;
            rets.get(izvedba.naslov).push(izvedba);
        }
        return rets;
    }



    static extractSalaIZvedbe(rep: Repertoar): Map<Naslov, Map<Sala, Izvedba[]>> {
        let rets: Map<Naslov, Map<Sala, Izvedba[]>> = new Map<Naslov, Map<Sala, Izvedba[]>>();
        let mapa: Map<Naslov, Izvedba[]> = Repertoar.extractNasloviIzvedbe(rep);
        let naslovi: Array<Naslov> = Repertoar.getNaslovi(rep);
        for (let naslov of naslovi) {
            let izvedbe: Array<Izvedba> = mapa.get(naslov);
            let sale: Array<Sala> = new Array<Sala>();
            let salaIzvedba: Map<Sala, Array<Izvedba>> = new Map<Sala, Array<Izvedba>>();
            for (let izvedba of izvedbe) {

                if (!sale.includes(izvedba.sala)) {
                    sale.push(izvedba.sala);
                    salaIzvedba.set(izvedba.sala, new Array<Izvedba>());
                }
                salaIzvedba.get(izvedba.sala).push(izvedba);
            }
            rets.set(naslov, salaIzvedba);
        }
        return rets;
    }
}