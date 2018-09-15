package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.dto.RekvizitDto;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.model.entity.Skala;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.SkalaService;

@RestController
@RequestMapping(value = "/api/skala")
public class SkalaController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	SkalaService skalaService;
	
	
	@GetMapping
	public ResponseEntity<?> get(){
		try {
			Integer id = 1;
			Skala skala = skalaService.findOne(id);
			List<Skala> skale = skalaService.findAll();
			for (Skala skala2 : skale) {
				skala = skala2;
			}
			
			return new ResponseEntity<Skala>(skala, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping(consumes = { "application/json" })
	public ResponseEntity<?> save(@RequestBody Skala skala, HttpSession session) {
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		StringBuilder sb = new StringBuilder("");
		try {
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			Korisnik k = new Korisnik();
			k = korisnikService.findOne(loggedId);
//			if(k.getTip()!="root"){
//				throw new Exception("You must be logged as system admin.");
//			}
			Skala newSkala = new Skala( skala.getBronzaGranica(), skala.getBronzaPopust(),
										skala.getSrebroGranica(), skala.getSrebroPopust(),
										skala.getZlatoGranica(), skala.getZlatoPopust());
			skalaService.save(newSkala);
			System.out.println(newSkala);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage() ,HttpStatus.BAD_REQUEST);
		}
	}
}
