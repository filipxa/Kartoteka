package sw3.kartoteka.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import sw3.kartoteka.model.dto.RekvizitDto;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.repository.RekvizitRepository;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.RekvizitService;

@RestController
@RequestMapping(value = "/api/rekvizti")
public class RekvizitController {
	@Autowired
	RekvizitService rekvizitService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	KorisnikService korisnikService;
	
	@GetMapping
	public ResponseEntity<List<RekvizitDto>> getAll(){
		List<Rekvizit> rekviziti = rekvizitService.findAll();
		List<RekvizitDto> rekviztitiDTO = new ArrayList();
		for (Rekvizit r : rekviziti) {
			rekviztitiDTO.add(new RekvizitDto(r));
		}
		return new ResponseEntity<List<RekvizitDto>>(rekviztitiDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RekvizitDto> getOne(@PathVariable("id") Integer id){
		try {
			Rekvizit rekvizit = rekvizitService.findOne(id);
			RekvizitDto rDTO = new RekvizitDto(rekvizit);
			return new ResponseEntity<RekvizitDto>(rDTO, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Void> save(@RequestBody RekvizitDto rDTO) {
		//AKO SE BUDE KORISTILO TREBA PROVERITI ZA STA TREBA I PREPRAVITI rDTO u Rekvizti
		try {
			Rekvizit rekvizit = new Rekvizit();
			if(rDTO.getIdRekvizita()!= -1) {
				rekvizit = rekvizitService.findOne(rDTO.getIdRekvizita());
				rekvizit.setCena(rDTO.getCena());
				rekvizit.setNaziv(rDTO.getNaziv());
				rekvizit.setOpis(rDTO.getOpis());
			}else {
				rekvizit.setCena(rDTO.getCena());
				rekvizit.setNaziv(rDTO.getNaziv());
				rekvizit.setOpis(rDTO.getOpis());
			}
			
		
			rekvizitService.save(rekvizit);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping(value = "/book", consumes = "application/json")
	public ResponseEntity<Void> book(@RequestBody RekvizitDto rDTO) {
		//tu sranje treba ispisati
		try {
			Rekvizit rekvizit = rekvizitService.findOne(rDTO.getIdRekvizita());
			
			Korisnik k = new Korisnik();
			Korisnik proba = (Korisnik)request.getSession().getAttribute("logged");
			System.out.println("Proba " + proba);
			System.out.println(request.getSession().getAttribute("logged"));
			k = (Korisnik)request.getSession().getAttribute("logged");
			System.out.println(request.getSession().getAttribute("logged"));
			System.out.println(k);
			System.out.println(rekvizit);
			rekvizit.setKorisnik(k);
			
			rekvizitService.save(rekvizit);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		try{
			Rekvizit rekvizit = rekvizitService.findOne(id);
			rekvizitService.delete(rekvizit);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
