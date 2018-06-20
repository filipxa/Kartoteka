package sw3.kartoteka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.repository.LokalRepository;

@Service
public class LokalService {

	@Autowired
	LokalRepository lokalRepository;

	public List<Lokal> findAll() {
		return lokalRepository.findAll();
	}

	
	public Lokal findOne(Integer id) {
		// TODO Auto-generated method stub
		return lokalRepository.getOne(id);
	}

	
	public Lokal save(Lokal Lokal) {
		// TODO Auto-generated method stub
		return lokalRepository.save(Lokal);
	}

	
	public void delete(Lokal Lokal) {
		lokalRepository.delete(Lokal);

	}



}
