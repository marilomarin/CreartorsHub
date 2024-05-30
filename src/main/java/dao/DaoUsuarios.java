package dao;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;


/**
 * Esta clase proporciona métodos para interactuar con la tabla 'Usuarios' en la base de datos.
 * Incluye métodos para insertar, actualizar, borrar y listar usuarios, así como para realizar operaciones de autenticación.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class DaoUsuarios {
	
	// Conexión a la base de datos
	private Connection con = null; 
	
	// Instancia única de la clase para el patrón Singleton
	private static DaoUsuarios  instance =  null; 
	
	/**
	 * Constructor para establecer la conexión a la base de datos.
     * La conexión se obtiene mediante el método 'getConexion()' de la clase 'DBconexion'.
     * 
	 * @throws SQLException si hay un error al conectar a la base de datos.
	 */
	
	public DaoUsuarios() throws SQLException {
		
		con = DBconexion.getConexion();
		
	}
		
	/**
	 * Método estático para obtener la instancia única de la clase.
	 * Implementa el patrón Singleton para garantizar una sola instancia de 'DaoUsuarios'.
	 * 
	 * @return la instancia única de 'DaoUsuarios'.
	 * @throws SQLException si hay un error al conectar a la base de datos.
	 */
		 
	public static DaoUsuarios getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoUsuarios();
		}
		
		return instance;
	}

	/**
	 * Método para insertar un nuevo usuario con rol de <strong>creador</strong>  en la base de datos.
	 * @param u el usuario a insertar.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public void insertarCreador(Usuario u) throws SQLException {
		
		System.out.println("Contraseña recibida para insertar en la base de datos: " + u.getPass());

		
		String sql = "INSERT INTO Usuarios (Email,Nombre,Apellidos,RazonSocial,DNICIF,DireccionLinea1,DireccionLinea2,Ciudad,Provincia,CP,Telefono,Rol,Boletin,Categoria,Portfolio,Bio,Pass,FotoPerfil) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getApellidos());
			ps.setString(4, u.getRazonSocial());
			ps.setString(5, u.getDnicif());
			ps.setString(6, u.getDireccionLinea1());
			ps.setString(7, u.getDireccionLinea2());
			ps.setString(8, u.getCiudad());
			ps.setString(9, u.getProvincia());
			ps.setString(10, u.getCp());
			ps.setString(11, u.getTelefono());
			ps.setString(12, u.getRol());
			ps.setBoolean(13, u.isBoletin());
			ps.setString(14, u.getCategoria());
			ps.setString(15, u.getPortfolio());
			ps.setString(16, u.getBio());
			ps.setString(17, u.getPass());
			ps.setString(18, u.getFotoPerfil());
		
			
			int filas = ps.executeUpdate();
			System.out.println("Filas insertadas: " + filas);
			
			ps.close();
		
		
	}
	
	/**
	 * Método para insertar un nuevo usuario con rol de <strong>empresa</strong> en la base de datos.
	 * @param u el usuario a insertar.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public void insertarEmpresa(Usuario u) throws SQLException {
		
		System.out.println("Contraseña recibida para insertar en la base de datos: " + u.getPass());

		
		String sql = "INSERT INTO Usuarios (Email,Nombre,Apellidos,RazonSocial,DNICIF,DireccionLinea1,DireccionLinea2,Ciudad,Provincia,CP,Telefono,Rol,Boletin,Categoria,Portfolio,Bio,Pass) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getApellidos());
			ps.setString(4, u.getRazonSocial());
			ps.setString(5, u.getDnicif());
			ps.setString(6, u.getDireccionLinea1());
			ps.setString(7, u.getDireccionLinea2());
			ps.setString(8, u.getCiudad());
			ps.setString(9, u.getProvincia());
			ps.setString(10, u.getCp());
			ps.setString(11, u.getTelefono());
			ps.setString(12, u.getRol());
			ps.setBoolean(13, u.isBoletin());
			ps.setString(14, u.getCategoria());
			ps.setString(15, u.getPortfolio());
			ps.setString(16, u.getBio());
			ps.setString(17, u.getPass());
		
			
			int filas = ps.executeUpdate();
			System.out.println("Filas insertadas: " + filas);
			
			ps.close();
		
		
	}
	
	/**
	 * Método para realizar la autenticación de un usuario.
	 * 
	 * @param u el usuario que intenta autenticarse.
	 * @param pass la contraseña proporcionada por el usuario.
	 * @return el usuario autenticado si las credenciales son correctas, y null, si las credenciales no son correctas.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */

	public Usuario login(Usuario u, String pass) throws SQLException {
	
		
		String sql = "SELECT * FROM Usuarios WHERE Email=? AND Pass=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getEmail()); 
		ps.setString(2, pass);
	
		ResultSet rs = ps.executeQuery();
		
		
	    if (rs.next()) { // Verificar si hay algún usuario en la bd antes de intentar acceder a él
	    	
	    	System.out.println("El usuario existe en la bd");
	        
	    	Usuario aux = new Usuario(rs.getString("dnicif"), rs.getString("email"), rs.getString("nombre"), rs.getString("rol"));
	        
	    	return aux;
	    	
	    } else {
	    	System.out.println("El usuario introducido NO existe en la bd");
	    	return null;
	        
	    }
	}
	
	/**
	 * Método para obtener un usuario por su DNI/CIF.
	 * 
	 * @param dnicif el DNI o CIF del usuario.
	 * @return el usuario correspondiente al DNI/CIF especificado.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public Usuario obtenerPorDnicif(String dnicif) throws SQLException {

	    String sql = "SELECT * FROM Usuarios WHERE DNICIF=?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	    	
	        ps.setString(1, dnicif);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	        	
	            if (rs.next()) {
	            	//Aquí ponemos todos los campos del Usuario
	                return new Usuario(
	                		rs.getString(1), 
	                		rs.getString(2), 
	                		rs.getString(3), 
	                		rs.getString(4), 
	                		rs.getString(5), 
	                		rs.getString(6), 
	                		rs.getString(7), 
	                		rs.getString(8), 
	                		rs.getString(9), 
	                		rs.getString(10),
	                		rs.getString(11), 
	                		rs.getString(12), 
	                		rs.getBoolean(13), 
	                		rs.getString(14), 
	                		rs.getString(15),
	                		rs.getString(16),
	                		rs.getString(17),
	                		rs.getString(18));
	                
	            } else {
	                throw new SQLException("No se encontró el usuario con DNI/CIF: " + dnicif);
	            }
	        }
	    }
	}
	
	/**
	 * Método para actualizar los datos de un usuario en la base de datos.
	 * 
	 * @param u el usuario con los datos actualizados.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */

	public void actualizar(Usuario u) throws SQLException {
		
		String sql = "UPDATE Usuarios SET Nombre=?,Apellidos=?,RazonSocial=?,DireccionLinea1=?,DireccionLinea2=?,Ciudad=?,Provincia=?,Cp=?,Telefono=?,Pass=? WHERE Dnicif=?";
		
		//Aquí tengo que establecer el orden en el que están en el formulario
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellidos());
			ps.setString(3, u.getRazonSocial());
			ps.setString(4, u.getDireccionLinea1());
			ps.setString(5, u.getDireccionLinea2());
			ps.setString(6, u.getCiudad());
			ps.setString(7, u.getProvincia());
			ps.setString(8, u.getCp());
			ps.setString(9, u.getTelefono());
			ps.setString(10, u.getPass()); 
			ps.setString(11, u.getDnicif()); 
		
			int filas = ps.executeUpdate();
			System.out.println("Filas actualizadas: " + filas);
			
			ps.close();
		
	}
	
	/**
	 * Método para eliminar un usuario de la base de datos por su DNI/CIF.
	 * 
	 * @param dnicif el DNI o CIF del usuario a eliminar.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public void borrar(String dnicif) throws SQLException {
		
		String sql = "DELETE FROM Usuarios WHERE DNICIF=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,dnicif);
		
			int filas = ps.executeUpdate();
			System.out.println("Filas borradas: " + filas);
			
			ps.close();
	}
	
	
// --------- MÉTODOS PARA LISTAR
	
	
	/**
	 * Método para obtener una lista de usuarios en formato JSON por su DNI/CIF.
	 * 
	 * @param dnicif el DNI o CIF de los usuarios a listar.
	 * @return una cadena JSON que representa la lista de usuarios.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSON(String dnicif) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar(dnicif));
		
		return json;
		
	}
	
	/**
	 * Método para obtener una lista de usuarios por su DNI/CIF.
	 * 
	 * @param dnicif el DNI o CIF de los usuarios a listar.
	 * @return una lista de usuarios correspondientes al DNI/CIF especificado.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */

	public ArrayList<Usuario> listar(String dnicif) throws SQLException{
		
		String sql = "SELECT * FROM Usuarios WHERE dnicif = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dnicif);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> result = null;
		
		while(rs.next()){
			
			if (result == null){
				result = new ArrayList<Usuario>();
			}
			
			result.add(new Usuario(rs.getString("email"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("razonSocial"), rs.getString("dnicif"), rs.getString("direccionLinea1"), rs.getString("direccionLinea2"), rs.getString("ciudad"), rs.getString("provincia"), rs.getString("cp"), rs.getString("telefono"), rs.getString("rol"), rs.getBoolean("boletin"), rs.getString("categoria"), rs.getString("pass")));
			}
		
		return result;
	}

	/**
	 * Método para obtener una lista de todos los creadores en formato JSON.
	 * 
	 * @return una cadena JSON que representa la lista de creadores.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSONCreadores() throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarCreadores());
		
		return json;
		
	}
	
	/**
	 * Método para obtener una lista de todos los creadores.
	 * 
	 * @return una lista de todos los creadores.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public ArrayList<Usuario> listarCreadores() throws SQLException{
		
		String sql = "SELECT * FROM Usuarios WHERE Rol = 'Creador'";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> result = new ArrayList<>();
	    while(rs.next()){
	        result.add(new Usuario(
	            rs.getString("email"), 
	            rs.getString("nombre"), 
	            rs.getString("apellidos"), 
	            rs.getString("razonSocial"), 
	            rs.getString("dnicif"), 
	            rs.getString("direccionLinea1"), 
	            rs.getString("direccionLinea2"), 
	            rs.getString("ciudad"), 
	            rs.getString("provincia"), 
	            rs.getString("cp"), 
	            rs.getString("telefono"), 
	            rs.getString("rol"), 
	            rs.getBoolean("boletin"), 
	            rs.getString("categoria"), 
	            rs.getString("portfolio"), 
	            rs.getString("bio"), 
	            rs.getString("pass"),
	            rs.getString("fotoPerfil")
	        ));
	    }
	    return result;
	}

	/**
	 * Método para obtener una lista de creadores por categoría en formato JSON.
	 * 
	 * @param categoria la categoría de los creadores a listar.
	 * @return una cadena JSON que representa la lista de creadores de la categoría especificada.
	 * @throws SQLException si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSONCategorias(String categoria) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarXCategorias(categoria));
		
		return json;
		
	}
	
	
	/**
	 * Método para devolver una lista de usuarios que pertenecen a una categoría específica y tienen el rol de "Creador".
	 * 
	 * @param categoria La categoría de los usuarios que se desean listar.
	 * @return Una lista de objetos Usuario que pertenecen a la categoría especificada y tienen el rol de "Creador".
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	
	public ArrayList<Usuario> listarXCategorias(String categoria) throws SQLException{
		
		String sql = "SELECT * FROM Usuarios WHERE Rol = 'Creador' AND Categoria = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, categoria);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> result = new ArrayList<>();
	    while(rs.next()){
	        result.add(new Usuario(
	            rs.getString("email"), 
	            rs.getString("nombre"), 
	            rs.getString("apellidos"), 
	            rs.getString("razonSocial"), 
	            rs.getString("dnicif"), 
	            rs.getString("direccionLinea1"), 
	            rs.getString("direccionLinea2"), 
	            rs.getString("ciudad"), 
	            rs.getString("provincia"), 
	            rs.getString("cp"), 
	            rs.getString("telefono"), 
	            rs.getString("rol"), 
	            rs.getBoolean("boletin"), 
	            rs.getString("categoria"), 
	            rs.getString("portfolio"), 
	            rs.getString("bio"), 
	            rs.getString("pass")
	        ));
	    }
	    return result;
	}
	
	
}	