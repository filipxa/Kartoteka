package sw3.kartoteka.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class VrstaIzvedbe {
	
	@Column(nullable = false)
	private boolean isFilm;  // nacin provere da li je film ili predstava
	
	@Column(nullable = false)
	private String naziv;
	
	@Column
	private Date godina;
	
	@Column
	private Osoba reziser;
	
	@Column
	private List<Osoba> glumci;
	
	@Column
	private Double prosecnaOcena;
	
	
	public boolean isFilm() {
		return isFilm;
	}
	public void setFilm(boolean isFilm) {
		this.isFilm = isFilm;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Date getGodina() {
		return godina;
	}
	public void setGodina(Date godina) {
		this.godina = godina;
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
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
}
