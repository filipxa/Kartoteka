package sw3.kartoteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Rekvizit {
	
	@Column
	private Integer idRekvizita;

	public Integer getIdRekvizita() {
		return idRekvizita;
	}

	public void setIdRekvizita(Integer idRekvizita) {
		this.idRekvizita = idRekvizita;
	}
}
