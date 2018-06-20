package sw3.kartoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;



@Repository
public interface LokalRepository extends JpaRepository<Lokal, Integer>  {
	

}
