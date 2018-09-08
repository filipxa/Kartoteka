package sw3.kartoteka.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sw3.kartoteka.model.dto.RekvizitDto;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.repository.RekvizitRepository;
import sw3.kartoteka.services.FileStorageService;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.RekvizitService;

@RestController
@RequestMapping(value = "/api/rekviziti")
public class RekvizitController {
	

	@Autowired
	RekvizitService rekvizitService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
    private FileStorageService fileStorageService;
	
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
	
	@PostMapping()
	public ResponseEntity<Void> save(@RequestPart(value="file", required = false) MultipartFile file, @RequestPart("rekvizit") RekvizitDto rDTO) {
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
			
			if(file!=null && !file.isEmpty()) {
				String fileName = fileStorageService.storeFile(file,"rekviziti",rekvizit.getIdRekvizita().toString());
				System.out.println(fileName);
			}
			
			
			
	        // Try to determine file's content type
	        
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping(value = "/book", consumes = "application/json")
	public ResponseEntity<Void> book(@RequestBody RekvizitDto rDTO) {
		//tu sranje treba ispisati
		System.out.println("BOOK");
		try {
			Rekvizit rekvizit = rekvizitService.findOne(rDTO.getIdRekvizita());
			
			Korisnik k = (Korisnik)request.getSession().getAttribute("logged");
			Korisnik korisnikPravi = korisnikService.findByEmail(k.getEmail());
			
			rekvizit.setKorisnik(korisnikPravi);
			
			rekvizitService.save(rekvizit);
			System.out.println(rekvizit.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	
	public static class Pera{
		String user;
		String pass;
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		
	}
	
}
