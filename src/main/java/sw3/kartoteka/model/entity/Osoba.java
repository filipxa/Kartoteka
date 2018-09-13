package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Osoba {

	public Osoba(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}

	
	public Osoba() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Column(nullable = false, unique = true, name = "osoba_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idOsobe;
	
	@Column
	private String ime;

	
	@Column
	private String prezime;

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
