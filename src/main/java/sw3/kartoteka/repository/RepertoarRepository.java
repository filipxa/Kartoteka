package sw3.kartoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sw3.kartoteka.model.entity.Repertoar;
@Repository
public interface RepertoarRepository extends JpaRepository<Repertoar, Integer>  {

}
