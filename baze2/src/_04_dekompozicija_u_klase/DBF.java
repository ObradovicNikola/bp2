package _04_dekompozicija_u_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBF {

	public static void dodajNastavnika(Nastavnik n) {
		PreparedStatement pstmt = null;

		try {

			pstmt = DBConnection.getConnection()
					.prepareStatement("insert into nastavnik (nastavnik_id, ime, prezime, zvanje) values (?, ?, ?, ?)");
			pstmt.setInt(1, n.getNastavnik_id());
			pstmt.setString(2, n.getIme());
			pstmt.setString(3, n.getPrezime());
			pstmt.setString(4, n.getZvanje());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean promeniZvanje(int nastavnik_id, String novoZvanje) {
		PreparedStatement pstmt = null;
		boolean uspesno = false;
		try {
			pstmt = DBConnection.getConnection().prepareStatement("update nastavnik set zvanje=? where nastavnik_id=?");
			pstmt.setString(1, novoZvanje);
			pstmt.setInt(2, nastavnik_id);
			pstmt.executeUpdate();
			uspesno = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return uspesno;
	}

//	prikazivanje predmeta koje predaje neki nastavnik
	public static void prikaziPredmete(int nastavnik_id) {
		Statement stmt = null;
		ResultSet rset = null;

		try {
			stmt = DBConnection.getConnection().createStatement();
			rset = stmt.executeQuery("select naziv from predmet p, nastavnik n, predaje pr "
					+ "where p.predmet_id = pr.predmet_id and n.nastavnik_id = pr.nastavnik_id and n.nastavnik_id = "
					+ nastavnik_id);

			System.out.println("Naziv predmeta:");
			while (rset.next()) {
				System.out.println(rset.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean obrisiNastavnika(int nastavnik_id) {
		boolean uspesno = false;
		PreparedStatement pstmt = null;
		try {
			DBConnection.getConnection().setAutoCommit(false);
			pstmt = DBConnection.getConnection().prepareStatement("delete from predaje where nastavnik_id=?");
			pstmt.setInt(1, nastavnik_id);
			pstmt.executeUpdate();
			pstmt.close();

			pstmt = DBConnection.getConnection().prepareStatement("delete from nastavnik where nastavnik_id=?");
			pstmt.setInt(1, nastavnik_id);
			pstmt.executeUpdate();

			DBConnection.getConnection().commit();
			uspesno = true;
		} catch (Exception e) {
			e.printStackTrace();

			try {
				DBConnection.getConnection().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				DBConnection.getConnection().setAutoCommit(true);
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return uspesno;
	}
}
