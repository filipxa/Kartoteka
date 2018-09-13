package sw3.kartoteka.model.entity;

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
	

	public Lokal(Integer id, boolean isPozoriste, Repertoar repertoar, String naziv, String adresa,
			String promotivniOpis, List<Sala> sale) {
		super();
		this.id = id;
		this.isPozoriste = isPozoriste;
		this.repertoar = repertoar;
		this.naziv = naziv;
		this.adresa = adresa;
		this.promotivniOpis = promotivniOpis;
		this.sale = sale;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( nullable = false, name = "lokal_id")
	private Integer id;

	@Column(nullable = false)
	private boolean isPozoriste;
	
	@ManyToOne
	@JoinColumn(name = "repertoar_id", referencedColumnName = "repertoar_id")
	private Repertoar repertoar;

	@Column(nullable = false)
	private String naziv;

	@Column()
	private String adresa;

	@Column
	private String promotivniOpis;
	
	@OneToMany
	@Column
	private List<Sala> sale;
	
	 
	public List<Sala> getSale() {
		return sale;
	}


	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}


	public Lokal() {
		
	}


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

	public void setIsPozoriste(boolean isPozoriste) {
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
	public void setPozoriste(boolean isPozoriste) {
		this.isPozoriste = isPozoriste;
	}



	

}
