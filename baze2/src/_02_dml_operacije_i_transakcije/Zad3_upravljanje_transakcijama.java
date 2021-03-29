package _02_dml_operacije_i_transakcije;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Zad3_upravljanje_transakcijama {
	public static void main(String[] args) {
		System.out.println("02 - 3");

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
			System.out.println("Dodaj je " + brojRedova + " nastavnik.");

			pstmt.setInt(1, 213);
			pstmt.setString(2, "Hal");
			pstmt.setString(3, "Finney");
			pstmt.setString(4, "asistent");
			brojRedova = pstmt.executeUpdate();
			System.out.println("Dodaj je " + brojRedova + " nastavnik.");

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
