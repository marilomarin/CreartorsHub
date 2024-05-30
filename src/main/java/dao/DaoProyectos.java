package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Proyecto;

/**
 * Esta clase proporciona métodos para interactuar con la tabla 'Proyectos' de la base de datos.
 * Permite realizar operaciones como insertar, actualizar, obtener y borrar proyectos, así como listar proyectos según diferentes criterios como usuario, estado o categoría.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class DaoProyectos {
	
	private Connection con = null; 
	private static DaoProyectos  instance =  null;
	
	/**
     * Constructor para establecer la conexión a la base de datos.
     * La conexión se obtiene mediante el método 'getConexion()' de la clase 'DBconexion'.
     * 
     * @throws SQLException Si hay un error al establecer la conexión.
     */
	
	public DaoProyectos() throws SQLException {
		
		con = DBconexion.getConexion();
		
	}
		
	/**
     * Método estático que devuelve una instancia única de DaoProyectos utilizando el patrón Singleton.
     * 
     * @return Una instancia única de DaoProyectos.
     * @throws SQLException Si hay un error al establecer la conexión.
     */
		 
	public static DaoProyectos getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoProyectos();
		}
		
		return instance;
	}

		
	/**
     * Método para insertar un nuevo proyecto en la base de datos.
     * 
     * @param p El proyecto a insertar.
     * @throws SQLException Si hay un error al ejecutar la consulta SQL.
     */
	
	public void insertar(Proyecto p) throws SQLException {
		
		String sql = "INSERT INTO Proyectos (idProyecto,TituloProyecto,Categoria,Descripcion,FechaEntrega,ArchivoAdjunto,Estado,Entregable,Dnicif_Empresa,Dnicif_Creador) VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getIdProyecto());
			ps.setString(2, p.getTituloProyecto());			
			ps.setString(3, p.getCategoria());
			ps.setString(4, p.getDescripcion());
			ps.setString(5, p.getFechaEntrega());
			ps.setString(6, p.getArchivoAdjunto());
			ps.setString(7, "Sin asignar");
			ps.setString(8, p.getEntregable());
			ps.setString(9, p.getDnicif_Empresa());
			ps.setString(10, p.getDnicif_Creador());
			
			int filas = ps.executeUpdate();
			System.out.println("Filas insertadas: " + filas);
			
			ps.close();
		
	}
	
	/**
     * Método para actualizar un proyecto existente en la base de datos.
     * 
     * @param p El proyecto con los datos actualizados.
     * @throws SQLException Si hay un error al ejecutar la consulta SQL.
     */

	public void actualizar(Proyecto p) throws SQLException {
		
		String sql = "UPDATE Proyectos SET TituloProyecto=?,Categoria=?,Descripcion=?,FechaEntrega=?, ArchivoAdjunto=? WHERE IDProyecto=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getTituloProyecto());
			ps.setString(2, p.getCategoria());
			ps.setString(3, p.getDescripcion());
			ps.setString(4, p.getFechaEntrega());
			ps.setString(5, p.getArchivoAdjunto());
			ps.setInt(6, p.getIdProyecto());
		
			int filas = ps.executeUpdate();
			System.out.println("Filas actualizadas: " + filas);
			
			ps.close();
		
	}
	
	/**
     * Método para obtener un proyecto de la base de datos por su ID.
     * 
     * @param idProyecto El ID del proyecto a obtener.
     * @return El proyecto correspondiente al ID especificado.
     * @throws SQLException Si el proyecto no se encuentra en la base de datos.
     */
	
	public Proyecto obtenerPorId(int idProyecto) throws SQLException {

	    String sql = "SELECT * FROM Proyectos WHERE idProyecto=?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	    	
	        ps.setInt(1, idProyecto);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	        	
	            if (rs.next()) {
	            	//Aquí ponemos todos los campos del Proyecto
	                return new Proyecto(
	                		rs.getInt(1), 
	                		rs.getString(2), 
	                		rs.getString(3), 
	                		rs.getString(4), 
	                		rs.getString(5), 
	                		rs.getString(6), 
	                		rs.getString(7), 
	                		rs.getString(8),
	                		rs.getString(9),
	                		rs.getString(10));
	                
	            } else {
	                throw new SQLException("No se encontró el proyecto con id: " + idProyecto);
	            }
	        }
	    }
	}
	
	/**
     * Método para eliminar un proyecto de la base de datos por su ID.
     * 
     * @param idProyecto El ID del proyecto a eliminar.
     * @throws SQLException Si hay un error al ejecutar la consulta SQL.
     */

	public void borrar(int idProyecto) throws SQLException {
		
		String sql = "DELETE FROM Proyectos WHERE idProyecto=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,idProyecto);
		
			int filas = ps.executeUpdate();
			System.out.println("Filas borradas: " + filas);
			
			ps.close();
	}
	
	
// --------- MÉTODOS PARA LISTAR
	
	/**
	 * Método que devuelve una representación JSON de los proyectos asociados a una empresa especificada por su DNI/CIF.
	 * 
	 * @param dnicif_Empresa El DNI/CIF de la empresa.
	 * @return Una cadena JSON que contiene los proyectos asociados a la empresa.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSONProyectosXUsuario(String dnicif_Empresa) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarXUsuario(dnicif_Empresa));
		
		return json;
		
	}
	
	/**
	 * Método que devuelve una representación JSON de los proyectos creados por un usuario especificado por su DNI/CIF.
	 * 
	 * @param dnicif_Creador El DNI/CIF del creador.
	 * @return Una cadena JSON que contiene los proyectos creados por el usuario.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSONProyectosXUsuarioCreador(String dnicif_Creador) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarXUsuarioCreador(dnicif_Creador));
		
		return json;
		
	}
	
	/**
	 * Método para listar los proyectos asociados a una empresa especificada por su DNI/CIF.
	 * 
	 * @param dnicif El DNI/CIF de la empresa.
	 * @return Una lista de proyectos asociados a la empresa.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	public ArrayList<Proyecto> listarXUsuario(String dnicif) throws SQLException{
		
		String sql = "SELECT * FROM Proyectos WHERE DNICIF_Empresa = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dnicif);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Proyecto> result = null;
		
		while(rs.next()){
			
			if (result == null){
				result = new ArrayList<Proyecto>();
			}
			
			result.add(new Proyecto(
					rs.getInt("idProyecto"), 
					rs.getString("tituloProyecto"), 
					rs.getString("categoria"), 
					rs.getString("descripcion"), 
					rs.getString("fechaEntrega"), 
					rs.getString("archivoAdjunto"), 
					rs.getString("estado"), 
					rs.getString("entregable"), 
					rs.getString("dnicif_Empresa"),
					rs.getString("dnicif_Creador")));
		}
		
		return result;
	}
	
	/**
	 * Lista los proyectos creados por un usuario especificado por su DNI/CIF.
	 * 
	 * @param dnicif El DNI/CIF del creador.
	 * @return Una lista de proyectos creados por el usuario.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	public ArrayList<Proyecto> listarXUsuarioCreador(String dnicif) throws SQLException{
			
			String sql = "SELECT * FROM Proyectos WHERE DNICIF_Creador = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dnicif);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Proyecto> result = null;
			
			while(rs.next()){
				
				if (result == null){
					result = new ArrayList<Proyecto>();
				}
				
				result.add(new Proyecto(
						rs.getInt("idProyecto"), 
						rs.getString("tituloProyecto"), 
						rs.getString("categoria"), 
						rs.getString("descripcion"), 
						rs.getString("fechaEntrega"), 
						rs.getString("archivoAdjunto"), 
						rs.getString("estado"), 
						rs.getString("entregable"), 
						rs.getString("dnicif_Empresa"),
						rs.getString("dnicif_Creador")));
			}
			
			return result;
		}
		
	/**
	 * Método que devuelve una representación JSON de los proyectos que tienen un estado específico (por ejemplo, 'Sin asignar').
	 * 
	 * @return Una cadena JSON que contiene los proyectos en el estado especificado.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSONProyectosXEstado() throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarProyectosXEstado());
		
		return json;
		
	}
	
	/**
	 * Método para listar los proyectos que tienen un estado específico (por ejemplo, 'Sin asignar').
	 * 
	 * @return Una lista de proyectos en el estado especificado.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
		
	public ArrayList<Proyecto> listarProyectosXEstado() throws SQLException{
		
		String sql = "SELECT * FROM Proyectos WHERE Estado = 'Sin asignar'";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Proyecto> result = new ArrayList<>();
	    while(rs.next()){
	    	result.add(new Proyecto(
					rs.getInt("idProyecto"), 
					rs.getString("tituloProyecto"), 
					rs.getString("categoria"), 
					rs.getString("descripcion"), 
					rs.getString("fechaEntrega"), 
					rs.getString("archivoAdjunto"), 
					rs.getString("estado"), 
					rs.getString("entregable"), 
					rs.getString("dnicif_Empresa"),
					rs.getString("dnicif_Creador")));
	    }
	    return result;
	}
	
	/**
	 * Método que devuelve una representación JSON de los proyectos asociados a una categoría especificada.
	 * 
	 * @param categoria La categoría de los proyectos.
	 * @return Una cadena JSON que contiene los proyectos asociados a la categoría especificada.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	public String listarJSONProyectosXCategorias(String categoria) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listarProyectosXCategorias(categoria));
		
		return json;
		
	}
	
	/**
	 * Método para listar los proyectos asociados a una categoría especificada.
	 * 
	 * @param categoria La categoría de los proyectos.
	 * @return Una lista de proyectos asociados a la categoría especificada.
	 * @throws SQLException Si hay un error al ejecutar la consulta SQL.
	 */
	
	
	public ArrayList<Proyecto> listarProyectosXCategorias(String categoria) throws SQLException{
		
		String sql = "SELECT * FROM Proyectos WHERE Estado = 'Sin asignar' AND Categoria = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);		
		ps.setString(1, categoria);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Proyecto> result = new ArrayList<>();
	    while(rs.next()){
	    	result.add(new Proyecto(
					rs.getInt("idProyecto"), 
					rs.getString("tituloProyecto"), 
					rs.getString("categoria"), 
					rs.getString("descripcion"), 
					rs.getString("fechaEntrega"), 
					rs.getString("archivoAdjunto"), 
					rs.getString("estado"), 
					rs.getString("entregable"), 
					rs.getString("dnicif_Empresa"),
					rs.getString("dnicif_Creador")));
	    }
	    return result;
	}
	
	
}
