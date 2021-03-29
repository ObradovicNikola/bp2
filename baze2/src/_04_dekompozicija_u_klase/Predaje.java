package _04_dekompozicija_u_klase;

public class Predaje {
	private int predmet_id;
	private int nastavnik_id;

	public Predaje() {
		super();
	}

	public Predaje(int predmet_id, int nastavnik_id) {
		this();
		this.predmet_id = predmet_id;
		this.nastavnik_id = nastavnik_id;
	}

	public int getPredmet_id() {
		return predmet_id;
	}

	public void setPredmet_id(int predmet_id) {
		this.predmet_id = predmet_id;
	}

	public int getNastavnik_id() {
		return nastavnik_id;
	}

	public void setNastavnik_id(int nastavnik_id) {
		this.nastavnik_id = nastavnik_id;
	}

}
