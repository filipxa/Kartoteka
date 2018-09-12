package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.dto.UserDTO;
import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Sediste;
import sw3.kartoteka.services.IzvedbaService;
import sw3.kartoteka.services.KartaService;
import sw3.kartoteka.services.KorisnikService;

@RestController
@RequestMapping(value = "/api/karta")
public class KartaController {
	@Autowired
	KartaService kartaService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired 
	IzvedbaService izvedbaService;
	


	
	
	@GetMapping
	public ResponseEntity<List<Karta>> getAll(){
		List<Karta> karte = kartaService.findAll();
		return new ResponseEntity<>(karte, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<Karta>> getKarte( HttpSession session){
		Korisnik korisnik = (Korisnik)session.getAttribute("logged");
		
		try {
			List<Karta> karte = kartaService.findByKorisnik(korisnik); 
			
			return new ResponseEntity<>(karte, HttpStatus.OK);
		
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteKarta(@PathVariable("id") Integer id){
		try{
			Karta karta = kartaService.findOne(id);
			if(karta == null){throw new Exception();}
			kartaService.remove(karta);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
	}
	@GetMapping(value = "/cancel/{id}")
	public ResponseEntity<Void> cancelTicket(@PathVariable("id") Integer id){
		try{
			Karta karta = kartaService.findOne(id);
			if(karta == null){throw new Exception();}
			karta.setTip("slobodno");
			karta.setKorisnik(null);
			kartaService.save(karta);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		
	}
	
	@GetMapping(value="/izvedba/{id}")
	public ResponseEntity<?> getKarteByIzvedba(@PathVariable("id") Integer id){
		try {
			Izvedba izvedba = izvedbaService.findOne(id);
			List<Karta> karte = kartaService.findByIzvedba(izvedba);
			return new ResponseEntity<List<Karta>>(karte, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	@GetMapping(value="/getKartePopustuByNaslov/{idRepertoara}/{idNaslova}")
	public ResponseEntity<List<Karta>> getKarteNaPopustuZaNaslov(@PathVariable("id") Integer idRepertoara, @PathVariable("idNaslova") Integer idNaslova){
		List<Karta> karteNaPopustu = new ArrayList<>();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	@PostMapping(value = "/rez", consumes = { "application/json" })
	public ResponseEntity<?> reserveTicketes(@RequestBody RezTicketDTO dto, HttpSession session) {
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		
		try {
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			for(Sediste sediste : dto.seats) {
				Karta karta = kartaService.findbySediste(sediste);
				if (karta == null) {
					throw new Exception();
				}
				
				Integer userId = null;
				if(dto.friends.size()>0) {
					userId = dto.friends.get(0).getId();
					dto.friends.remove(0);
				} else {
					userId = loggedId;
				}
				
				karta.setTip("zauzeto");
				Korisnik k = korisnikService.findOne(userId);
				karta.setKorisnik(k);
				kartaService.save(karta);
				if(userId!= loggedId) {
					//TODO Poslati mail prijateljima
				}
				
			}
			//TODO poslati mail korisniku

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage() ,HttpStatus.BAD_REQUEST);
		}

	}
	
	
	private static class RezTicketDTO {
		private List<UserDTO> friends;
		private List<Sediste> seats;
		public List<UserDTO> getFriends() {
			return friends;
		}
		public void setFriends(List<UserDTO> friends) {
			this.friends = friends;
		}
		public List<Sediste> getSeats() {
			return seats;
		}
		public void setSeats(List<Sediste> seats) {
			this.seats = seats;
		}
		
	}
	
	
	private static class KartaDTO {
		
	}
	
	private static class KarteNaPopustuZaIzvedbu{
		
		private Izvedba izvedba;
		private List<Karta> karteNaPopustu;
		
		public Izvedba getIzvedba() {
			return izvedba;
		}
		public void setIzvedba(Izvedba izvedba) {
			this.izvedba = izvedba;
		}
		public List<Karta> getKarteNaPopustu() {
			return karteNaPopustu;
		}
		public void setKarteNaPopustu(List<Karta> karteNaPopustu) {
			this.karteNaPopustu = karteNaPopustu;
		}
		public KarteNaPopustuZaIzvedbu() {
			this.karteNaPopustu = new ArrayList<>();
		}
		public KarteNaPopustuZaIzvedbu(Izvedba izvedba, List<Karta> karteNaPopustu) {
			this.izvedba = izvedba;
			this.karteNaPopustu = karteNaPopustu;
		}
	}
	

}
