package _03_uskladistene_procedure_i_meta_podaci;

import java.sql.*;

public class Zad2_meta_podaci {
	public static void main(String[] args) {
		System.out.println("03 - 2");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@nastava.is.pmf.uns.ac.rs:1521:xe", "baze2", "baze2");

			String s = "select * from nastavnik";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(s);

			ResultSetMetaData rsmd = rset.getMetaData();

			int brojKolona = rsmd.getColumnCount();

			for (int i = 1; i <= brojKolona; i++) {
				System.out.printf("%20s", rsmd.getColumnName(i));
			}
			System.out.println();
			System.out.println(
					"------------------------------------------------------------------------------------------------");

			while (rset.next()) {
				for (int i = 1; i <= brojKolona; i++) {
					switch (rsmd.getColumnTypeName(i)) {
					case "VARCHAR2" -> System.out.printf("%20s", rset.getString(i)); // VARCHAR za mysql
					case "DATE" -> System.out.printf("%20s", rset.getDate(i));
					case "NUMBER" -> System.out.printf("%20s", rset.getInt(i)); // INT za mysql
					}
				}
				System.out.println();
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
