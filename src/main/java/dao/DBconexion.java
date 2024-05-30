package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconexion {
	
	public static Connection instance = null;
	
	public static final String JDBC_URL = "jdbc:mysql://localhost:8889/creatorshub";
	
	private DBconexion() {
		
	}
	
	public static Connection getConexion() throws SQLException {
		
		if(instance == null) {
		

			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "root");
		
			
			instance = DriverManager.getConnection(JDBC_URL, props);
			
		}
		
		return instance;
		
	}
	
	
	
	
	
}