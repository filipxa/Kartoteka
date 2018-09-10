package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ponuda {
	@Column(nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id")
	private Korisnik korisnik;
	
	@ManyToOne
	@JoinColumn(name = "oglas_id", referencedColumnName = "oglas_id")
	private Oglas oglas;
	
	@Column
	private Integer cena;
	


	@Column
	private boolean prihvacena;
	
	@Column
	private boolean zavrsena;



	@Override
	public String toString() {
		return "Ponuda [id=" + id + ", korisnik=" + korisnik.getIdKorisnika() + ", oglas=" + oglas.getIdOglasa() + ", cena=" + cena + ", prihvacena="
				+ prihvacena + ", zavrsena=" + zavrsena + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public boolean isPrihvacena() {
		return prihvacena;
	}

	public void setPrihvacena(boolean prihvacena) {
		this.prihvacena = prihvacena;
	}
	public Integer getCena() {
		return cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}

	public boolean isZavrsena() {
		return zavrsena;
	}

	public void setZavrsena(boolean zavrsena) {
		this.zavrsena = zavrsena;
	}
	
	
}
