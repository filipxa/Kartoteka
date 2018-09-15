package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Repeat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;

import sw3.kartoteka.model.dto.RekvizitDto;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.model.entity.Repertoar;
import sw3.kartoteka.services.FileStorageService;
import sw3.kartoteka.services.KartaService;
import sw3.kartoteka.services.LokalService;


@RestController
@RequestMapping(value = "/api/lokal/") 
public class LokalController {
	
	@Autowired
	LokalService lokalService;
	
	@Autowired
	KartaService kartaService;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@GetMapping(value = "search/{term}")
	public ResponseEntity<List<Lokal>> searchLokali(@PathVariable("term") String term){
	 	List<Lokal> lokali = lokalService.findAll();
	 	lokali = lokali.stream().filter(t-> t.getNaziv().toLowerCase().contains(term.toLowerCase())).collect(Collectors.toList());
		return new ResponseEntity<>(lokali, HttpStatus.OK);
	}
	
	@GetMapping(value = "byId/{id}")
	public ResponseEntity<Lokal>getLokal(@PathVariable("id") String id){
		try {
			List<Lokal> lokali = lokalService.findAll();
			Lokal l = lokalService.findOne(Integer.parseInt(id));
			if(l==null) {
				throw new Exception();
			}
			return new ResponseEntity<Lokal>(l, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Lokal>( HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Void> save(@RequestPart(value="file", required = false) MultipartFile file, @RequestPart("lokal") Lokal inputLokal) {
		//AKO SE BUDE KORISTILO TREBA PROVERITI ZA STA TREBA I PREPRAVITI rDTO u Rekvizti
		try {
			Lokal lokal = new Lokal();
			if(inputLokal.getId()!= -1) {
				lokal = lokalService.findOne(inputLokal.getId());
				//OVDE treba napraviti ostatak ovo se moze koristiti kao izmena lokala
			}else {
				lokal.setNaziv(inputLokal.getNaziv());
				lokal.setIsPozoriste(inputLokal.isPozoriste());
				lokal.setPromotivniOpis(inputLokal.getPromotivniOpis());
				lokal.setAdresa(inputLokal.getAdresa());
			}
			
		
			Lokal lokalSave = lokalService.save(lokal);
			System.out.println(lokalSave.getNaziv());
			if(file!=null && !file.isEmpty()) {
				String fileName = fileStorageService.storeFile(file,"lokal",lokalSave.getId().toString());
				System.out.println(fileName);
			}
			
			
			
	        // Try to determine file's content type
	        
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value="karta/{id}")
	public ResponseEntity<Lokal> getLokalByKartaId(@PathVariable("id") Integer id){
		Karta karta = kartaService.findOne(id);
		return new ResponseEntity<Lokal>(karta.getIzvedba().getSala().getLokal(),HttpStatus.OK);
	}
	
	@GetMapping(value = "cinemas")
	public ResponseEntity<List<Lokal>>getCinemas(){
		List<Lokal> all = lokalService.findAll();
		List<Lokal> cinemas = new ArrayList<>();
		for (Lokal lokal : all) {
			if(!lokal.isPozoriste()) {
				cinemas.add(lokal);
			}
		}
		return new ResponseEntity<>(cinemas, HttpStatus.OK);
	}
	
	@GetMapping(value = "theatres")
	public ResponseEntity<List<Lokal>>getTheatres(){
		List<Lokal> all = lokalService.findAll();
		List<Lokal> theatres = new ArrayList<>();
		for (Lokal lokal : all) {
			if(lokal.isPozoriste()) {
				theatres.add(lokal);
			}
		}
		return new ResponseEntity<>(theatres, HttpStatus.OK);
	}
	
	
	
	

}
