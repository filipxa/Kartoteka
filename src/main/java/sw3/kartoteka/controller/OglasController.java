package sw3.kartoteka.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sw3.kartoteka.model.dto.OglasDTO;
import sw3.kartoteka.model.dto.UserDTO;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Oglas;
import sw3.kartoteka.services.FileStorageService;
import sw3.kartoteka.services.KorisnikService;
import sw3.kartoteka.services.OglasService;

@RestController
@RequestMapping(value = "/api/oglasi")
public class OglasController {

	@Autowired
	OglasService oglasService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping
	public ResponseEntity<List<OglasDTO>> getAll() {
		List<Oglas> oglasi = oglasService.findAll();
		List<OglasDTO> oglasiDTO = new ArrayList();
		for (Oglas o : oglasi) {
			oglasiDTO.add(new OglasDTO(o));
		}
		return new ResponseEntity<List<OglasDTO>>(oglasiDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<OglasDTO> getOne(@PathVariable("id") Integer id) {
		try {
			Oglas oglas = oglasService.findOne(id);
			OglasDTO oDTO = new OglasDTO(oglas);
			return new ResponseEntity<OglasDTO>(oDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping()
	public ResponseEntity<Void> save(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart("oglas") OglasDTO oDTO) {
		// AKO SE BUDE KORISTILO TREBA PROVERITI ZA STA TREBA I PREPRAVITI rDTO u
		// Rekvizti
		try {
			Oglas oglas = new Oglas();
			if (oDTO.getIdOglasa() != -1) {
				oglas = oglasService.findOne(oDTO.getIdOglasa());
				oglas.setDatum(new Date(oDTO.getDatum()));// DATUM tu moze biti frkice treba ce vrv neko kastovanje
				oglas.setNaziv(oDTO.getNaziv());
				oglas.setOpis(oDTO.getOpis());
				oglas.setOdobren(oDTO.isOdobren());
				oglas.setProdat(oDTO.isProdat());
				
			} else {
				oglas.setKorisnik(korisnikService.findOne(oDTO.getPostavioID()));
				oglas.setDatum(new Date(oDTO.getDatum()));
				oglas.setNaziv(oDTO.getNaziv());
				oglas.setOpis(oDTO.getOpis());
				oglas.setOdobren(false);
				oglas.setProdat(false);
			}

			oglasService.save(oglas);
			System.out.println(oglas);
			if (file != null && !file.isEmpty()) {
				String fileName = fileStorageService.storeFile(file, "oglasi", oglas.getIdOglasa().toString());
				System.out.println(fileName);
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// kopirano sa Rekvizita treba testirati sve funkcionalnosti
	@GetMapping(value = "/accept/{id}")
	public ResponseEntity<Void> book(@PathVariable("id") Integer id) {
		// tu sranje treba ispisati
		System.out.println("BOOK");
		try {
			Oglas oglas = oglasService.findOne(id);

			oglas.setOdobren(true);

			oglasService.save(oglas);
			System.out.println(oglas.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		try {
			Oglas oglas = oglasService.findOne(id);

			fileStorageService.deleteFile("oglasi", id.toString());
			oglasService.delete(oglas);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}
}
