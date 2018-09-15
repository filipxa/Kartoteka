package sw3.kartoteka.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Naslov;
import sw3.kartoteka.model.entity.Osoba;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.model.entity.Repertoar;
import sw3.kartoteka.model.entity.Sala;
import sw3.kartoteka.model.entity.Sediste;
import sw3.kartoteka.repository.IzvedbaRepository;
import sw3.kartoteka.repository.LokalRepository;
import sw3.kartoteka.repository.NaslovRepository;
import sw3.kartoteka.repository.RepertoarRepository;
import sw3.kartoteka.repository.SalaRepository;
import sw3.kartoteka.repository.SedisteRepository;
import sw3.kartoteka.services.KartaService;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.LokalService;
import sw3.kartoteka.services.OsobaService;
import sw3.kartoteka.services.RekvizitService;

@Service
public class InitBean {
	@Autowired
	KartaService kartaService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	IzvedbaRepository izvedbaRepository;
	
	@Autowired
	LokalService lokalService;
	
	@Autowired
	NaslovRepository naslovRepository;
	
	@Autowired
	RepertoarRepository repertoarRepository;
	
	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	SedisteRepository sedisteRepository;
	
	@Autowired
	RekvizitService rekvizitService;
	
	@Autowired
	OsobaService osobaService;
	
	
	public void fillData(){
		Korisnik korisnik1 = new Korisnik( "korisnik", "Filip", "Micic", "filipxa@hotmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik1);
		Korisnik korisnik = new Korisnik( "korisnik", "Pera", "Peric", "filipxa@gmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik);
		Korisnik korisnik2 = new Korisnik( "fan", "Filip", "Micic", "a@a.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik2);
		Korisnik korisnik3 = new Korisnik( "korisnik", "Jova", "Jovic", "vlado12310@gmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Subotica");
		korisnikService.save(korisnik3);
		Korisnik korisnik4 = new Korisnik( "root", "Koren", "Korenovic", "root@a.a", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Visnjevac");
		korisnikService.save(korisnik4);
		korisnik1.getListaPrijatelja().add(korisnik);
		korisnik.getListaPrijatelja().add(korisnik1);
		korisnikService.save(korisnik1);
		korisnikService.save(korisnik);
		
		
		// administrator 

		Korisnik test = korisnikService.findByEmail(korisnik1.getEmail());
		
		Izvedba izvedba = new Izvedba();
		izvedbaRepository.save(izvedba);
		

		Naslov naslov = new Naslov();
		
		naslov.setNaziv("Leave No Trace (2018)");
		naslovRepository.save(naslov);
		
		Repertoar rep = new Repertoar();
		rep.setIzvedbe(new ArrayList<Izvedba>());
		rep.getIzvedbe().add(izvedba);
		repertoarRepository.save(rep);
		
		// lokali
		Lokal lokal = new Lokal();
		lokal.setNaziv("Cineplexxx");
		lokal.setIsPozoriste(false);
		lokal.setAdresa("SVetogoroooooska 33");
		lokal.setPromotivniOpis("Also check out: Image Overlay Slide, Image Overlay Zoom, Image Overlay Title and Image Overlay Icon.");
		lokal.setRepertoar(rep);
		
		lokalService.save(lokal);
		
		Lokal lokal1 = new Lokal();
		lokal1.setNaziv("Bioskop1");
		lokal1.setIsPozoriste(false);
		lokal1.setAdresa("SVetogoroooooska 33");
		lokal1.setPromotivniOpis("Also check out: Image Overlay Slide, Image Overlay Zoom, Image Overlay Title and Image Overlay Icon.");
		
		lokal1.setRepertoar(rep);
		
		lokalService.save(lokal1);
		
		Lokal lokal2 = new Lokal();
		lokal2.setNaziv("Bioskop2");
		lokal2.setAdresa("SVetogoroooooska 33");
		lokal2.setPromotivniOpis("Also check out: Image Overlay Slide, Image Overlay Zoom, Image Overlay Title and Image Overlay Icon.");
		lokal2.setIsPozoriste(false);
		lokal2.setRepertoar(rep);
		
		lokalService.save(lokal2);
		
		Lokal lokal4 = new Lokal();
		lokal4.setNaziv("ARENICA");
		lokal4.setIsPozoriste(false);
		lokal4.setAdresa("SVetogoroooooska 33");
		lokal4.setPromotivniOpis("Also check out: Image Overlay Slide, Image Overlay Zoom, Image Overlay Title and Image Overlay Icon.");
		
		lokal4.setRepertoar(rep);
		
		lokalService.save(lokal4);
		
		
		Lokal lokal5 = new Lokal();
		lokal5.setNaziv("ARENICA");
		lokal5.setIsPozoriste(false);
		lokal5.setAdresa("SVetogoroooooska 33");
		lokal5.setPromotivniOpis("Also check out: Image Overlay Slide, Image Overlay Zoom, Image Overlay Title and Image Overlay Icon.");
		
		lokal5.setRepertoar(rep);
		
		lokalService.save(lokal5);
		
		Rekvizit rekvizit = new Rekvizit();
		rekvizit.setCena(200);
		rekvizit.setNaziv("Naziv");
		rekvizit.setOpis("Brutalno");
		rekvizitService.save(rekvizit);
		
		rekvizit = new Rekvizit();
		rekvizit.setCena(200);
		rekvizit.setNaziv("Naziv 2");
		rekvizit.setOpis("jos Brutalnije");
		rekvizitService.save(rekvizit);
		
		
		Sala sala = new Sala( 10, 10, lokal);
		salaRepository.save(sala);
		lokal.setSale(new ArrayList<Sala>());
		lokal.getSale().add(sala);
		lokalService.save(lokal);
		
		
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat =
		        new SimpleDateFormat(pattern);

		izvedba.setNaslov(naslov);
		try {
			izvedba.setTermin(simpleDateFormat.parse("2018-09-09 03:05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		izvedba.setSala(sala);
		izvedba.setKarte(new ArrayList<Karta>());
		Random r = new Random();
		sala.setSedista(new ArrayList<Sediste>());
		Sediste s;
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				s = new Sediste();
				s.setRed(i);
				s.setKolona(j);
				
				sedisteRepository.save(s);
				sala.getSedista().add(s);
				Karta k = new Karta();
				
				k.setCena(200);
				k.setIzvedba(izvedba);
				
				if(r.nextInt(100)>90) {
					k.setTip("zauzeto");
				} else {
					k.setTip("slobodno");
				}
				k.setSediste(s);
				izvedba.getKarte().add(k);
				kartaService.save(k);
				
			}
			
		}

		salaRepository.save(sala);
		izvedbaRepository.save(izvedba);
		

		// podaci za pozoriste MARKO
			
			
		// administrator pozorista
		Korisnik adminPozorista = new Korisnik("adminPozoriste", "Marko", "Suhanov", "marko@m.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 645775434, "Novi sad");
		adminPozorista.getListaPrijatelja().add(korisnik2);
		korisnikService.save(adminPozorista);
				
	
		// administrator bioskopa
		Korisnik adminBioskopa = new Korisnik("adminBioskop", "Milan", "Suhanov", "milan@m.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 645775434, "Novi sad");
		adminBioskopa.getListaPrijatelja().add(korisnik1);
		korisnikService.save(adminBioskopa);
		
		
		// reziser i glumci
		Osoba reziser1 = new Osoba("Jim", "Jarmusch");
		Osoba glumac1 = new Osoba("Boris", "Milivojevic");
		Osoba glumac2 = new Osoba("Sergej", "Trifunovic");
		osobaService.save(reziser1);
		osobaService.save(glumac1);
		osobaService.save(glumac2);
//		
		List<Osoba> glumciZaMunje = new ArrayList<>();
//		
		glumciZaMunje.add(glumac1);
		glumciZaMunje.add(glumac2);
		Naslov film1 = new Naslov( "Munje", reziser1, glumciZaMunje, "Komedija", 132, 500, "Zivot mladih u Beogradu");
		naslovRepository.save(film1);
		Sala sala4 = new Sala(50, 30, lokal1);
		salaRepository.save(sala4);
		Izvedba zvezdaIzvedba = new Izvedba( sala4, new ArrayList<>(), new Date(), false, film1);
		izvedbaRepository.save(zvezdaIzvedba);
		ArrayList<Izvedba> izvedbeZvezda = new ArrayList<>();
		izvedbeZvezda.add(zvezdaIzvedba);
		Repertoar zvezdaRepe = new Repertoar(izvedbeZvezda);
		repertoarRepository.save(zvezdaRepe);
		List<Sala> zvezdaSale = new ArrayList<>();
		zvezdaSale.add(sala4);
		Lokal zvezda = new Lokal( false, zvezdaRepe, "ZVEZDA", "Beograd", "Pustanje starih filmova", zvezdaSale);
		lokalService.save(zvezda);

	
		
		
	

	}
}
