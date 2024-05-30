package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Esta clase proporciona una conexión a la base de datos utilizando JDBC.
 * La conexión se crea utilizando una instancia única y se mantiene abierta durante la vida útil de la aplicación.
 * Se utiliza el patrón Singleton para garantizar una única instancia de conexión en toda la aplicación.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class DBconexion {
	
	/** La instancia única de la conexión a la base de datos. */
	public static Connection instance = null;
	
	/** La URL de conexión JDBC para la base de datos. */
	public static final String JDBC_URL = "jdbc:mysql://localhost:8889/creatorshub";
	
	/**
     * Constructor privado para evitar la instanciación de la clase.
     */
	private DBconexion() {
		
	}
	
	/**
     * Método que obtiene una conexión a la base de datos.
     * Si la conexión aún no se ha establecido, crea una nueva conexión y la devuelve.
     * Si ya existe una conexión, simplemente la devuelve.
     * 
     * @return la conexión a la base de datos.
     * @throws SQLException si ocurre un error al conectarse a la base de datos.
     */
	
	public static Connection getConexion() throws SQLException {
		
		if(instance == null) {
		
			// Configuración de las propiedades de la conexión
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "root");
		
			// Establecimiento de la conexión
			instance = DriverManager.getConnection(JDBC_URL, props);
			
		}
		
		return instance;
		
	}
	
	
	
	
	
}