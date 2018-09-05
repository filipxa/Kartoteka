package sw3.kartoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sw3.kartoteka.model.entity.Naslov;
import sw3.kartoteka.model.entity.Rekvizit;

public interface RekvizitRepository extends JpaRepository<Rekvizit, Integer> {

}
