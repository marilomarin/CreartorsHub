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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Proyecto;
import modelo.Usuario;

public class DaoUsuarios {
	
	private Connection con = null; //esto quizás hay que ponerle static
	private static DaoUsuarios  instance =  null; //Esto quzás no sea necesario
	
	public DaoUsuarios() throws SQLException {
		
		con = DBconexion.getConexion();
		
	}
		
		/* 
		 * Patrón Singleton. Para no tener que instanciar cada vez que llamo al método*/
		 
	public static DaoUsuarios getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoUsuarios();
		}
		
		return instance;
	}

		
	/**
	 * Método para insertar un nuevo Usuario en la base de datos. Registro a través del formulario web.
	 * @param u
	 * @throws SQLException
	 */
	public void insertar(Usuario u) throws SQLException {
		
		System.out.println("Contraseña recibida para insertar en la base de datos: " + u.getPass());

		
		String sql = "INSERT INTO Usuarios (Email,Nombre,Apellidos,RazonSocial,DNICIF,Direccion1,Direccion2,Ciudad,Provincia,CP,Telefono,Rol,Boletin,Categoria,Pass) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			ps.setString(15, u.getPass());
		
			
			int filas = ps.executeUpdate();
			System.out.println("Filas insertadas: " + filas);
			
			ps.close();
		
		
	}

	public Usuario login(Usuario u, String pass) throws SQLException {
	
		
		String sql = "SELECT * FROM Usuarios WHERE Email=? AND Pass=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getEmail()); 
		ps.setString(2, pass);
	
		ResultSet rs = ps.executeQuery();
		
		/*rs.next();*/
		
	    if (rs.next()) { // Verificar si hay algún usuario en la bd antes de intentar acceder a él
	    	
	    	System.out.println("El usuario existe en la bd");
	        
	    	Usuario aux = new Usuario(rs.getString("dnicif"), rs.getString("email"), rs.getString("nombre"), rs.getString("rol"));
	        
	    	return aux;
	    	
	    } else {
	    	System.out.println("El usuario introducido NO existe en la bd");
	    	return null;
	        
	    }
	}
	
}	