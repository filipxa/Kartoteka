package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import sw3.kartoteka.model.dto.PonudaDTO;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Ponuda;
import sw3.kartoteka.services.FileStorageService;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.OglasService;
import sw3.kartoteka.services.PonudaService;

@RestController
@RequestMapping(value = "/api/ponude")
public class PonudaController {
	@Autowired
	PonudaService ponudaService;
	
	@Autowired
	OglasService oglasService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	KorisnikService korisnikService;
	
	
	
	@GetMapping
	public ResponseEntity<List<PonudaDTO>> getAll(){
		List<Ponuda> ponude = ponudaService.findAll();
		List<PonudaDTO> pondeDTO = new ArrayList<>();
		for (Ponuda p : ponude) {
			pondeDTO.add(new PonudaDTO(p));
		}
		return new ResponseEntity<List<PonudaDTO>>(pondeDTO,HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = { "application/json" })
	public ResponseEntity<Void> save(@RequestBody PonudaDTO pDTO, HttpSession session) {
		// AKO SE BUDE KORISTILO TREBA PROVERITI ZA STA TREBA I PREPRAVITI rDTO u
		// Rekvizti
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		try {
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			Ponuda ponuda = new Ponuda();
			if (pDTO.getId() != -1) {
				ponuda = ponudaService.findOne(pDTO.getId());
				ponuda.setKorisnik(korisnikService.findOne(loggedId));
				ponuda.setOglas(oglasService.findOne(pDTO.getOglasID()));
				ponuda.setPrihvacena(pDTO.isPrihvacena());
				ponuda.setZavrsena(pDTO.isZavrsena());
				ponuda.setCena(pDTO.getCena());
			} else {
				ponuda.setKorisnik(korisnikService.findOne(loggedId));
				ponuda.setOglas(oglasService.findOne(pDTO.getOglasID()));
				ponuda.setCena(pDTO.getCena());
				ponuda.setPrihvacena(false);
				ponuda.setZavrsena(false);
			}
			System.out.println(ponuda.getKorisnik().getEmail());
			ponudaService.save(ponuda);
			System.out.println(ponuda);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
