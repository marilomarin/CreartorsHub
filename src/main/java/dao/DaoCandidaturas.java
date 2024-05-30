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
import jakarta.servlet.http.HttpSession;
import modelo.Candidatura;
import modelo.Proyecto;


/**
 * Clase que gestiona las operaciones relacionadas con las candidaturas a proyectos, interactuando con la tabla 'Candidaturas' de la bd.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class DaoCandidaturas {
	
	private Connection con = null; 
	private static DaoCandidaturas  instance =  null; 
	
	/**
     * Constructor para establecer la conexión a la base de datos.
     * La conexión se obtiene mediante el método 'getConexion()' de la clase 'DBconexion'.
     * 
     * @throws SQLException Si hay un error al establecer la conexión con la base de datos
     */
	
	public DaoCandidaturas() throws SQLException {
		
		con = DBconexion.getConexion();
		
	}
		
	/**
     * Método estático que devuelve la instancia única de la clase DaoCandidaturas.
     * 
     * 
     * @return La instancia única de la clase DaoCandidaturas
     * @throws SQLException Si hay un error al establecer la conexión con la base de datos
     */
		 
	public static DaoCandidaturas getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoCandidaturas();
		}
		
		return instance;
	}

		
	/**
     * Método para insertar una nueva candidatura en la base de datos.
     * 
     * @param c La candidatura a insertar
     * @throws SQLException Si hay un error al ejecutar la consulta SQL
     */
	
	public void aplicar(Candidatura c) throws SQLException {
		
		String sql = "INSERT INTO Candidaturas (IdProyecto,Dnicif_Creador,Precio,Estado) VALUES (?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getIdProyecto());
			ps.setString(2, c.getDnicif_Creador());
			ps.setDouble(3, c.getPrecio());
			ps.setString(4, "Pendiente");
		
			
			int filas = ps.executeUpdate();
			System.out.println("Filas insertadas: " + filas);
			
			ps.close();
	}
	
	/**
     * Método para actualizar el estado de una candidatura y del proyecto asociado.
     * Además, abre un nuevo chat e inserta una línea de mensaje por defecto.
     * 
     * @param c La candidatura cuyo estado se actualizará
     * @param sesion La sesión del usuario actual
     * @throws SQLException Si hay un error al ejecutar la consulta SQL
     */
	
	public void actualizarEstado(Candidatura c, HttpSession sesion) throws SQLException {
	    
		// Verificar si el estado es "aceptada"
	    if (c.getEstado().equalsIgnoreCase("Aceptada")) {
	    	
	        String sql ="UPDATE Proyectos SET Estado = 'Asignado', DNICIF_Creador = ? WHERE IDProyecto = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, c.getDnicif_Creador());
	        ps.setInt(2, c.getIdProyecto());
	        
	        int filas = ps.executeUpdate(); 
	        System.out.println("Se han actualizado " + filas + " filas en la tabla Proyectos"); 

	        ps.close();
	        
	     // Obtener el DNICIF_Empresa desde la tabla Proyectos para insertar en Chats
	        String sqlEmpresa = "SELECT DNICIF_Empresa FROM Proyectos WHERE IDProyecto = ?";
	        
	        PreparedStatement psObtenerEmpresa = con.prepareStatement(sqlEmpresa);
	        
	        psObtenerEmpresa.setInt(1, c.getIdProyecto());

	        ResultSet rs = psObtenerEmpresa.executeQuery();
	        String dnicifEmpresa = null;
	        
	        if (rs.next()) {
	            dnicifEmpresa = rs.getString("DNICIF_Empresa");
	        }

	        rs.close();
	        psObtenerEmpresa.close();

	        if (dnicifEmpresa != null) {
	        
	        	
	        	//String rol = (String) sesion.getAttribute("rol");
	        	
	            // Insertar nueva línea en la tabla Chats
	            String sqlChat = "INSERT INTO Chats (IDProyecto, DNICIF_Creador, DNICIF_Empresa, Mensaje) VALUES (?, NULL, ?, ?)";
	            
	            PreparedStatement psChat = con.prepareStatement(sqlChat);
	            
	            psChat.setInt(1, c.getIdProyecto());
	            psChat.setString(2, dnicifEmpresa);
	            psChat.setString(3, "¡Hola! &#128075 Enhorabuena, te hemos seleccionado para nuestro proyecto :)");
	            
	            int filasChat = psChat.executeUpdate();
	            System.out.println("Se han insertado " + filasChat + " filas en la tabla Chats");

	            psChat.close();
	            
	        } else {
	            System.out.println("No se encontró el DNICIF_Empresa para el IDProyecto: " + c.getIdProyecto());
	        }
	    }
	    
	    // Actualizar el estado en la tabla Candidaturas
	    String sql = "UPDATE Candidaturas SET Estado=? WHERE IDProyecto=? AND Dnicif_Creador=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getEstado());
        ps.setInt(2, c.getIdProyecto());
        ps.setString(3, c.getDnicif_Creador());
       		
		int filas = ps.executeUpdate();
		System.out.println("Filas actualizadas: " + filas);
		
		ps.close();
	
}
	
	/**
     * Método para obtener una candidatura por su ID de proyecto.
     * 
     * @param idProyecto El ID del proyecto asociado a la candidatura
     * @return La candidatura asociada al ID de proyecto especificado
     * @throws SQLException Si hay un error al ejecutar la consulta SQL
     */
	
	public Candidatura obtenerPorId(int idProyecto) throws SQLException {

	    String sql = "SELECT * FROM Candidaturas WHERE idProyecto=?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	    	
	        ps.setInt(1, idProyecto);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	        	
	            if (rs.next()) {
	            	//Aquí ponemos todos los campos del Proyecto
	                return new Candidatura(
	                		rs.getInt(1), 
	                		rs.getString(2), 
	                		rs.getDouble(3), 
	                		rs.getString(4));
	                
	            } else {
	                throw new SQLException("No se encontró ninguna candidatura asociada al proyecto con id: " + idProyecto);
	            }
	        }
	    }
	}
	
	
	 /**
     * Método para listar las candidaturas en formato JSON.
     * 
     * @param idProyecto El ID del proyecto asociado a las candidaturas
     * @return Las candidaturas asociadas al ID de proyecto especificado en formato JSON
     * @throws SQLException Si hay un error al ejecutar la consulta SQL
     */
	
	public String listarJSONCandidaturas(int idProyecto) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarCandidatos(idProyecto));
		
		return json;
		
	}
	
	 /**
     * Método para obtener una lista de candidaturas asociadas a un proyecto.
     * 
     * @param idProyecto El ID del proyecto asociado a las candidaturas
     * @return Una lista de candidaturas asociadas al ID de proyecto especificado
     * @throws SQLException Si hay un error al ejecutar la consulta SQL
     */
	
	public ArrayList<Candidatura> listarCandidatos(int idProyecto) throws SQLException{
		
		String sql = "SELECT * FROM Candidaturas WHERE idProyecto=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idProyecto);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Candidatura> result = null;
		
		while(rs.next()){
			
			if (result == null){
				result = new ArrayList<Candidatura>();
			}
			
			result.add(new Candidatura(
					rs.getInt("idProyecto"), 
					rs.getString("dnicif_Creador"), 
					rs.getDouble("precio"), 
					rs.getString("estado")));
		}
		
		return result;
	}

	
}	