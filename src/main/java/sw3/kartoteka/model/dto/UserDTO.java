package sw3.kartoteka.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sw3.kartoteka.model.entity.Korisnik;
import sw3.kartoteka.model.entity.Lokal;

public class UserDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String lName;
	
	private String email;
	
	private String password;
	
	private String tip;
	
	private Boolean activated;
	
	private Integer tel;
	
	private String adresa;
	
	private List<String> listaLokala;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String name, String lName, String email,
			String password, String tip, String adresa, int tel){
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.lName = lName;
		this.password = password;
		this.setTip(tip);
		this.setAdresa(adresa);
		this.setActivated(false);
		this.tel = tel;
		this.listaLokala = new ArrayList<>();

	}
	public UserDTO(Korisnik user){
		this(user.getIdKorisnika(), user.getIme(), user.getPrezime(), 
				user.getEmail(), user.getPassword(), user.getTip(), user.getAdresa(), user.getBrTelefona());
		this.setActivated(user.getActivated());
		this.setListaLokala(user.getAdminLokali());
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public String getlName() {
		return lName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getAdresa() {
		return adresa;
	}

	public List<String> getListaLokala() {
		return listaLokala;
	}

	public void setListaLokala(List<String> listaLokala) {
		this.listaLokala = listaLokala;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}



}
