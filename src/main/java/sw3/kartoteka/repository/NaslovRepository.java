package sw3.kartoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sw3.kartoteka.model.entity.Naslov;
@Repository
public interface NaslovRepository extends JpaRepository<Naslov, Integer>  {

}
