package sw3.kartoteka.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Osoba {

	@Column(nullable = false, unique = true)
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
