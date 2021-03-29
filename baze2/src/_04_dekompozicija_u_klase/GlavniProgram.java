package _04_dekompozicija_u_klase;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GlavniProgram {
	public static void main(String[] args) {

		Nastavnik n = new Nastavnik(420, "Milivoje", "Radic", "Prof");
		DBF.dodajNastavnika(n);

		try {
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Unesite ime nastavnika: ");
			String ime = b.readLine();

			System.out.println("Unesite prezime nastavnika: ");
			String prezime = b.readLine();

			System.out.println("Unesite zanimanje nastavnika: ");
			String zanimanje = b.readLine();

			n = new Nastavnik(1337, ime, prezime, zanimanje);
			DBF.dodajNastavnika(n);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBF.prikaziPredmete(1);

		DBF.promeniZvanje(1337, "docent");

		if (DBF.obrisiNastavnika(6611)) {
			System.out.println("Uspesno brisanje nastavnika 6611");
		} else {
			System.out.println("Doslo je do greske prilikom brisanja nastavnika 6611");
		}

		DBConnection.closeConnection();

	}
}
