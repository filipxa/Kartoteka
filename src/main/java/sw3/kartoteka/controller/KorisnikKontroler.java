package sw3.kartoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.Korisnik;
import sw3.kartoteka.services.KorisnikService;

@RestController
@RequestMapping(value = "/api")
public class KorisnikKontroler {

	@Autowired
	KorisnikService korisnikService;
	
	@GetMapping
	public ResponseEntity<List<Korisnik>> getAll(){
		List<Korisnik> korisnici = korisnikService.findAll();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
	
}
