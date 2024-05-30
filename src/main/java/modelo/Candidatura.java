package modelo;

import java.sql.SQLException;

/**
 * La clase Candidatura representa las candidaturas (optar a) que realizan los Usuarios con rol Creador a uno o varios proyectos.
 * @author: Mariló Marín Moreno
 * @version: 27/05/2024 V2.0
 */

import com.google.gson.Gson;

import dao.DaoCandidaturas;


/**
 * La clase Candidatura representa las solicitudes de vinculación que hacen los usuarios con rol de creador sobre uno o varios proyectos.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class Candidatura {
	
	protected int idProyecto;
	protected String dnicif_Creador;
	protected double precio;
	protected String estado;
	
// Constructores

	 /**
     * Constructor general por defecto.
     */
	
	public Candidatura() {	
	}
	
	/**
     * Constructor con todos los parámetros para crear la candidatura.
     * 
     * @param idProyecto el ID del proyecto al que se vincula la candidatura
     * @param dnicif_Creador el DNI o CIF del Creador que aplica
     * @param precio el precio que que marca el Creador como condidción a desarrollar el proyecto
     * @param estado el estado de en el que se encuentra la candidatura
     */
	
	public Candidatura(int idProyecto, String dnicif_Creador, double precio, String estado) {
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.precio = precio;
		this.estado = estado;
	}
	
	/**
     * Constructor con el estado de la candidatura.
     * 
     * @param estado el estado de en el que se encuentra la candidatura
     */

	public Candidatura(String estado) {
		this.estado = estado;
	}
	
	/**
     * Constructor con el ID del proyecto y el estado de la candidatura.
     * 
     * @param idProyecto el ID del proyecto al que se vincula la candidatura
     * @param estado el estado de en el que se encuentra la candidatura
     */
	
	public Candidatura(int idProyecto, String estado) {
		this.idProyecto = idProyecto;
		this.estado = estado;
	}
	
	/**
     * Constructor con el ID del proyecto, el DNI del Creador y el estado de la candidatura.
     * 
     * @param idProyecto el ID del proyecto al que se vincula la candidatura
     * @param dnicif_Creador el DNI o CIF del Creador que aplica
     * @param estado el estado de en el que se encuentra la candidatura
     */
	
	public Candidatura(int idProyecto, String dnicif_Creador, String estado) {
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.estado = estado;
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
     * @param idProyecto el nuevo ID del proyecto
     */

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	 /**
     * Obtiene el DNI o CIF del creador.
     * 
     * @return el DNI o CIF del creador
     */

	public String getDnicif_Creador() {
		return dnicif_Creador;
	}
	
	 /**
     * Establece el DNI o CIF del creador.
     * 
     * @param dnicif_Creador el nuevo DNI o CIF del creador
     */

	public void setDnicif_Creador(String dnicif_Creador) {
		this.dnicif_Creador = dnicif_Creador;
	}
	
	/**
     * Obtiene el precio vinculado a la candidatura, en decimal.
     * 
     * @return el precio vinculado a la candidatura, en decimal
     */

	public double getPrecio() {
		return precio;
	}
	
	/**
     * Establece el precio vinculado a la candidatura, en decimal.
     * 
     * @param precio el nuevo precio vinculado a la candidatura, en decimal
     */

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	 /**
     * Obtiene el estado en el que se encuentra la candidatura.
     * 
     * @return el estado en el que se encuentra la candidatura
     */

	public String getEstado() {
		return estado;
	}
	
	/**
     * Establece el estado en el que se encuentra la candidatura.
     * 
     * @param estado el nuevo estado en el que se encuentra la candidatura
     */

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	/**
     * Método toString() qe devuelve una representación en cadena de la candidatura completa.
     * 
     * @return una cadena que representa la candidatura
     */
	
	@Override
	public String toString() {
		return "Candidatura [idProyecto=" + idProyecto + ", dnicif_Creador=" + dnicif_Creador + ", precio=" + precio
				+ ", estado=" + estado + "]";
	}
	
	
	 /**
     * Método para insertar una nueva candidatura a través del DAO.
     * 
     * @throws SQLException si ocurre un error en la base de datos
     */
	
	public void aplicar() throws SQLException {
		
		DaoCandidaturas.getInstance().aplicar(this);
			
	}
	
	/**
     * Método para obtener una candidatura localizándola por el ID del Proyecto vinculado.
     * 
     * @param idProyecto el ID del proyecto
     * @throws SQLException si ocurre un error en la base de datos
     */
	
	public void obtenerPorId(int idProyecto) throws SQLException {
		
		DaoCandidaturas dao = new DaoCandidaturas();
		Candidatura c = dao.obtenerPorId(idProyecto);
			
		this.setIdProyecto(c.getIdProyecto());
		this.setDnicif_Creador(c.getDnicif_Creador());
		this.setPrecio(c.getPrecio());
		this.setEstado(c.getEstado());
			
	}
	
	/**
     * Método para actualizar el estado de la candidatura a través del DAO.
     * 
     * @throws SQLException si ocurre un error en la base de datos
     */
	
	public void actualizarEstado() throws SQLException {
		
		DaoCandidaturas dao = new DaoCandidaturas();
		dao.actualizarEstado(this, null);
	
				
	}
	
	/**
     * Método para listar los diferentes candidatos en formato JSON.
     * 
     * @return una cadena JSON que representa los candidatos, con sus correspondientes atributos
     */
	
	
	public String listarCandidatos() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
	
	
}
