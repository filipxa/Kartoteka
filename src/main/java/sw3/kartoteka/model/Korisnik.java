package sw3.kartoteka.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idKorisnika;

	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private TipKorisnika tipKorisnika;
	
	@ManyToMany
	@Column(nullable = false)
	private List<Korisnik> listaPrijatelja;

	
	
	public int getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public List<Korisnik> getListaPrijatelja() {
		return listaPrijatelja;
	}

	public void setListaPrijatelja(List<Korisnik> listaPrijatelja) {
		this.listaPrijatelja = listaPrijatelja;
	}

}
