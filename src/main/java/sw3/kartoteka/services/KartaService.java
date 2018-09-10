package sw3.kartoteka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Izvedba;
import sw3.kartoteka.model.entity.Karta;
import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;
import sw3.kartoteka.model.entity.Sediste;
import sw3.kartoteka.repository.KartaRepository;


@Service
public class KartaService {
	@Autowired
	KartaRepository kartaRepository;
	
	public List<Karta> findAll() {
		return kartaRepository.findAll();
	}


	public Karta findOne(Integer id) {
		
		return kartaRepository.findById(id).get();
	}


	public Karta save(Karta karta) {
		// TODO Auto-generated method stub
		return kartaRepository.save(karta);
	}

	public void delete(Karta karta) {
		kartaRepository.delete(karta);

	}
	
	public List<Karta> findByKorisnik(Korisnik korisnik) {
		return kartaRepository.findByKorisnik(korisnik);
	}
	
	public List<Karta> findByIzvedba(Izvedba izvedba){
		return kartaRepository.findByIzvedba(izvedba);
	}
	
	public Karta findbySediste(Sediste sediste) {
		return kartaRepository.findBySediste(sediste);
	}


	public void remove(Karta karta) {
		kartaRepository.delete(karta);
		
	}

}
