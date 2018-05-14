package sw3.kartoteka.model;

import java.util.Date;
import java.util.List;

public class VrstaIzvedbe {
	
	private boolean isFilm;
	private String naziv;
	private Date godina;
	private Osoba reziser;
	private List<Osoba> glumci;
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
