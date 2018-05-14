package sw3.kartoteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Adresa {

	@Column
	private String ulica;

	@Column
	private int broj;

	@Column
	private String Grad;

	@Column
	private String Drzava;

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getGrad() {
		return Grad;
	}

	public void setGrad(String grad) {
		Grad = grad;
	}

	public String getDrzava() {
		return Drzava;
	}

	public void setDrzava(String drzava) {
		Drzava = drzava;
	}

}
