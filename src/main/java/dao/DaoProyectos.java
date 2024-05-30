package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Proyecto;
import modelo.Usuario;

public class DaoProyectos {
	
	private Connection con = null; 
	private static DaoProyectos  instance =  null;
	
	public DaoProyectos() throws SQLException {
		
		con = DBconexion.getConexion();
		
	}
		
	// Patrón Singleton. Para no tener que instanciar cada vez que llamo al método
		 
	public static DaoProyectos getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoProyectos();
		}
		
		return instance;
	}

		
	/**
	 * Método para insertar un nuevo Usuario en la base de datos. Registro a través del formulario web.
	 * @param u
	 * @throws SQLException
	 */
	public void insertar(Proyecto p) throws SQLException {
		
		String sql = "INSERT INTO Proyectos (TituloProyecto,Categoria,Descripcion,FechaEntrega,ArchivoAdjunto,Estado,Dnicif_Empresa) VALUES (?,?,?,?,?,'Sin asignar',?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getTituloProyecto());
			ps.setString(2, p.getCategoria());
			ps.setString(3, p.getDescripcion());
			ps.setString(4, p.getFechaEntrega());
			ps.setString(5, p.getArchivoAdjunto());
			ps.setString(6, p.getDnicif_Empresa());
			
			int filas = ps.executeUpdate();
			System.out.println("Filas insertadas: " + filas);
			
			ps.close();
		
	}

	public void actualizar(Proyecto p) throws SQLException {
		
		String sql = "UPDATE Proyectos SET TituloProyecto=?,Categoria=?,Descripcion=?,FechaEntrega=?, ArchivoAdjunto=? WHERE ID=?";
		
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
	//Hacer un método como este pero para cambiar la contraseña de Usuario

	public void borrar(int idProyecto) throws SQLException {
		
		String sql = "DELETE FROM Proyectos WHERE idProyecto=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,idProyecto);
		
			int filas = ps.executeUpdate();
			System.out.println("Filas borradas: " + filas);
			
			ps.close();
	}
	
	
	//Esto es una lista de un solo elemento

	public Proyecto obtenerPorId(int idProyecto) throws SQLException {
		
		String sql = "SELECT * FROM Proyectos WHERE idProyecto=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idProyecto);
		
			ResultSet rs = ps.executeQuery();
		
			rs.next(); //Muevo el puntero una posición 
	
		Proyecto p = new Proyecto(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));

		return p;
	}
	
	//Método para listar los proyectos
	
	public ArrayList<Proyecto> listar() throws SQLException{
		
		String sql = "SELECT * FROM Proyectos";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Proyecto> result = null;
		
		while(rs.next()){
			
			if (result == null){
				result = new ArrayList<Proyecto>();
			}
			
			result.add(new Proyecto(rs.getInt("idProyecto"), rs.getString("tituloProyecto"), rs.getString("categoria"), rs.getString("descripcion"), rs.getString("fechaEntrega"), rs.getString("archivoAdjunto"), rs.getString("estado")));
		}
		
		return result;
	 	
	}
	
	//Método para listar por un dato:
	
/*	public Usuario listar(String categoria) throws SQLException{
		
		String sql = "SELECT * FROM Proyecto WHERE categoria=?";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoria);
			
			ResultSet rs = ps.executeQuery();
		
		ArrayList<Proyecto> ls = null;
	
		rs.next();	
		
		Proyecto p = new Proyecto(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
			
		return p;
	
	}
	
	
	//Método para comprobar en la bd si ya existe el usuario
	 
	 private boolean existe (Usuario u) {
	 
	 + sentencia sql con lo que quieras comprobar
	 y en el value ponemos lo que el usuario nos está dando
	 
	 return true o false
	 
	 *Esto se haría antes del método insertar
	 
	 }
	 
*/
	
	//Método para convertir los objetos a JSON
	
	public String listarJSON() throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar());
		
		return json;
		
	}


}
