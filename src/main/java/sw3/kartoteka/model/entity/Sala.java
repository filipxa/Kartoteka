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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Sala {
	
	public Sala( int brRedova, int brKolona, Lokal lokal) {
		super();
		this.brRedova = brRedova;
		this.brKolona = brKolona;
		this.lokal = lokal;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name = "sala_id")
	private int idSale;
	
	@Column
	private int brRedova;
	
	@Column
	private int brKolona;
	
	@ManyToOne
	@JoinColumn(name = "lokal_id", referencedColumnName = "lokal_id", nullable = false)
	private Lokal lokal;
	
	
	@OneToMany
	@Column
	private List<Sediste> sedista;
	
	
	public List<Sediste> getSedista() {
		return sedista;
	}


	public void setSedista(List<Sediste> sedista) {
		this.sedista = sedista;
	}


	public Sala() {
		
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

	public int getIdSale() {
		return idSale;
	}
	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}
	@JsonIgnore
	public Lokal getLokal() {
		return lokal;
	}
	public void setLokal(Lokal lokal) {
		this.lokal = lokal;
	}
}
