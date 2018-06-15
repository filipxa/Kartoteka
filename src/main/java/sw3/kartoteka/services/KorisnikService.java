package sw3.kartoteka.services;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.Korisnik;
import sw3.kartoteka.repository.KorisnikRepositorijum;

@Service
public class KorisnikService implements KorisnikServisInterface {

	@Autowired
	KorisnikRepositorijum korisnikRepositorijum;

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepositorijum.findAll();
	}

	@Override
	public Korisnik findOne(Integer id) {
		// TODO Auto-generated method stub
		return korisnikRepositorijum.getOne(id);
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return korisnikRepositorijum.save(korisnik);
	}

	@Override
	public void delete(Korisnik korisnik) {
		korisnikRepositorijum.delete(korisnik);

	}
	@Override
	public Korisnik findByEmail(String email) {
		return korisnikRepositorijum.findByEmail(email);
	}

	@Override
	public Korisnik findByuuid(String uuid) {
		return korisnikRepositorijum.findByuuid(uuid);
	}

}
