package sw3.kartoteka.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//public class Projekcija {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(unique = true, nullable = false)
//	private Integer idProjekcije;
//	
//	@Column
//	private VrstaIzvedbe vrstaIzvedbe;
//	
//	@Column
//	private Sala sala;
//	
//	@Column
//	private List<Karta> prodateKarte;
//	
//	@Column
//	private Map<Integer, Integer> rezervisaneKarte; // <idKarte, idKorisnika>
//	
//	@Column
//	private List<Karta> karteNaPopustu;
//	
//	@Column
//	private Date termin;
//	
//	
//	public VrstaIzvedbe getVrstaIzvedbe() {
//		return vrstaIzvedbe;
//	}
//	public void setVrstaIzvedbe(VrstaIzvedbe vrstaIzvedbe) {
//		this.vrstaIzvedbe = vrstaIzvedbe;
//	}
//	public Sala getSala() {
//		return sala;
//	}
//	public void setSala(Sala sala) {
//		this.sala = sala;
//	}
//	public List<Karta> getProdateKarte() {
//		return prodateKarte;
//	}
//	public void setProdateKarte(List<Karta> prodateKarte) {
//		this.prodateKarte = prodateKarte;
//	}
//	public Map<Integer, Integer> getRezervisaneKarte() {
//		return rezervisaneKarte;
//	}
//	public void setRezervisaneKarte(Map<Integer, Integer> rezervisaneKarte) {
//		this.rezervisaneKarte = rezervisaneKarte;
//	}
//	public List<Karta> getKarteNaPopustu() {
//		return karteNaPopustu;
//	}
//	public void setKarteNaPopustu(List<Karta> karteNaPopustu) {
//		this.karteNaPopustu = karteNaPopustu;
//	}
//	public Date getTermin() {
//		return termin;
//	}
//	public void setTermin(Date termin) {
//		this.termin = termin;
//	}
//	public Integer getIdProjekcije() {
//		return idProjekcije;
//	}
//	public void setIdProjekcije(Integer idProjekcije) {
//		this.idProjekcije = idProjekcije;
//	}
//	
//}
