package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Karta {
	
	@Column(nullable = false, unique = true, name= "karta_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idKarte;
	
	@ManyToOne
	@JoinColumn(name = "izvedba_id", referencedColumnName = "izvedba_id", nullable = false)
	private Izvedba izvedba;
	
	@ManyToOne
	@JoinColumn(name = "sediste_id", referencedColumnName = "sediste_id", nullable = false)
	private Sediste sediste;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id", nullable = false)
	private Korisnik korisnik;
	
	//Karta moze biti rezervisana prodata slobodna
	@Column
	private String tip;
	
	@Column
	private int popust;
	
	@Column
	private int cena;
	
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	
	public int getIdKarte() {
		return idKarte;
	}
	public void setIdKarte(int idKarte) {
		this.idKarte = idKarte;
	}

	public Sediste getSediste() {
		return sediste;
	}
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public int getPopust() {
		return popust;
	}
	public void setPopust(int popust) {
		this.popust = popust;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public Izvedba getIzvedba() {
		return izvedba;
	}
	public void setIzvedba(Izvedba izvedba) {
		this.izvedba = izvedba;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}

}
