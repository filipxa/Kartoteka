package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sediste {
	
	@Column(nullable = false, unique = true, name = "sediste_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idSedista;


	@Column
	private int red;
	
	@Column
	private int kolona;

	public Integer getIdSedista() {
		return idSedista;
	}

	public void setIdSedista(Integer idSedista) {
		this.idSedista = idSedista;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getKolona() {
		return kolona;
	}

	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
	


	
}
