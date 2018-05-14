package sw3.kartoteka.model;

import java.util.List;

public class Sala {

	private String idSale;
	private int brRedova;
	private int brKolona;
	private List<Sediste> sedista;
	
	
	public String getIdSale() {
		return idSale;
	}
	public void setIdSale(String idSale) {
		this.idSale = idSale;
	}
	public int getBrRedova() {
		return brRedova;
	}
	public void setBrRedova(int brRedova) {
		this.brRedova = brRedova;
	}
	public int getBrKolona() {
		return brKolona;
	}
	public void setBrKolona(int brKolona) {
		this.brKolona = brKolona;
	}
	public List<Sediste> getSedista() {
		return sedista;
	}
	public void setSedista(List<Sediste> sedista) {
		this.sedista = sedista;
	}
}
