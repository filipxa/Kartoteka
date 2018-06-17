package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Naslov {

	@Column(nullable = false, unique = true, name= "naslov_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	
	@Column(nullable = false)
	private String naziv;
	

	@ManyToOne
	@JoinColumn(name = "osoba_id", referencedColumnName = "osoba_id", nullable = false)
	private Osoba reziser;
	
	@Column
	@OneToMany
	private List<Osoba> glumci;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Osoba getReziser() {
		return reziser;
	}
	public void setReziser(Osoba reziser) {
		this.reziser = reziser;
	}
	public List<Osoba> getGlumci() {
		return glumci;
	}
	public void setGlumci(List<Osoba> glumci) {
		this.glumci = glumci;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
