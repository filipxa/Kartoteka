package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Naslov;
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
@RestController
@RequestMapping(value = "/api/dummy")
public class DummyDataController {
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
	
	
	@GetMapping()
	public ResponseEntity<Korisnik> getKarte(){
		Korisnik korisnik1 = new Korisnik( "korisnik", "Filip", "Micic", "filipxa@hotmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik1);
		Korisnik korisnik = new Korisnik( "korisnik", "Pera", "Peric", "filipxa@gmail.com", "123", new ArrayList<Korisnik>(), "131232131", true, 0, 63666458, "Beograd");
		korisnikService.save(korisnik);
		korisnik = korisnikService.findByEmail("filipxa@hotmail.com");
		korisnik1 = korisnikService.findByEmail("filipxa@gmail.com");
		
		korisnik1.getListaPrijatelja().add(korisnik);
		korisnik.getListaPrijatelja().add(korisnik1);
		korisnikService.save(korisnik1);
		korisnikService.save(korisnik);
		
		
		
		korisnikService.save(korisnik1);
		
		Izvedba izvedba = new Izvedba();
		izvedbaRepository.save(izvedba);

		Naslov naslov = new Naslov();
		
		naslov.setNaziv("Leave No Trace (2018)");
		naslovRepository.save(naslov);
		
		Repertoar rep = new Repertoar();
		rep.setIzvedbe(new ArrayList<Izvedba>());
		rep.getIzvedbe().add(izvedba);
		repertoarRepository.save(rep);
		

		Lokal lokal = new Lokal();
		lokal.setNaziv("Cineplexxx");
		lokal.setIsPozoriste(false);
		lokal.setRepertoar(rep);
		
		lokalService.save(lokal);
		
		
		Sala sala = new Sala( 10, 10, lokal);
		salaRepository.save(sala);
		lokal.setSale(new ArrayList<Sala>());
		lokal.getSale().add(sala);
		lokalService.save(lokal);
		

		izvedba.setNaslov(naslov);
		izvedba.setTermin(new Date());
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
				kartaService.save(k);
				k.setCena(200);
				k.setIzvedba(izvedba);
				
				if(r.nextInt(100)>90) {
					k.setTip("zauzeto");
				} else {
					k.setTip("slobodno");
				}
				k.setSediste(s);
				
				izvedba.getKarte().add(k);
				
			}
			
		}

		salaRepository.save(sala);
		izvedbaRepository.save(izvedba);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
