package _01_jdbc_konekcija_sa_bazom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Zad2_netrivijalan_select {
	public static void main(String[] args) {
		System.out.println("01");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/baze2", "root", "root");

			stmt = conn.createStatement();
			rset = stmt.executeQuery("select p.naziv " + "from nastavnik n, predmet p, predaje pr "
					+ "where n.nastavnik_Id = pr.nastavnik_id and pr.predmet_id = p.predmet_id and n.ime = 'Petar' and n.prezime = 'Peric'");

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
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}