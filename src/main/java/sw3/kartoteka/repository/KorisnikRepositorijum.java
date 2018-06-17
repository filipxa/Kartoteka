package sw3.kartoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sw3.kartoteka.model.entity.Korisnik;

public interface KorisnikRepositorijum extends JpaRepository<Korisnik, Integer>  {

	
	Korisnik findByEmail(String email);

	Korisnik findByuuid(String uuid);
}
