package sw3.kartoteka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.repository.IzvedbaRepository;

@Service
public class IzvedbaService  {
	@Autowired
	IzvedbaRepository izvedbaRepository;
	public Izvedba findOne(Integer id) {
		Optional<Izvedba> iz= izvedbaRepository.findById(id);
		return iz.get();
	}
	
}
