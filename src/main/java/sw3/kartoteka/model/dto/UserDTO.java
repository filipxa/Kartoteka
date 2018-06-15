package sw3.kartoteka.model.dto;

import java.io.Serializable;

import sw3.kartoteka.model.Korisnik;
import sw3.kartoteka.model.TipKorisnika;

public class UserDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String lName;
	
	private String email;
	
	private String password;
	
	private String tip;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String name, String lName, String email,
			String password, String tip){
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.lName = lName;
		this.password = password;
		this.setTip(tip);

	}
	public UserDTO(Korisnik user){
		this(user.getIdKorisnika(), user.getIme(), user.getPrezime(), 
				user.getEmail(), user.getPassword(), user.getTip());
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



}
