import { Repertoar } from "./repertoar";
import { Sala } from "./sala";

export class Lokal{
    id : number;
    isPozoriste : boolean;
    repertoar : Repertoar;
    naziv : string;
    adresa : string;
    promotivniOpis : string;
    sala : Array<Sala>;
}