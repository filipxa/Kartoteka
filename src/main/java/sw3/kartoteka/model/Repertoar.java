package sw3.kartoteka.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Repertoar {

	@Column
	private List<Projekcija> listaProjekcija;

	public List<Projekcija> getListaProjekcija() {
		return listaProjekcija;
	}

	public void setListaProjekcija(List<Projekcija> listaProjekcija) {
		this.listaProjekcija = listaProjekcija;
	}
}
