package sw3.kartoteka.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Oglas {
	@Column(nullable = false, unique = true,name = "oglas_id")
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idOglasa;

	@Column
	private String naziv;

	@Column
	private String opis;

	@Column
	private Date datum;

	@Column
	private boolean odobren;

	@Column
	private boolean prodat;

	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id")
	private Korisnik korisnik;


	@Override
	public String toString() {
		return "Oglas [idOglasa=" + idOglasa + ", naziv=" + naziv + ", opis=" + opis + ", datum=" + datum + ", odobren="
				+ odobren + ", prodat=" + prodat + ", korisnik=" + korisnik.getEmail() + "]";
	}

	public boolean isProdat() {
		return prodat;
	}

	public void setProdat(boolean prodat) {
		this.prodat = prodat;
	}

	public Integer getIdOglasa() {
		return idOglasa;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public void setIdOglasa(Integer idRekvizita) {
		this.idOglasa = idRekvizita;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
