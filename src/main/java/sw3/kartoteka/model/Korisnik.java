package sw3.kartoteka.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Korisnik {
	//dodati anotacije za poslednje clanove klase
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idKorisnika;

	@Column(nullable = false)
	private TipKorisnika tipKorisnika;

	@Column(nullable = false)
	private String ime;

	@Column(nullable = false)
	private String prezime;

	@Column(nullable = false)
	private String email;

	@ManyToMany
	@Column(nullable = false)
	private List<Korisnik> listaPrijatelja;
	
	private Map<Integer, Integer> ocenjeniFilmoviISerije; // <idTipaProjekcija, ocena>
	
	private Map<Integer, Integer> ocenjeniBioskopiIPozorista; // <idPozoristBioskopa, ocena>
	
	private List<Rekvizit> rekviziti;

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

	public Map<Integer, Integer> getOcenjeniFilmoviISerije() {
		return ocenjeniFilmoviISerije;
	}

	public void setOcenjeniFilmoviISerije(Map<Integer, Integer> ocenjeniFilmoviISerije) {
		this.ocenjeniFilmoviISerije = ocenjeniFilmoviISerije;
	}

	public Map<Integer, Integer> getOcenjeniBioskopiIPozorista() {
		return ocenjeniBioskopiIPozorista;
	}

	public void setOcenjeniBioskopiIPozorista(Map<Integer, Integer> ocenjeniBioskopiIPozorista) {
		this.ocenjeniBioskopiIPozorista = ocenjeniBioskopiIPozorista;
	}

	public List<Rekvizit> getRekviziti() {
		return rekviziti;
	}

	public void setRekviziti(List<Rekvizit> rekviziti) {
		this.rekviziti = rekviziti;
	}

	public void setIdKorisnika(Integer idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

}
