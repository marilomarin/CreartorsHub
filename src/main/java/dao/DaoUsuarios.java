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
	
	public Usuario obtenerPorDnicif(String dnicif) throws SQLException {

	    String sql = "SELECT * FROM Usuarios WHERE DNICIF=?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	    	
	        ps.setString(1, dnicif);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	        	
	            if (rs.next()) {
	            	//Aquí ponemos todos los campos del Usuario
	                return new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getBoolean(13), rs.getString(14), rs.getString(15));
	                
	            } else {
	                throw new SQLException("No se encontró el usuario con DNI/CIF: " + dnicif);
	            }
	        }
	    }
	}
	

	public void actualizar(Usuario u) throws SQLException {
		
		String sql = "UPDATE Usuarios SET Nombre=?,Apellidos=?,RazonSocial=?,Direccion1=?,Direccion2=?,Ciudad=?,Provincia=?,Cp=?,Telefono=?,Pass=? WHERE Dnicif=?";
		
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
	
	//Hacer un método como este pero para cambiar la contraseña de Usuario !!!!!!!!!!!!

	
	public void borrar(String dnicif) throws SQLException {
		
		String sql = "DELETE FROM Usuarios WHERE DNICIF=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,dnicif);
		
			int filas = ps.executeUpdate();
			System.out.println("Filas borradas: " + filas);
			
			ps.close();
	}
	
	
	//Método para convertir los objetos a JSON
	
	public String listarJSON(String dnicif) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar(dnicif));
		
		return json;
		
	}
	
	//Método para listar los proyectos
	
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
	
	
}	