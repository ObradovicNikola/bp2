package _04_dekompozicija_u_klase;

public class Predmet {
	private int predmet_id;
	private String naziv;

	public Predmet() {
		super();
	}

	public Predmet(int predmet_id, String naziv) {
		this();
		this.predmet_id = predmet_id;
		this.naziv = naziv;
	}

	public int getPredmet_id() {
		return predmet_id;
	}

	public void setPredmet_id(int predmet_id) {
		this.predmet_id = predmet_id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
