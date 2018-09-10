package sw3.kartoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Oglas;
import sw3.kartoteka.model.entity.Ponuda;

@Repository
public interface PonudaRepository extends JpaRepository<Ponuda, Integer>{
	List<Ponuda> findByKorisnik(Korisnik korisnik);
	
	List<Ponuda> findByOglas(Oglas oglas);
}
