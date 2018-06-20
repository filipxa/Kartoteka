package sw3.kartoteka.model.entity;



import java.util.Date;
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
public class Izvedba {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "izvedba_id")
	private Integer idIzvedba;
	
	
	public Integer getIdIzvedba() {
		return idIzvedba;
	}
	public void setIdIzvedba(Integer idIzvedba) {
		this.idIzvedba = idIzvedba;
	}
	@ManyToOne
	@JoinColumn(name = "sala_id", referencedColumnName = "sala_id")
	private Sala sala;
	
	public Izvedba(Integer idIzvedba, Sala sala, List<Karta> karte, Date termin, boolean isPredstava, Naslov naslov) {
		super();
		this.idIzvedba = idIzvedba;
		this.sala = sala;
		this.karte = karte;
		this.termin = termin;
		this.isPredstava = isPredstava;
		this.naslov = naslov;
	}
	@Column
	@OneToMany
	private List<Karta> karte;
	
	
	public List<Karta> getKarte() {
		return karte;
	}
	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}
	@Column
	private Date termin;
	
	@Column()
	private boolean isPredstava;  
	
	
	@ManyToOne
	@JoinColumn(name = "naslov_id", referencedColumnName = "naslov_id")
	private Naslov naslov;
	

	public boolean isPredstava() {
		return isPredstava;
	}
	public void setPredstava(boolean isFilm) {
		this.isPredstava = isFilm;
	}
	
	public Izvedba() {
		
	}
	



	
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Date getTermin() {
		return termin;
	}
	public void setTermin(Date termin) {
		this.termin = termin;
	}
	public Integer getIdProjekcije() {
		return idIzvedba;
	}
	public void setId(Integer idProjekcije) {
		this.idIzvedba = idProjekcije;
	}
	public Naslov getNaslov() {
		return naslov;
	}
	public void setNaslov(Naslov naslov) {
		this.naslov = naslov;
	}
	
}
