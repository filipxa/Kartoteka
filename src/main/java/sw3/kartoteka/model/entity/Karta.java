package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Karta {
	
	@Column(nullable = false, unique = true, name= "karta_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idKarte;
	public Karta() {
		
	}
	
	public Karta( Izvedba izvedba, Sediste sediste, Korisnik korisnik, String tip, int popust,
			int cena) {
		super();
		this.izvedba = izvedba;
		this.sediste = sediste;
		this.korisnik = korisnik;
		this.tip = tip;
		this.popust = popust;
		this.cena = cena;
	}

	public void setIdKarte(Integer idKarte) {
		this.idKarte = idKarte;
	}
	
	@ManyToOne
	@JoinColumn(name = "izvedba_id", referencedColumnName = "izvedba_id")
	private Izvedba izvedba;
	
	@ManyToOne
	@JoinColumn(name = "sediste_id", referencedColumnName = "sediste_id")
	private Sediste sediste;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id")
	private Korisnik korisnik;
	
	//Karta moze biti rezervisano slobodno
	@Column
	private String tip;
	
	@Column
	private int popust;
	
	@Column
	private int cena;

	public Izvedba getIzvedba() {
		return izvedba;
	}
	
	public String getLokalNaziv() {
		return izvedba.getSala().getLokal().getNaziv();
	}

	public void setIzvedba(Izvedba izvedba) {
		this.izvedba = izvedba;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
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

	public Integer getIdKarte() {
		return idKarte;
	}
	
	public String toEmailString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("===============ID=%04d===============\n", getIdKarte()));
		sb.append(izvedba.getNaslov().getNaziv());
		sb.append("   ");
		sb.append(izvedba.getTermin().toString());
		sb.append("\n");
		sb.append("Auditorium: " + izvedba.getSala().getIdSale() +"\n");
		sb.append(String.format("Seat: row %02d   num %02d", sediste.getRed(), sediste.getKolona())+"\n");
		sb.append("Ime i prezime: " + korisnik.getIme() + " "+ korisnik.getPrezime()+"\n");
		sb.append("====================================\n");
		return sb.toString();
	}
	
	

}
