import { Karta } from "./karta";
import { Osoba } from "./osoba";
import { Sala } from "./sala";
import { Naslov } from "./naslov";

export class Izvedba {

    idIzvedba: number;
    sala: Sala;
    karte: Array<Karta>;
    termin: string;
    isFilm: boolean;
    naslov: Naslov;
    reziser: Osoba;
    glumci: Array<Osoba>;
    datum: string;
    cene: Array<number>;
}