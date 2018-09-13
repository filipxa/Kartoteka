import { Osoba } from "./osoba";

export class Naslov {
    id : number;
    naziv : string;
    reziser : Osoba;
    glumci : Array<Osoba>;
    zanr : string;
    trajanje : number;
    ocena : number;
    opis : string;
}