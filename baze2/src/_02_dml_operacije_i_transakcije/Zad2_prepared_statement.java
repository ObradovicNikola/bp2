package _02_dml_operacije_i_transakcije;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Zad2_prepared_statement {
	public static void main(String[] args) {
		System.out.println("02 - 2");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/baze2", "root", "root");

			pstmt = conn.prepareStatement("insert into nastavnik (ime, prezime, zvanje) values (?, ?, ?)");
			pstmt.setString(1, "Nikola");
			pstmt.setString(2, "Jokara");
			pstmt.setString(3, "Profa");
			int brojRedova = pstmt.executeUpdate();
			System.out.println("Dodat je " + brojRedova + (brojRedova == 1 ? " nastavnik" : " nastavnika."));

		} catch (Exception e) {
			e.printStackTrace();
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
