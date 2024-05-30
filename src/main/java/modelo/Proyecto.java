package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoProyectos;
import dao.DaoUsuarios;

public class Proyecto {

	protected int idProyecto=0;
	protected String tituloProyecto;
	protected String categoria;
	protected String descripcion;
	protected String fechaEntrega;
	protected String archivoAdjunto;
	protected String estado;
	protected String dnicif_Empresa; 

	
// Constructores 

	public Proyecto() {
		}
	
	//Para la bbdd
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String estado, String dnicif_Empresa) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.estado = estado;
		this.dnicif_Empresa = dnicif_Empresa;
	}
	
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String dnicif_Empresa) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.dnicif_Empresa = dnicif_Empresa;
	}
	
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
	}
	
	public Proyecto(String tituloProyecto, String categoria, String descripcion, String fechaEntrega) {

		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
	}
	
	//Para el formulario
	public Proyecto(String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String dnicif_Empresa) {

		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.dnicif_Empresa = dnicif_Empresa;
	}
	
	//Para el formulario
	public Proyecto(String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto) {

		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
	}
	
	//Para el método post del servlet al actualizars
	//public Proyecto(String tituloProyecto2, String categoria2, String descripcion2, String fechaEntrega2,
	//		String archivoAdjunto2, String id2) {
		// TODO Auto-generated constructor stub
	//}
	

// Getters & Setters


	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getTituloProyecto() {
		return tituloProyecto;
	}

	public void setTituloProyecto(String tituloProyecto) {
		this.tituloProyecto = tituloProyecto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getArchivoAdjunto() {
		return archivoAdjunto;
	}

	public void setArchivoAdjunto(String archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getDnicif_Empresa() {
        return dnicif_Empresa;
    }

    public void setDnicif_Empresa(String dnicif_Empresa) {
        this.dnicif_Empresa = dnicif_Empresa;
    }
		
		
// Métodos CRUD
		
		public void insertar() throws SQLException {
			
			/*DaoProyecto dao = new DaoProyecto();
			dao.insertar(this);*/
			
			DaoProyectos.getInstance().insertar(this);
			
					
		}

		
		public void actualizar() throws SQLException {
			
			DaoProyectos dao = new DaoProyectos();
			dao.actualizar(this);
			
			//DaoProyectos.getInstance().actualizar(this);
			
					
		}
		
		
		public void borrar(int idProyecto) throws SQLException {
			
			DaoProyectos dao = new DaoProyectos();
			dao.borrar(idProyecto);
			
			//DaoProyectos.getInstance().borrar(id);
			
					
		}

			
	public void obtenerPorId(int idProyecto) throws SQLException {
			
		DaoProyectos dao = new DaoProyectos();
		Proyecto p = dao.obtenerPorId(idProyecto);
			
		this.setIdProyecto(p.getIdProyecto());
		this.setTituloProyecto(p.getTituloProyecto());
		this.setCategoria(p.getCategoria());
		this.setDescripcion(p.getDescripcion());
		this.setFechaEntrega(p.getFechaEntrega());
		this.setArchivoAdjunto(p.getArchivoAdjunto());
			
	}
	
	
	public String dameJson() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
		
// ToString - 
		
	@Override
	public String toString() {

		return "Proyecto [idProyecto=" + idProyecto + ", tituloProyecto=" + tituloProyecto + ", categoria=" + categoria + ", descripcion=" + descripcion + ", archivoAdjunto=" + archivoAdjunto +", estado=" + estado + ", dnicif_Empresa=" + dnicif_Empresa +"]";
	}

	

}
