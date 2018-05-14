package sw3.kartoteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sediste {

	@Column
	private boolean slobodno;
	
	@Column
	private int red;
	
	@Column
	private int kolona;
	
	
	public boolean isSlobodno() {
		return slobodno;
	}
	public void setSlobodno(boolean slobodno) {
		this.slobodno = slobodno;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getKolona() {
		return kolona;
	}
	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
	
}
