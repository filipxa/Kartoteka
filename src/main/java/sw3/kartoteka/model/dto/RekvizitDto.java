package sw3.kartoteka.model.dto;

import sw3.kartoteka.model.entity.Rekvizit;

public class RekvizitDto {

	private Integer idRekvizita;

	private String naziv;

	private String opis;

	private int cena;
	
	private boolean rezervisano;

	
	public RekvizitDto(Integer idRekvizita, String naziv, String opis, int cena,boolean rezervisano) {
		super();
		this.idRekvizita = idRekvizita;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.rezervisano = rezervisano;
	}
	
	public RekvizitDto() {}
	
	public RekvizitDto(Rekvizit rekvizit) {
		this.idRekvizita = rekvizit.getIdRekvizita();
		this.naziv = rekvizit.getNaziv();
		this.opis = rekvizit.getOpis();
		this.cena = rekvizit.getCena();
		if(rekvizit.getKorisnik() == null) { 
			this.rezervisano = false;
		}
		else { 
			this.rezervisano = true;}
	}

	public Integer getIdRekvizita() {
		return idRekvizita;
	}

	public void setIdRekvizita(Integer idRekvizita) {
		this.idRekvizita = idRekvizita;
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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isRezervisano() {
		return rezervisano;
	}

	public void setRezervisano(boolean rezervisano) {
		this.rezervisano = rezervisano;
	}
	
	

}
