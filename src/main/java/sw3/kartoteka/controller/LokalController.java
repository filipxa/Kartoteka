package sw3.kartoteka.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.services.LokalService;


@RestController
@RequestMapping(value = "/api/lokal/") 
public class LokalController {
	
	@Autowired
	LokalService lokalService;
	
	
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
	

}