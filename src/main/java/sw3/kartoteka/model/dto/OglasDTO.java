package sw3.kartoteka.model.dto;

import java.util.Date;

import sw3.kartoteka.model.entity.Oglas;

public class OglasDTO {

	private Integer idOglasa;

	private String naziv;

	private String opis;

	private int postavioID;
	
	private long datum;

	private boolean odobren;
	
	private boolean prodat;
	
	public OglasDTO(Oglas oglas) {
		super();
		//menjati jos oko prodaje rezervacije i korisnika...
		this.idOglasa = oglas.getIdOglasa();
		this.naziv = oglas.getNaziv();
		this.opis = oglas.getOpis();
		this.datum = oglas.getDatum().getTime();
		this.odobren = oglas.isOdobren();
		this.prodat = oglas.isProdat();
		this.postavioID = oglas.getKorisnik().getIdKorisnika();

	}
	public OglasDTO() {}


	public OglasDTO(Integer idOglasa, String naziv, String opis, int postavioID, long datum,
			boolean odobren, boolean prodat) {
		super();
		this.idOglasa = idOglasa;
		this.naziv = naziv;
		this.opis = opis;
		this.postavioID = postavioID;
		this.datum = datum;
		this.odobren = odobren;
		this.prodat = prodat;
	}


	
	@Override
	public String toString() {
		return "OglasDTO [idOglasa=" + idOglasa + ", naziv=" + naziv + ", opis=" + opis + ", postavioID=" + postavioID
				+ ", datum=" + datum + ", odobren=" + odobren + ", prodat=" + prodat + "]";
	}
	
	public int getPostavioID() {
		return postavioID;
	}
	public void setPostavioID(int postavioID) {
		this.postavioID = postavioID;
	}
	public Integer getIdOglasa() {
		return idOglasa;
	}

	public void setIdOglasa(Integer idRekvizita) {
		this.idOglasa = idRekvizita;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isProdat() {
		return prodat;
	}
	public void setProdat(boolean prodat) {
		this.prodat = prodat;
	}
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isOdobren() {
		return odobren;
	}
	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}
	public long getDatum() {
		return datum;
	}

	public void setDatum(long datum) {
		this.datum = datum;
	}

}
