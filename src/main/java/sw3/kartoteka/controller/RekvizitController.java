package sw3.kartoteka.controller;

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
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.services.RekvizitService;

@RestController
@RequestMapping(value = "/api/rekvizti")
public class RekvizitController {
	@Autowired
	RekvizitService rekvizitService;
	
	@GetMapping
	public ResponseEntity<List<Rekvizit>> getAll(){
		List<Rekvizit> rekviziti = rekvizitService.findAll();
		return new ResponseEntity<List<Rekvizit>>(rekviziti, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Rekvizit> getOne(@PathVariable("id") Integer id){
		try {
			Rekvizit rekvizit = rekvizitService.findOne(id);
			return new ResponseEntity<Rekvizit>(rekvizit, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Void> save(@RequestBody Rekvizit rekvizit) {
		try {
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
