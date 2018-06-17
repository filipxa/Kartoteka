package sw3.kartoteka.model.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Sala {
	
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
	public Lokal getLokal() {
		return lokal;
	}
	public void setLokal(Lokal lokal) {
		this.lokal = lokal;
	}
}
