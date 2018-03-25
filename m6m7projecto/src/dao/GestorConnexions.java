package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConnexions {
	private static String url = "jdbc:mysql://192.168.3.145/bdclinica";
	private static String user = "root" ;
	private static String pwd = "root";
	private static Connection connexio = null;

	private static int connectar() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexio = DriverManager.getConnection(url,user,pwd);
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	public static Connection obtenirConnexio() throws SQLException{
			if (connexio == null || connexio.isClosed()){
				connectar();
			}
		return connexio;
	}

	public static void tancarConnexio() throws SQLException{

			connexio.close();

	}
}