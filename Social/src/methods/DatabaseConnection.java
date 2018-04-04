package methods;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection ConnectDB() {
		Connection db = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://hard-plum.db.elephantsql.com:5432/tsiktwnd";
		String username = "tsiktwnd";
		String password = "KElFVKKuaxso_6F3wx0wa_kN1rqAdOkO";

		try {
			db = DriverManager.getConnection(url, username, password);
			db.setAutoCommit(false);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		
		return db;
	}
}
