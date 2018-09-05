package sw3.kartoteka.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.dto.UserDTO;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Sediste;
import sw3.kartoteka.repository.KorisnikRepositorijum;
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
	
	@PostMapping(value = "/rez", consumes = { "application/json" })
	public ResponseEntity<Void> reserveTicketes(@RequestBody RezTicketDTO dto) {
		try {
			for(Sediste sediste : dto.seats) {
				Karta karta = kartaService.findbySediste(sediste);
				if (karta == null) {
					throw new Exception();
				}
				
				UserDTO toSet = null;
				if(dto.friends.size()>0) {
					toSet = dto.friends.get(0);
					dto.friends.remove(0);
				} else {
					toSet = dto.logged;
				}
				karta.setTip("zauzeto");
				Korisnik k = korisnikService.findOne(toSet.getId());
				karta.setKorisnik(k);
				kartaService.save(karta);
				if(toSet!= dto.logged) {
					//TODO Poslati mail prijateljima
				}
				
			}
			//TODO poslati mail korisniku

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	private static class RezTicketDTO {
		private List<UserDTO> friends;
		private List<Sediste> seats;
		private UserDTO logged;
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
		public UserDTO getLogged() {
			return logged;
		}
		public void setLogged(UserDTO logged) {
			this.logged = logged;
		}
		
	}
	

}
