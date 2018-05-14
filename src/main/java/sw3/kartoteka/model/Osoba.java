package sw3.kartoteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Osoba {

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
