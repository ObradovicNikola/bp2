package _02_dml_operacije_i_transakcije;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Zad1_insert_statement {
	public static void main(String[] args) {
		System.out.println("02 - 1");

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/baze2", "root", "root");

			stmt = conn.createStatement();
			int brojRedova = stmt.executeUpdate(
					"insert into nastavnik (ime, prezime, zvanje) values ('Nikola', 'Jokara', 'doktor')");
			System.out.println("Dodat je " + brojRedova + (brojRedova == 1 ? " nastavnik" : " nastavnika."));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
