package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Repertoar {
	@Column(nullable = false, unique = true, name = "repertoar_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column
	@OneToMany
	private List<Izvedba> listIzvedba;

	public List<Izvedba> getListaProjekcija() {
		return listIzvedba;
	}

	public void setListaProjekcija(List<Izvedba> listaIzvedba) {
		this.listIzvedba = listaIzvedba;
	}
}
