import { Izvedba } from "./izvedba";
import { Karta } from "./karta";

export class Sediste {

    idSedista : number;
    red : number;
    kolona : number;
    zauzeto : boolean;

    static getSeats( izvedba : Izvedba, karte : Array<Karta>): Array<Array<Sediste>> {
        let rets : Sediste[][] = [];

        for(let i: number = 0; i < izvedba.sala.brRedova; i++){
            rets[i]=[];
            for(let j: number = 0; j < izvedba.sala.brKolona; j++){
                rets[i][j] = new Sediste();
                rets[i][j].red=i;
                rets[i][j].kolona=j;
                rets[i][j].zauzeto=true; 
                
            }
        }
        for(let karta of karte ){
            rets[karta.sediste.red][karta.sediste.kolona].zauzeto=karta.tip!="slobodno";
            rets[karta.sediste.red][karta.sediste.kolona].idSedista=karta.sediste.idSedista;
        }

        return rets;
      }
    
}