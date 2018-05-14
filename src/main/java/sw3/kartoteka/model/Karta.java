package sw3.kartoteka.model;

import javax.persistence.Entity;

@Entity
public class Karta {
	
	private Integer idKarte;
	private Sala sala;
	private Sediste sediste;
	private Integer idKupca;
	private int popust;
	private int cena;
	
	public int getIdKarte() {
		return idKarte;
	}
	public void setIdKarte(int idKarte) {
		this.idKarte = idKarte;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Sediste getSediste() {
		return sediste;
	}
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
	public int getIdKupca() {
		return idKupca;
	}
	public void setIdKupca(int idKupca) {
		this.idKupca = idKupca;
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
}
