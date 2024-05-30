package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoProyectos;
import dao.DaoUsuarios;

/**
 * La clase Proyecto representa los proyectos que publican los usuarios con rol de empresa, con sus detalles y métodos asociados, y a los que aplican los usuarios con rol de creador a través de la clase Candidatura.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class Proyecto {

	protected int idProyecto=0;
	protected String tituloProyecto;
	protected String categoria;
	protected String descripcion;
	protected String fechaEntrega;
	protected String archivoAdjunto;
	protected String estado;
	protected String entregable;
	protected String dnicif_Empresa; 
	protected String dnicif_Creador; 

	
// Constructores 
	
	/**
     * Constructor general por defecto.
     */

	public Proyecto() {
		}
	
	/**
     * Constructor con todos los atributos del proyecto.
     *
     * @param idProyecto      el ID del proyecto
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha máxima de entrega del proyecto
     * @param archivoAdjunto  el archivo adjunto del proyecto (lo adjunta la empresa)
     * @param estado          el estado del proyecto
     * @param entregable      el entregable del proyecto (lo adjunta el creador)
     * @param dnicif_Empresa  el DNI/CIF de la empresa
     * @param dnicif_Creador  el DNI/CIF del creador
     */
	
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String estado, String entregable, String dnicif_Empresa, String dnicif_Creador) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.estado = estado;
		this.entregable = entregable;
		this.dnicif_Empresa = dnicif_Empresa;
		this.dnicif_Creador = dnicif_Creador;	
	}
	
	/**
     * Constructor con todos los atributos del proyecto, excepto el entregable, que es el que gestionará el usuario con rol de creador, justo antes de finalizar el proyecto.
     *
     * @param idProyecto      el ID del proyecto
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha máxima de entrega del proyecto
     * @param archivoAdjunto  el archivo adjunto del proyecto (lo adjunta la empresa)
     * @param estado          el estado del proyecto
     * @param dnicif_Empresa  el DNI/CIF de la empresa
     * @param dnicif_Creador  el DNI/CIF del creador
     */
	
	
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String estado, String dnicif_Empresa, String dnicif_Creador) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.estado = estado;
		this.dnicif_Empresa = dnicif_Empresa;
		this.dnicif_Creador = dnicif_Creador;	
	}
	
	/**
     * Constructor con todos los atributos excepto el de entregable y DNI CIF del creador.
     *
     * @param idProyecto      el ID del proyecto
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha máxima de entrega del proyecto
     * @param archivoAdjunto  el archivo adjunto del proyecto (lo adjunta la empresa)
     * @param estado          el estado del proyecto
     * @param dnicif_Empresa  el DNI/CIF de la empresa
     */
	
	
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
	
	/**
     * Constructor con todos los atributos del proyecto, excepto entregable, estado y dnicif_Creador.
     * 
     * @param idProyecto      el ID del proyecto
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha máxima de entrega del proyecto
     * @param archivoAdjunto  el archivo adjunto del proyecto (lo adjunta la empresa)
     * @param dnicif_Empresa  el DNI/CIF de la empresa
     */
	
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String dnicif_Empresa) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.dnicif_Empresa = dnicif_Empresa;
	}
	
	/**
     * Constructor con los campos mínimos del proyecto, incluyendo el ID.
     * 
     * @param idProyecto  el ID del proyecto
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha máxima de entrega del proyecto
     */
	
	public Proyecto(int idProyecto, String tituloProyecto, String categoria, String descripcion, String fechaEntrega) {

		this.idProyecto = idProyecto;
		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
	}
	
	/**
     * Constructor con los campos mínimos del proyecto.
     * 
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha máxima de entrega del proyecto
     */
	
	public Proyecto(String tituloProyecto, String categoria, String descripcion, String fechaEntrega) {

		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
	}
	
	/**
     * Constructor con los campos necesarios para el formulario para publicar un proyecto, incluyendo dnicifEmpresa.
     *
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha de entrega del proyecto
     * @param archivoAdjunto  el archivo adjunto del proyecto (lo adjunta la empresa)
     * @param dnicif_Empresa  el DNI/CIF de la empresa
     */
	
	public Proyecto(String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto, String dnicif_Empresa) {

		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
		this.dnicif_Empresa = dnicif_Empresa;
	}
	
	/**
     * Constructor con los campos necesarios para el formulario para publicar un proyecto, excluyendo dnicifEmpresa.
     *
     * @param tituloProyecto  el título del proyecto
     * @param categoria       la categoría del proyecto
     * @param descripcion     la descripción del proyecto
     * @param fechaEntrega    la fecha de entrega del proyecto
     * @param archivoAdjunto  el archivo adjunto del proyecto (lo adjunta la empresa)
     */
	
	public Proyecto(String tituloProyecto, String categoria, String descripcion, String fechaEntrega, String archivoAdjunto) {

		this.tituloProyecto = tituloProyecto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.archivoAdjunto = archivoAdjunto;
	}
	

// Getters & Setters
	
	 /**
     * Obtiene el ID del proyecto.
     *
     * @return el ID del proyecto
     */

	public int getIdProyecto() {
		return idProyecto;
	}
	
	/**
     * Establece el ID del proyecto.
     *
     * @param idProyecto el ID del proyecto
     */

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	/**
     * Obtiene el título del proyecto.
     *
     * @return el título del proyecto
     */

	public String getTituloProyecto() {
		return tituloProyecto;
	}
	
	/**
     * Establece el título del proyecto.
     *
     * @param tituloProyecto el título del proyecto
     */

	public void setTituloProyecto(String tituloProyecto) {
		this.tituloProyecto = tituloProyecto;
	}
	
	/**
     * Obtiene la categoría del proyecto.
     *
     * @return la categoría del proyecto
     */

	public String getCategoria() {
		return categoria;
	}
	
	/**
     * Establece la categoría del proyecto.
     *
     * @param categoria la categoría del proyecto
     */

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
     * Obtiene la descripción del proyecto.
     *
     * @return la descripción del proyecto
     */

	public String getDescripcion() {
		return descripcion;
	}
	
	/**
     * Establece la descripción del proyecto.
     *
     * @param descripcion la descripción del proyecto
     */

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
     * Obtiene la fecha de entrega del proyecto.
     *
     * @return la fecha de entrega del proyecto
     */

	public String getFechaEntrega() {
		return fechaEntrega;
	}
	
	/**
     * Establece la fecha de entrega del proyecto.
     *
     * @param fechaEntrega la fecha de entrega del proyecto
     */

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	/**
     * Obtiene el archivo adjunto del proyecto (el que adjunta la empresa).
     *
     * @return el archivo adjunto del proyecto
     */

	public String getArchivoAdjunto() {
		return archivoAdjunto;
	}
	
	/**
     * Establece el archivo adjunto del proyecto (el que adjunta la empresa).
     *
     * @param archivoAdjunto el archivo adjunto del proyecto
     */
	public void setArchivoAdjunto(String archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
	
	/**
     * Obtiene el estado del proyecto.
     *
     * @return el estado del proyecto
     */

	public String getEstado() {
		return estado;
	}
	
	/**
     * Establece el estado del proyecto.
     *
     * @param estado el estado del proyecto
     */

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
     * Obtiene el entregable del proyecto (el que adjunta el creador).
     *
     * @return el entregable del proyecto
     */
	
	public String getEntregable() {
		return entregable;
	}
	
	/**
     * Establece el entregable del proyecto (el que adjunta el creador).
     *
     * @param entregable el entregable del proyecto
     */

	public void setEntregable(String entregable) {
		this.entregable = entregable;
	}
	
	/**
     * Obtiene el DNI o CIF de la empresa asociada al proyecto.
     *
     * @return el DNI o CIF de la empresa
     */
	
	public String getDnicif_Empresa() {
        return dnicif_Empresa;
    }
	
	/**
     * Establece el DNI o CIF de la empresa asociada al proyecto.
     *
     * @param dnicif_Empresa el DNI o CIF de la empresa
     */

    public void setDnicif_Empresa(String dnicif_Empresa) {
        this.dnicif_Empresa = dnicif_Empresa;
    }
    
    /**
     * Obtiene el DNI o CIF del creador asociado al proyecto.
     *
     * @return el DNI o CIF del creador
     */
    
	public String getDnicif_Creador() {
        return dnicif_Creador;
    }
	
	/**
     * Establece el DNI o CIF del creador asociado al proyecto.
     *
     * @param dnicif_Creador el DNI o CIF del creador.
     */

    public void setDnicif_Creador(String dnicif_Creador) {
        this.dnicif_Creador = dnicif_Creador;
    }
    
    
    
    /**
     * Método para insertar el proyecto en la base de datos.
     * Se gestiona a través del DAO.
     * 
     * @throws SQLException si ocurre un error al interactuar con la base de dato
     */
		
	public void insertar() throws SQLException {
	
		DaoProyectos.getInstance().insertar(this);
		
	}
		
	/**
	 * Actualiza el proyecto en la base de datos.
	 * Se gestiona a través del DAO.
	 * 
	 * @throws SQLException si ocurre un error al interactuar con la base de datos
	 */
		
	public void actualizar() throws SQLException {
		
		DaoProyectos dao = new DaoProyectos();
		dao.actualizar(this);
				
	}
		
	/**
	 * Método para eliminar el proyecto de la base de datos.
	 * Se gestiona a través del DAO.
	 * 
	 * @param idProyecto el ID del proyecto a eliminar
	 * @throws SQLException si ocurre un error al interactuar con la base de datos
	 */
		
	public void borrar(int idProyecto) throws SQLException {
		
		DaoProyectos dao = new DaoProyectos();
		dao.borrar(idProyecto);
				
	}
		
	/**
	 * Método para obtener un proyecto de la base de datos por su ID y actualizar los atributos de este.
	 * 
	 * @param idProyecto el ID del proyecto a obtener
	 * @throws SQLException si ocurre un error al interactuar con la base de datos
	 */
			
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
	
	/**
	 * Método para devolver una representación JSON del proyecto.
	 * 
	 * @return una cadena JSON que representa el proyecto
	 */
	
	public String listarProyectos() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
		
	
	/**
     * Método toString() que devuelve una representación en cadena del objeto Proyecto, con todos sus atributos.
     * 
     * @return una cadena que representa el objeto Proyecto
     */
	
	@Override
	public String toString() {

		return "Proyecto [idProyecto=" + idProyecto + ", tituloProyecto=" + tituloProyecto + ", categoria=" + categoria + ", descripcion=" + descripcion + ", archivoAdjunto=" + archivoAdjunto +
				", estado=" + estado + ", entregable=" + entregable + ", dnicif_Empresa=" + dnicif_Empresa +", dnicif_Creador=" + dnicif_Creador +"]";
	}

	

}
