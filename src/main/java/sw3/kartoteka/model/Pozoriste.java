package sw3.kartoteka.model;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//@Entity
//public class Pozoriste {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(unique = true, nullable = false)
//	private Integer id;
//	
//	
//	@Column(nullable = false)
//	private boolean isPozoriste;
//	
//	@Column(nullable = false)
//	private Repertoar repertoar;
//
//	@Column(nullable = false)
//	private String naziv;
//
//	@Column(nullable = false)
//	private Adresa adresa;
//
//	@Column
//	private String promotivniOpis;
//
//	@OneToMany(fetch = LAZY, targetEntity = Karta.class )
//	private List<Karta> karteBrzaRezervacija;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Repertoar getRepertoar() {
//		return repertoar;
//	}
//
//	public void setRepertoar(Repertoar repertoar) {
//		this.repertoar = repertoar;
//	}
//
//	public boolean isPozoriste() {
//		return isPozoriste;
//	}
//
//	public void setPozoriste(boolean isPozoriste) {
//		this.isPozoriste = isPozoriste;
//	}
//
//	public String getNaziv() {
//		return naziv;
//	}
//
//	public void setNaziv(String naziv) {
//		this.naziv = naziv;
//	}
//
//	public Adresa getAdresa() {
//		return adresa;
//	}
//
//	public void setAdresa(Adresa adresa) {
//		this.adresa = adresa;
//	}
//
//	public String getPromotivniOpis() {
//		return promotivniOpis;
//	}
//
//	public void setPromotivniOpis(String promotivniOpis) {
//		this.promotivniOpis = promotivniOpis;
//	}
//
//	public List<Karta> getKarteBrzaRezervacija() {
//		return karteBrzaRezervacija;
//	}
//
//	public void setKarteBrzaRezervacija(List<Karta> karteBrzaRezervacija) {
//		this.karteBrzaRezervacija = karteBrzaRezervacija;
//	}
//
//
//	
//
//}
