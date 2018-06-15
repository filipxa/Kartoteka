package sw3.kartoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw3.kartoteka.model.Korisnik;
import sw3.kartoteka.model.dto.UserDTO;
import sw3.kartoteka.services.EMailService;
import sw3.kartoteka.services.KorisnikService;

@RestController
@RequestMapping(value = "/api/user")
public class KorisnikKontroler {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	EMailService emailService;
	
	@GetMapping
	public ResponseEntity<List<Korisnik>> getAll(){
		List<Korisnik> korisnici = korisnikService.findAll();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
	
	
	// GET SINGLE USER
	@GetMapping(value = "/{email}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("email") String email){
		try {
		Korisnik user = korisnikService.findByEmail(email);
		if(user == null){throw new Exception();}
		UserDTO userDTO = new UserDTO(user);
		return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
		
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.ACCEPTED);
		}
	}
	
	@PostMapping(value = "/register",consumes="application/json")
	public ResponseEntity<String> register( @RequestBody UserDTO userDTO){
		Korisnik user = korisnikService.findByEmail(userDTO.getEmail());
		try {
			if(user != null) {
				return new ResponseEntity<String>("\"email\"",HttpStatus.ACCEPTED);
			}
			if( userDTO.getName() == null || userDTO.getPassword() == null){
				throw new Exception();
			}
			Korisnik newUser = new Korisnik();
			newUser.setIme(userDTO.getName());
			newUser.setPassword(userDTO.getPassword());
			newUser.setEmail(userDTO.getEmail());
			newUser.setPrezime(userDTO.getlName());
			newUser.setTip(userDTO.getTip());
			newUser = korisnikService.save(newUser);
			
			try {
				emailService.sendMail(newUser,"Activation link", "Please follow link below to activate \nhttp://localhost:8080/api/user/activate/"+newUser.getUuid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<String>("\"succes\"",HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("\"bad\"" ,HttpStatus.ACCEPTED);
		}
		
	}
	
	@GetMapping(value = "/activate/{uuid}")
	public String activateUser(@PathVariable("uuid") String uuid) {
		Korisnik user = korisnikService.findByuuid(uuid);
		if(user!=null) {
			if(!user.getActivated()) {
				user.setActivated(true);
				return String.format("<p>Succesfully activated! <p> <p>%s welcome to site!<p>", user.getIme());
			} else {
				return "User allready activated!";
			}
		}
		return "Bad activation link!";
	}

	
	
}
