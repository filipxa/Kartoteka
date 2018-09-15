package sw3.kartoteka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.model.entity.Skala;
import sw3.kartoteka.repository.SkalaRepository;

@Service
public class SkalaService {
	@Autowired
	SkalaRepository skalaRepository;
	
	public List<Skala> findAll() {
		return skalaRepository.findAll();
	}


	public Skala findOne(Integer id) {
		// TODO Auto-generated method stub
		return skalaRepository.getOne(id);
	}


	public Skala save(Skala skala) {
		// TODO Auto-generated method stub
		return skalaRepository.save(skala);
	}

	public void delete(Skala skala) {
		skalaRepository.delete(skala);
		
	}
}
