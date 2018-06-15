package sw3.kartoteka.services;

import java.util.List;

import sw3.kartoteka.model.Korisnik;

public interface KorisnikServisInterface {

	List<Korisnik> findAll();
	
	Korisnik findOne(Integer id);
	
	Korisnik save(Korisnik korisnik);
	
	void delete(Korisnik korisnik);
	
	Korisnik findByEmail(String email);
	
	Korisnik findByuuid(String uuid);
}
