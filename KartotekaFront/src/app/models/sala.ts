import { Lokal } from "src/app/models/lokal";
import { Sediste } from "./sediste";

export class Sala{
    idSale : number;
    brRedova : number;
    brKolona : number;
    lokal : Lokal;
    naziv : String;
    sedista : Array<Sediste>;
    
}