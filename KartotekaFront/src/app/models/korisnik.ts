

export class Korisnik {
    idKorisnika: number;
    tip: string;
    ime: string;
    prezime: string;
    email: string;
    password: string;
    listaPrijatelja: Array<Korisnik>;
    uuid: string;
    activated: number;
    brBodova: number;
    brTelefona: number;
    adresa: string;
    adminLokali:string[];    
  }