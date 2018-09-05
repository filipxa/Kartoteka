package sw3.kartoteka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Rekvizit;
import sw3.kartoteka.repository.RekvizitRepository;

@Service
public class RekvizitService {
	@Autowired
	RekvizitRepository rekvizitRepository;
	
	public List<Rekvizit> findAll() {
		return rekvizitRepository.findAll();
	}


	public Rekvizit findOne(Integer id) {
		// TODO Auto-generated method stub
		return rekvizitRepository.getOne(id);
	}


	public Rekvizit save(Rekvizit rekvizit) {
		// TODO Auto-generated method stub
		return rekvizitRepository.save(rekvizit);
	}

	public void delete(Rekvizit rekvizit) {
		rekvizitRepository.delete(rekvizit);
		
	}
	
}
