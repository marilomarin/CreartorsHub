package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import modelo.Chat;
import modelo.Proyecto;
import modelo.Usuario;


/**
 * Clase que gestiona las operaciones relacionadas con el chat de un proyecto, interactuando con la tabla 'Chats' de la bd.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

	public class DaoChat {
		
		private Connection con = null; 
		private static DaoChat  instance =  null;
		
		
		/**
	     * Constructor para establecer la conexión a la base de datos.
	     * La conexión se obtiene mediante el método 'getConexion()' de la clase 'DBconexion'.
	     * 
	     * @throws SQLException Si hay un error al establecer la conexión con la base de datos.
	     */
		
		public DaoChat() throws SQLException {
			
			con = DBconexion.getConexion();
			
		}
		
		/**
	     * Método estático que devuelve la instancia única de la clase DaoChat.
	     * 
	     * @return La instancia única de la clase DaoChat.
	     * @throws SQLException Si hay un error al establecer la conexión con la base de datos.
	     */
		 
	public static DaoChat getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoChat();
		}
		
		return instance;
	}
	
	/**
     * Método para enviar un mensaje en el chat de un proyecto.
     * 
     * @param idProyecto El ID del proyecto.
     * @param dnicifRemitente El DNI/CIF del remitente del mensaje.
     * @param dnicifDestinatario El DNI/CIF del destinatario del mensaje.
     * @param mensaje El contenido del mensaje.
     * @throws SQLException Si hay un error al ejecutar la consulta SQL.
     */
	
	public void enviarMensaje(int idProyecto, String dnicifRemitente, String dnicifDestinatario, String mensaje) throws SQLException {
       
		
		String sql = "INSERT INTO Chats (IDProyecto, DNICIF_Creador, DNICIF_Empresa, Mensaje) VALUES (?, ?, ?, ?)";
            
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProyecto);
            ps.setString(2, dnicifRemitente);
            ps.setString(3, dnicifDestinatario);
            ps.setString(4, mensaje);
            int filas = ps.executeUpdate();
            
            System.out.println("Mensajes enviados: " + filas);
            
            ps.close();
     
    }
	
	
	/**
     * Método para obtener el historial de mensajes por proyecto.
     * 
     * @param idProyecto El ID del proyecto.
     * @param sesion La sesión del usuario.
     * @return Una lista de mensajes del chat asociados al proyecto.
     * @throws SQLException Si hay un error al ejecutar la consulta SQL.
     */
	
	public ArrayList<Chat> obtenerMensajes(int idProyecto, HttpSession sesion) throws SQLException {
		
	    String rol = (String) sesion.getAttribute("rol");

	    String columnaDnicif;
	    
	    if (rol.equals("Creador")) {
	        columnaDnicif = "DNICIF_Creador";
	        
	    } else if (rol.equals("Empresa")) {
	        columnaDnicif = "DNICIF_Empresa";
	        
	    } else {
	        throw new IllegalArgumentException("Rol de usuario no válido: " + rol);
	    }

	    String sql = "SELECT c.idChat, c.idProyecto, c.dnicif_Creador, c.dnicif_Empresa, c.mensaje, c.fecha, " +
	                 "uc.Nombre AS nombreCreador, ue.Nombre AS nombreEmpresa " +
	                 "FROM Chats c " +
	                 "LEFT JOIN Usuarios uc ON c.dnicif_Creador = uc.DNICIF " +
	                 "LEFT JOIN Usuarios ue ON c.dnicif_Empresa = ue.DNICIF " +
	                 "WHERE c.IDProyecto = ?";

	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, idProyecto);

	    ResultSet rs = ps.executeQuery();

	    ArrayList<Chat> result = new ArrayList<>();

	    while (rs.next()) {
	        String nombreUsuario = rs.getString("nombreCreador");
	        if (rol.equals("Empresa")) {
	            nombreUsuario = rs.getString("nombreEmpresa");
	        }

	        result.add(new Chat(
	                rs.getInt("idChat"),
	                rs.getInt("idProyecto"),
	                rs.getString("DNICIF_Creador"),
	                rs.getString("DNICIF_Empresa"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fecha"),
	                nombreUsuario,
	                null)); // Puedes dejar la columna "nombreEmpresa" como null si no la necesitas en este punto
	    }

	    return result;
	}
	
	/**
     * Método para obtener el nombre de usuario asociado a un DNI/CIF.
     * 
     * @param dnicif El DNI/CIF del usuario.
     * @return El nombre del usuario.
     * @throws SQLException Si hay un error al ejecutar la consulta SQL.
     */
	
	public String obtenerNombreUsuario(String dnicif) throws SQLException {
		
	    String nombreUsuario = null;
	    
	    String sql = "SELECT Nombre FROM Usuarios WHERE DNICIF = ?";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, dnicif);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                nombreUsuario = rs.getString("Nombre");
	            }
	        }
	    }
	    
	    return nombreUsuario;
	}


}
