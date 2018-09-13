package sw3.kartoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Sediste;


public interface KartaRepository extends JpaRepository<Karta, Integer>  {

	List<Karta> findByKorisnik(Korisnik korisnik);

	Karta findBySediste(Sediste sediste);


	List<Karta> findByIzvedba(Izvedba izvedba);
}
