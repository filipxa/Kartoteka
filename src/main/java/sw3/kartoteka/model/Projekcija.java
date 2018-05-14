package sw3.kartoteka.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Projekcija {
	
	private Integer idProjekcije;
	private VrstaIzvedbe vrstaIzvedbe;
	private Sala sala;
	private List<Karta> prodateKarte;
	private Map<Integer, Integer> rezervisaneKarte; // <idKarte, idKorisnika>
	private List<Karta> karteNaPopustu;
	private Date termin;
	
	
	public VrstaIzvedbe getVrstaIzvedbe() {
		return vrstaIzvedbe;
	}
	public void setVrstaIzvedbe(VrstaIzvedbe vrstaIzvedbe) {
		this.vrstaIzvedbe = vrstaIzvedbe;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public List<Karta> getProdateKarte() {
		return prodateKarte;
	}
	public void setProdateKarte(List<Karta> prodateKarte) {
		this.prodateKarte = prodateKarte;
	}
	public Map<Integer, Integer> getRezervisaneKarte() {
		return rezervisaneKarte;
	}
	public void setRezervisaneKarte(Map<Integer, Integer> rezervisaneKarte) {
		this.rezervisaneKarte = rezervisaneKarte;
	}
	public List<Karta> getKarteNaPopustu() {
		return karteNaPopustu;
	}
	public void setKarteNaPopustu(List<Karta> karteNaPopustu) {
		this.karteNaPopustu = karteNaPopustu;
	}
	public Date getTermin() {
		return termin;
	}
	public void setTermin(Date termin) {
		this.termin = termin;
	}
	public Integer getIdProjekcije() {
		return idProjekcije;
	}
	public void setIdProjekcije(Integer idProjekcije) {
		this.idProjekcije = idProjekcije;
	}
	
}
