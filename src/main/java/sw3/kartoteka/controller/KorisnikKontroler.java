package sw3.kartoteka.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sw3.kartoteka.model.dto.UserDTO;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Sediste;
import sw3.kartoteka.services.EMailService;
import sw3.kartoteka.services.KorisnikService;

@RestController
@RequestMapping(value = "/api/user")
public class KorisnikKontroler {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	EMailService emailService;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping
	public ResponseEntity<List<Korisnik>> getAll(){
		List<Korisnik> korisnici = korisnikService.findAll();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
	
	@GetMapping(value = "/friends/{email}")
	public ResponseEntity<List<UserDTO>> getAll(@PathVariable("email") String email){
		List<Korisnik> korisnici = korisnikService.findAll();
		try {
			Korisnik user = korisnikService.findByEmail(email);
			if(user == null){throw new Exception();}
			List<UserDTO> rets = new ArrayList<UserDTO>();
			
			for(Korisnik friend : user.getListaPrijatelja()) {
				rets.add(new UserDTO(friend));
			}

			return new ResponseEntity<List<UserDTO>> (rets,HttpStatus.OK);
			
			}catch (Exception e) {
				return new ResponseEntity<List<UserDTO>>(HttpStatus.ACCEPTED);
			}
		
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
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
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
			newUser.setAdresa(userDTO.getAdresa());
			newUser.setBrTelefona(userDTO.getTel());
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
				korisnikService.save(user);
				return String.format("<p>Succesfully activated! <p> <p>%s welcome to site!<p>", user.getIme());
			} else {
				return "User allready activated!";
			}
		}
		return "Bad activation link!";
	}
	
	@PostMapping(value="logged", consumes="application/json")
	public ResponseEntity setLoggedIn(@RequestBody LoginDTO loginInfo ){
		Korisnik korisnik = korisnikService.findByEmail(loginInfo.username);
		if(korisnik==null || !korisnik.getPassword().equals(loginInfo.password)) {
			return ResponseEntity.badRequest().body("Bad login");
		} 
		if(!korisnik.getActivated()) {
			return ResponseEntity.badRequest().body("Follow link in email to activated your account.");
		}
		request.getSession().setAttribute("logged", korisnik);
		
		
		return new ResponseEntity<UserDTO>(new UserDTO(korisnik), HttpStatus.OK);
	}
	
	@GetMapping(value="logged")
	public ResponseEntity<UserDTO> getLoggedIn(){
		Korisnik proba = (Korisnik)request.getSession().getAttribute("logged");
		System.out.println(proba.getEmail());
		return new ResponseEntity<UserDTO>(new UserDTO(proba), HttpStatus.OK);
	}

	private static class LoginDTO
	{
		private String username;
		private String password;
		
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	
	
}
