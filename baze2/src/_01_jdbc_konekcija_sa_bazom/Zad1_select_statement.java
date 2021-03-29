package _01_jdbc_konekcija_sa_bazom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Zad1_select_statement {
	public static void main(String[] args) {
		System.out.println("01");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@nastava.is.pmf.uns.ac.rs:1521:xe", "baze2", "baze2");

//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/baze2", "root", "root");

			stmt = conn.createStatement();
			rset = stmt.executeQuery("select ime, prezime from nastavnik");

			while (rset.next()) {
				System.out.println(rset.getString(1) + " " + rset.getString(2));
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