package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rekvizit {
	
	@Column(nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idRekvizita;
	
	@Column
	private String naziv;
	
	@Column
	private String opis;
	
	@Column
	private int cena;

	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id")
	private Korisnik korisnik;


	

	public Integer getIdRekvizita() {
		return idRekvizita;
	}

	public void setIdRekvizita(Integer idRekvizita) {
		this.idRekvizita = idRekvizita;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}	
}
