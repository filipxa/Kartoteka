package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Skala {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "skala_id")
	private Integer idSkala;
	
	@Column
	private Integer bronzaGranica;
	
	@Column
	private Integer bronzaPopust;
	
	@Column
	private Integer srebroGranica;
	
	@Column
	private Integer srebroPopust;
	
	@Column
	private Integer zlatoGranica;
	
	@Column
	private Integer zlatoPopust;
	
	
	public Skala() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Skala( Integer bronzaGranica, Integer bronzaPopust, Integer srebroGranica,
			Integer srebroPopust, Integer zlatoGranica, Integer zlatoPopust) {
		super();
		this.bronzaGranica = bronzaGranica;
		this.bronzaPopust = bronzaPopust;
		this.srebroGranica = srebroGranica;
		this.srebroPopust = srebroPopust;
		this.zlatoGranica = zlatoGranica;
		this.zlatoPopust = zlatoPopust;
	}

	public Integer getIdSkala() {
		return idSkala;
	}

	public void setIdSkala(Integer idSkala) {
		this.idSkala = idSkala;
	}

	public Integer getBronzaGranica() {
		return bronzaGranica;
	}

	public void setBronzaGranica(Integer bronzaGranica) {
		this.bronzaGranica = bronzaGranica;
	}

	public Integer getBronzaPopust() {
		return bronzaPopust;
	}

	public void setBronzaPopust(Integer bronzaPopust) {
		this.bronzaPopust = bronzaPopust;
	}

	public Integer getSrebroGranica() {
		return srebroGranica;
	}

	public void setSrebroGranica(Integer srebroGranica) {
		this.srebroGranica = srebroGranica;
	}

	public Integer getSrebroPopust() {
		return srebroPopust;
	}

	public void setSrebroPopust(Integer srebroPopust) {
		this.srebroPopust = srebroPopust;
	}

	public Integer getZlatoGranica() {
		return zlatoGranica;
	}

	public void setZlatoGranica(Integer zlatoGranica) {
		this.zlatoGranica = zlatoGranica;
	}

	public Integer getZlatoPopust() {
		return zlatoPopust;
	}

	public void setZlatoPopust(Integer zlatoPopust) {
		this.zlatoPopust = zlatoPopust;
	}

	@Override
	public String toString() {
		return "Skala [idSkala=" + idSkala + ", bronzaGranica=" + bronzaGranica + ", bronzaPopust=" + bronzaPopust
				+ ", srebroGranica=" + srebroGranica + ", srebroPopust=" + srebroPopust + ", zlatoGranica="
				+ zlatoGranica + ", zlatoPopust=" + zlatoPopust + "]";
	}
	
}
