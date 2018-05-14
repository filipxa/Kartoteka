package sw3.kartoteka.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Karta {
	
	@Column(nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idKarte;
	
	@Column
	private Sala sala;
	
	@Column
	private Sediste sediste;
	
	@Column
	@GeneratedValue(strategy = IDENTITY)  // ne znam da li treba
	private Integer idKupca;
	
	@Column
	private int popust;
	
	@Column
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
