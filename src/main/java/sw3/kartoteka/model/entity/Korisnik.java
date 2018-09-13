package sw3.kartoteka.model.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Korisnik {
	//dodati anotacije za poslednje clanove klase
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "korisnik_id")
	private Integer idKorisnika;

	@Column(nullable = false)
	private String tip;

	@Column(nullable = false)
	private String ime;

	@Column(nullable = false)
	private String prezime;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@ManyToMany
	@Column(nullable = false)
	private List<Korisnik> listaPrijatelja;
	
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "activated")
	private Boolean activated;
	
	@Column
	private Integer brBodova;
	
	@Column
	private Integer brTelefona;
	
	@Column
	private String adresa;
	
	@ManyToMany
	@Column()
	private List<Lokal> listaLokala;
	
//	private Map<Integer, Integer> ocenjeniFilmoviISerije; // <idTipaProjekcija, ocena>
//	
//	private Map<Integer, Integer> ocenjeniBioskopiIPozorista; // <idPozoristBioskopa, ocena>
//	
	//private List<Rekvizit> rekviziti;
	
	@Override
	public String toString() {
		return "Korisnik [idKorisnika=" + idKorisnika + ", tip=" + tip + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", password=" + password  + ", uuid="
				+ uuid + ", activated=" + activated + ", brBodova=" + brBodova + ", brTelefona=" + brTelefona
				+ ", adresa=" + adresa + "]";
	}

	public Integer getBrBodova() {
		return brBodova;
	}

	public void setBrBodova(Integer brBodova) {
		this.brBodova = brBodova;
	}

	public Korisnik() {
		activated=false;
		uuid = UUID.randomUUID().toString();
	}

	public Integer getIdKorisnika() {
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




	@JsonIgnore
	public List<Korisnik> getListaPrijatelja() {
		return listaPrijatelja;
	}

	public void setListaPrijatelja(List<Korisnik> listaPrijatelja) {
		this.listaPrijatelja = listaPrijatelja;
	}
	
	

//	public Map<Integer, Integer> getOcenjeniFilmoviISerije() {
//		return ocenjeniFilmoviISerije;
//	}
//
//	public void setOcenjeniFilmoviISerije(Map<Integer, Integer> ocenjeniFilmoviISerije) {
//		this.ocenjeniFilmoviISerije = ocenjeniFilmoviISerije;
//	}
//
//	public Map<Integer, Integer> getOcenjeniBioskopiIPozorista() {
//		return ocenjeniBioskopiIPozorista;
//	}
//
//	public void setOcenjeniBioskopiIPozorista(Map<Integer, Integer> ocenjeniBioskopiIPozorista) {
//		this.ocenjeniBioskopiIPozorista = ocenjeniBioskopiIPozorista;
//	}

//	public List<Rekvizit> getRekviziti() {
//		return rekviziti;
//	}
//
//	public void setRekviziti(List<Rekvizit> rekviziti) {
//		this.rekviziti = rekviziti;
//	}
	
	@JsonIgnore
	public List<Lokal> getListaLokala() {
		return listaLokala;
	}

	public void setListaLokala(List<Lokal> listaLokala) {
		this.listaLokala = listaLokala;
	}
	
	public List<String> getAdminLokali(){
		List<String> rets = new ArrayList<>();
		for (Lokal lokal : listaLokala) {
			rets.add(lokal.getId().toString());
		}
		return rets;
	}
	
	public void setIdKorisnika(Integer idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Integer getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(Integer brTelefona) {
		this.brTelefona = brTelefona;
	}

	public Korisnik( String tip, String ime, String prezime, String email, String password,
			List<Korisnik> listaPrijatelja, String uuid, Boolean activated, Integer brBodova, Integer brTelefona,
			String adresa) {
		super();
		this.tip = tip;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.listaPrijatelja = listaPrijatelja;
		this.uuid = uuid;
		this.activated = activated;
		this.brBodova = brBodova;
		this.brTelefona = brTelefona;
		this.adresa = adresa;
	}



}
