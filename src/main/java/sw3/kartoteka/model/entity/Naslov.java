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

	@Column(nullable = false, unique = true, name = "naslov_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column()
	private String naziv;

	@ManyToOne
	@JoinColumn(name = "osoba_id", referencedColumnName = "osoba_id")
	private Osoba reziser;

	@Column
	@OneToMany
	private List<Osoba> glumci;

	@Column
	private String zanr;
	
	@Column
	private int trajanje;

	@Column
	private float ocena;
	
	@Column
	private String opis;
	
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public float getOcena() {
		return ocena;
	}

	public void setOcena(float ocena) {
		this.ocena = ocena;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
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
