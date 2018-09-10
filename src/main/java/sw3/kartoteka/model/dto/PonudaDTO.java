package sw3.kartoteka.model.dto;

import sw3.kartoteka.model.entity.Ponuda;

public class PonudaDTO {
	private Integer id;
	
	private Integer korisnikID;
	
	private Integer oglasID;
	
	private Integer cena;
	
	private boolean prihvacena;
	
	private boolean zavrsena;
	

	public PonudaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public PonudaDTO(Integer id, Integer korisnikID, Integer oglasID, Integer cena, boolean prihvacena,
			boolean zavrsena) {
		super();
		this.id = id;
		this.korisnikID = korisnikID;
		this.oglasID = oglasID;
		this.cena = cena;
		this.prihvacena = prihvacena;
		this.zavrsena = zavrsena;
	}



	public PonudaDTO(Ponuda ponuda) {
		this.id = ponuda.getId();
		this.korisnikID = ponuda.getKorisnik().getIdKorisnika();
		this.oglasID = ponuda.getOglas().getIdOglasa();
		this.prihvacena = ponuda.isPrihvacena();
		this.zavrsena = ponuda.isZavrsena();
		this.cena = ponuda.getCena();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKorisnikID() {
		return korisnikID;
	}

	public void setKorisnikID(Integer korisnikID) {
		this.korisnikID = korisnikID;
	}

	public Integer getOglasID() {
		return oglasID;
	}

	public void setOglasID(Integer oglasID) {
		this.oglasID = oglasID;
	}

	public Integer getCena() {
		return cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}

	public boolean isPrihvacena() {
		return prihvacena;
	}

	public void setPrihvacena(boolean prihvacena) {
		this.prihvacena = prihvacena;
	}

	public boolean isZavrsena() {
		return zavrsena;
	}

	public void setZavrsena(boolean zavrsena) {
		this.zavrsena = zavrsena;
	}

	@Override
	public String toString() {
		return "PonudaDTO [id=" + id + ", korisnikID=" + korisnikID + ", oglasID=" + oglasID + ", prihvacena="
				+ prihvacena + ", zavrsena=" + zavrsena + "]";
	}
	
	
}
