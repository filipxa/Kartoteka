package sw3.kartoteka.model;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//@Entity
public class Pozoriste {
	// Fali jos stvari Repertoar nije dovar treba lista filmova/bioskopa
	// Adresa treba da senapravi za mape
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false)
	private boolean isPozoriste;

	@Column(nullable = false)
	private String naziv;

	@Column(nullable = false)
	private String adresa;

	@Column
	private String repertoar;

	@Column
	private String promotivniOpis;

	@ManyToOne(fetch = LAZY)
	@Column(nullable = false)
	private List<Karta> karteBrzaRezervacija;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPozoriste() {
		return isPozoriste;
	}

	public void setPozoriste(boolean isPozoriste) {
		this.isPozoriste = isPozoriste;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(String repertoar) {
		this.repertoar = repertoar;
	}

	public String getPromotivniOpis() {
		return promotivniOpis;
	}

	public void setPromotivniOpis(String promotivniOpis) {
		this.promotivniOpis = promotivniOpis;
	}

	public List<Karta> getKarteBrzaRezervacija() {
		return karteBrzaRezervacija;
	}

	public void setKarteBrzaRezervacija(List<Karta> karteBrzaRezervacija) {
		this.karteBrzaRezervacija = karteBrzaRezervacija;
	}

}
