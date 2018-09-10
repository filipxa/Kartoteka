package sw3.kartoteka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Oglas;
import sw3.kartoteka.model.entity.Ponuda;
import sw3.kartoteka.repository.PonudaRepository;

@Service
public class PonudaService {
	@Autowired
	PonudaRepository ponudaRepository;

	public List<Ponuda> findAll(){
		return ponudaRepository.findAll();
	}
	
	public Ponuda findOne(Integer id) {
		// TODO Auto-generated method stub
		return ponudaRepository.getOne(id);
	}


	public Ponuda save(Ponuda ponuda) {
		// TODO Auto-generated method stub
		return ponudaRepository.save(ponuda);
	}

	public void delete(Ponuda ponuda) {
		ponudaRepository.delete(ponuda);
	}
	
	public List<Ponuda> findByKorisnik(Korisnik korisnik) {
		return ponudaRepository.findByKorisnik(korisnik);
	}
	
	public List<Ponuda> findByOglas(Oglas oglas) {
		return ponudaRepository.findByOglas(oglas);
	}
}
