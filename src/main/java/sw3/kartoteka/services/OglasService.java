package sw3.kartoteka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Oglas;
import sw3.kartoteka.repository.OglasRepository;

@Service
public class OglasService {
	@Autowired
	OglasRepository oglasRepository;
	
	public List<Oglas> findAll() {
		return oglasRepository.findAll();
	}


	public Oglas findOne(Integer id) {
		// TODO Auto-generated method stub
		return oglasRepository.getOne(id);
	}


	public Oglas save(Oglas oglas) {
		// TODO Auto-generated method stub
		return oglasRepository.save(oglas);
	}

	public void delete(Oglas oglas) {
		oglasRepository.delete(oglas);
		
	}
}
