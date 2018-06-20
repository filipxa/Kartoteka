package sw3.kartoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Izvedba;


@Repository
public interface IzvedbaRepository extends JpaRepository<Izvedba, Integer>  {

}
