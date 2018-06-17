package sw3.kartoteka.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.jmx.LoadBalanceConnectionGroupManager;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Naslov;
import sw3.kartoteka.model.entity.Sala;
import sw3.kartoteka.services.KartaService;
import sw3.kartoteka.services.KorisnikService;

@RestController
@RequestMapping(value = "/api/karta")
public class KartaController {
	@Autowired
	KartaService kartaService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@GetMapping
	public ResponseEntity<List<Karta>> getAll(){
		List<Karta> karte = kartaService.findAll();
		return new ResponseEntity<>(karte, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<Karta>> getKarte(@PathVariable("id") Integer id){
		Korisnik korisnik = korisnikService.findOne(id);
		
		try {
			List<Karta> karte = kartaService.findByKorisnik(korisnik); 
			Karta k = new Karta();
			Izvedba izvedba = new Izvedba();
			Naslov naslov = new Naslov();
			Lokal l = new Lokal();
			Sala s = new Sala();
			s.setIdSale(1);
			s.setLokal(l);
			l.setId(1);
			l.setNaziv("Cineplexxx");
			
			naslov.setNaziv("Bolan");
			izvedba.setNaslov(naslov);
			izvedba.setTermin(new Date());
			izvedba.setSala(s);
			k.setIzvedba(izvedba);
			karte.add(k);
			k.setIdKarte(1);
			
			return new ResponseEntity<>(karte, HttpStatus.OK);
		
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id){
		try{
			Karta karta = kartaService.findOne(id);
			if(karta == null){throw new Exception();}
			kartaService.remove(karta);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
	}
	
	

}
