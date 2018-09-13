package sw3.kartoteka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Osoba;
import sw3.kartoteka.repository.OsobaRepository;

@Service

public class OsobaService {
	@Autowired
	OsobaRepository osobaRepository;
	
	public void save(Osoba osoba) {
		osobaRepository.save(osoba);
	}
	
	
}
