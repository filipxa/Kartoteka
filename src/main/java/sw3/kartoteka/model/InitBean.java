package sw3.kartoteka.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Naslov;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.model.entity.Repertoar;
import sw3.kartoteka.model.entity.Sala;
import sw3.kartoteka.model.entity.Sediste;
import sw3.kartoteka.repository.IzvedbaRepository;
import sw3.kartoteka.repository.NaslovRepository;
import sw3.kartoteka.repository.RepertoarRepository;
import sw3.kartoteka.repository.SalaRepository;
import sw3.kartoteka.repository.SedisteRepository;
import sw3.kartoteka.services.KartaService;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.LokalService;
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
	
	
	public void fillData(){
		Korisnik korisnik1 = new Korisnik( "korisnik", "Filip", "Micic", "filipxa@hotmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik1);
		Korisnik korisnik = new Korisnik( "korisnik", "Pera", "Peric", "filipxa@gmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik);
		Korisnik korisnik2 = new Korisnik( "fan", "Filip", "Micic", "a@a.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik2);
		
		korisnik1.getListaPrijatelja().add(korisnik);
		korisnik.getListaPrijatelja().add(korisnik1);
		korisnikService.save(korisnik1);
		korisnikService.save(korisnik);

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
		lokal.setRepertoar(rep);
		
		lokalService.save(lokal);
		
		Lokal lokal1 = new Lokal();
		lokal1.setNaziv("Bioskop1");
		lokal1.setIsPozoriste(false);
		lokal1.setRepertoar(rep);
		
		lokalService.save(lokal1);
		
		Lokal lokal2 = new Lokal();
		lokal2.setNaziv("Bioskop2");
		lokal2.setIsPozoriste(false);
		lokal2.setRepertoar(rep);
		
		lokalService.save(lokal2);
		
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
		
		

	}
}