package _03_uskladistene_procedure_i_meta_podaci;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Zad1_uskladistene_funkcije {
	public static void main(String[] args) {
		System.out.println("03-1");

		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@nastava.is.pmf.uns.ac.rs:1521:xe", "baze2", "baze2");

			cstmt = conn.prepareCall("{?=call povezi(?, ?, ?)}");
			cstmt.setString(2, "Mina");
			cstmt.setString(3, "Minic");
			cstmt.setString(4, "Operativni sistemi");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.executeUpdate();
			System.out.println("Rezultat izvrsavanja: " + cstmt.getInt(1));

//            za mysql
//            cstmt = conn.prepareCall("{call povezi(?, ?, ?, ?)}");
//            cstmt.setString(1, "Mina");
//            cstmt.setString(2, "Minic");
//            cstmt.setString(3, "Operativni sistemi");
//            cstmt.registerOutParameter(4, Types.INTEGER);
//            cstmt.executeUpdate();
//            System.out.println("Rezultat izvrsavanja: " + cstmt.getInt(4));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
