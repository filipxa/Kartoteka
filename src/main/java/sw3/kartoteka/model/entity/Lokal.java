package sw3.kartoteka.model.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lokal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, name = "lokal_id")
	private Integer id;
	
	
	@Column(nullable = false)
	private boolean isPozoriste;
	
	@ManyToOne
	@JoinColumn(name = "repertoar_id", referencedColumnName = "repertoar_id", nullable = false)
	private Repertoar repertoar;

	@Column(nullable = false)
	private String naziv;

	@Column(nullable = false)
	private String adresa;

	@Column
	private String promotivniOpis;
	
	 



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Repertoar getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(Repertoar repertoar) {
		this.repertoar = repertoar;
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

	public String getPromotivniOpis() {
		return promotivniOpis;
	}

	public void setPromotivniOpis(String promotivniOpis) {
		this.promotivniOpis = promotivniOpis;
	}

//	public List<Karta> getKarteBrzaRezervacija() {
//		return karteBrzaRezervacija;
//	}
//
//	public void setKarteBrzaRezervacija(List<Karta> karteBrzaRezervacija) {
//		this.karteBrzaRezervacija = karteBrzaRezervacija;
//	}


	

}
