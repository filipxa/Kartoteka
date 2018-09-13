package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


import sw3.kartoteka.model.dto.PonudaDTO;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Oglas;
import sw3.kartoteka.model.entity.Ponuda;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.services.EMailService;
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
	
	@Autowired
	EMailService emailService;
	
	
	@GetMapping
	public ResponseEntity<List<PonudaDTO>> getAll(){
		List<Ponuda> ponude = ponudaService.findAll();
		List<PonudaDTO> pondeDTO = new ArrayList<>();
		for (Ponuda p : ponude) {
			pondeDTO.add(new PonudaDTO(p));
		}
		return new ResponseEntity<List<PonudaDTO>>(pondeDTO,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PonudaDTO> getOneForUser(@PathVariable("id") Integer oglasID,HttpSession session){
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		try {
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			List<Ponuda> ponudeKorisnika = ponudaService.findByKorisnik(korisnikService.findOne(loggedId));
			PonudaDTO pDTO = new PonudaDTO();
			for (Ponuda ponuda : ponudeKorisnika) {
				if(ponuda.getOglas().getIdOglasa()==oglasID) {
					pDTO = new PonudaDTO(ponuda);
					System.out.println("Vracena: "+ponuda);
					return new ResponseEntity<PonudaDTO>(pDTO,HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/oglas/{id}")
	public ResponseEntity<List<PonudaDTO>> getAllFromPost(@PathVariable("id") Integer oglasID,HttpSession session){
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		try {
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			Korisnik korisnik = korisnikService.findOne(loggedId);
			Oglas oglas = oglasService.findOne(oglasID);
			
			if(oglas.getKorisnik()!=korisnik) {
				throw new Exception("Not authorized.");
			}
			
			List<Ponuda> ponudeOglasa = ponudaService.findByOglas(oglas);
			List<PonudaDTO> peDTO = new ArrayList<>();
			for (Ponuda ponuda : ponudeOglasa) {
				peDTO.add(new PonudaDTO(ponuda));
				System.out.println("Vracena: "+ponuda);
			}
			return new ResponseEntity<List<PonudaDTO>>(peDTO,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/accept/{id}")
	public ResponseEntity<Void> accept(@PathVariable("id") Integer ponudaID,HttpSession session){
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		//izmeniti i sacuvati oglas i ponude!!!
		try {
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			Korisnik korisnik = korisnikService.findOne(loggedId);
			Ponuda ponuda = ponudaService.findOne(ponudaID);

			if(ponuda.getOglas().getKorisnik()!=korisnik) {
				throw new Exception("Not authorized.");
			}
			
			List<Ponuda> ponudeOglasa = ponudaService.findByOglas(ponuda.getOglas());
			for (Ponuda p : ponudeOglasa) {
				if(p.getId() == ponuda.getId()) {
					p.setPrihvacena(true);
					p.setZavrsena(true);
					System.out.println("Prihvacena: "+p);
					emailService.sendMail(p.getKorisnik(), "Offer accepted",
							"Offer name: "+ p.getOglas().getNaziv()+"\n"+
							"Offer description: "+ p.getOglas().getOpis()+"\n"+
							"Offer price: "+ p.getCena().toString()+"\n"+
							"User email: "+ korisnik.getEmail()+"\n");
				}
				else {
					p.setPrihvacena(false);
					p.setZavrsena(true);
					System.out.println("Odbijena: "+p);
					emailService.sendMail(p.getKorisnik(), "Offer denied",
							"Offer name: "+ p.getOglas().getNaziv()+"\n"+
							"Offer description: "+ p.getOglas().getOpis()+"\n"+
							"Offer price: "+ p.getCena().toString()+"\n");
				}
				ponudaService.save(p);
			}
			ponuda.getOglas().setProdat(true);
			oglasService.save(ponuda.getOglas());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id,HttpSession session){
		Integer loggedId = ((Korisnik)session.getAttribute("logged")).getIdKorisnika();
		try{
			if(loggedId==null) {
				throw new Exception("You must be logged in.");
			}
			List<Ponuda> ponudeKorisnika = ponudaService.findByKorisnik(korisnikService.findOne(loggedId));
			
			for (Ponuda ponuda : ponudeKorisnika) {
				if(ponuda.getOglas().getIdOglasa()==id) {
					System.out.println("Obrisana: "+ponuda);
					ponudaService.delete(ponuda);
				}
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
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
				ponuda.setId(pDTO.getId());
				ponuda.setKorisnik(korisnikService.findOne(loggedId));
				ponuda.setOglas(oglasService.findOne(pDTO.getOglasID()));
				ponuda.setCena(pDTO.getCena());
				ponuda.setPrihvacena(false);
				ponuda.setZavrsena(false);
			}
			System.out.println(ponuda.getKorisnik().getEmail());
			ponudaService.save(ponuda);
			System.out.println(ponudaService.findAll());

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
