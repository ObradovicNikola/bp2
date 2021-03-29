package _02_dml_operacije_i_transakcije;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Zad4_kaskadno_brisanje {
	public static void main(String[] args) {
		System.out.println("02 - 4");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/baze2", "root", "root");

			conn.setAutoCommit(false);

			pstmt = conn
					.prepareStatement("insert into nastavnik (nastavnik_id, ime, prezime, zvanje) values (?, ?, ?, ?)");

			pstmt.setInt(1, 213);
			pstmt.setString(2, "Satoshi");
			pstmt.setString(3, "Nakamoto");
			pstmt.setString(4, "profesor");
			int brojRedova = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Dodaj je " + brojRedova + " nastavnik.");

			pstmt = conn.prepareStatement("insert into predaje (nastavnik_id, predmet_id) values (?, ?)");
			pstmt.setInt(1, 213);
			pstmt.setInt(2, 1);
			brojRedova = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Dodato je " + brojRedova + " u tabelu predaje.");

			pstmt = conn.prepareStatement("delete from predaje where nastavnik_id = ?");
			pstmt.setInt(1, 213);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("delete from nastavnik where nastavnik_id = ?");
			pstmt.setInt(1, 213);
			pstmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
