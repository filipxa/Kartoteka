package sw3.kartoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.entity.Sala;
import sw3.kartoteka.repository.SalaRepository;


@RequestMapping(value = "/api/sala")
@RestController
public class SalaController {

	@Autowired
	SalaRepository salaRepository;
	
	
	@PostMapping(value = "/update", consumes = { "application/json" })
	public ResponseEntity<?> saveSala(@RequestBody Sala sala){
		Sala stara = salaRepository.findById(sala.getIdSale()).get();
		stara.setBrRedova(sala.getBrRedova());
		stara.setBrKolona(sala.getBrKolona());
		salaRepository.save(stara);
		return ResponseEntity.ok().build();
	}
	
}
