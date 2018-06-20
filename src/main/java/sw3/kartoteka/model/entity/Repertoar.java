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
	public Repertoar(Integer id, List<Izvedba> listIzvedba) {
		super();
		this.id = id;
		this.listIzvedba = listIzvedba;
	}
	
	public Repertoar() {
		// TODO Auto-generated constructor stub
	}

	@Column(nullable = false, unique = true, name = "repertoar_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	@OneToMany
	private List<Izvedba> listIzvedba;

	public List<Izvedba> getIzvedbe() {
		return listIzvedba;
	}

	public void setIzvedbe(List<Izvedba> listaIzvedba) {
		this.listIzvedba = listaIzvedba;
	}
}
