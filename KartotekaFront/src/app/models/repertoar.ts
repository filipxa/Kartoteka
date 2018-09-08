import { Izvedba } from "./izvedba";
import { Naslov } from "./naslov";

export class Repertoar{

    id : number;
    izvedbe : Array<Izvedba>;
    public static getNaslovi(repertoar : Repertoar) : Naslov[]{
        let rets : Naslov[] = [];
        let nazivi : number[] = [];

        for(let izvedba of repertoar.izvedbe ){
            if(!nazivi.includes(izvedba.naslov.id)){
                nazivi.push(izvedba.naslov.id);
                rets.push(izvedba.naslov);
            }
        }
        return rets;
    }
    static extractNasloviIzvedbe(rep: Repertoar):  Map<Naslov, Izvedba[]> {
        let rets : Map<Naslov, Izvedba[]> = new Map<Naslov, Izvedba[]>();
        for(let izvedba of rep.izvedbe){
            if(!(rets.has(izvedba.naslov))){
                rets.set(izvedba.naslov, new Array<Izvedba>());
            }
            let dt = new Date(izvedba.termin);
            izvedba.datum = dt.toDateString();
            let hours = "" + dt.getHours();
            while(hours.length<2){
                hours="0"+hours;
            }
            let minutes = "" +dt.getMinutes();
            while(minutes.length<2){
                minutes="0"+minutes;
            }
            izvedba.termin = "" + hours + ":" + minutes;
            rets.get(izvedba.naslov).push(izvedba);
        }
        return rets;
      }
}