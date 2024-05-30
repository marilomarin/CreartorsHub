package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Chat representa los mensajes que intercambian los usuarios con roles de Creador y Empresa que están vinculados a un mismo proyecto.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class Chat {
	
	protected int idChat=0;
	protected int idProyecto;
	protected String dnicif_Creador; 
	protected String dnicif_Empresa; 
	protected String mensaje;
	protected Timestamp fecha;
	private String nombreCreador;  
    private String nombreEmpresa;
    protected String nombreOtroUsuario;
    
 // Constructores
    
	 /**
     * Constructor general por defecto.
     */
	
	public Chat() {
		
	}
	
	/**
     * Constructor con todos los parámetros para crear el chat.
     * 
     * @param idChat El ID del chat
     * @param idProyecto El ID del proyecto al que está vinculado
     * @param dnicif_Creador El DNI o CIF del creador al que está vinculado el proyecto
     * @param dnicif_Empresa El DNI o CIF de la empresa a la que está vinculado el proyecto
     * @param mensaje Un mensaje del chat
     * @param fecha La fecha y hora  en la que se envía el mensaje
     * @param nombreCreador El nombre del creador
     * @param nombreEmpresa El nombre de la empresa
     * @param nombreOtroUsuario El nombre del usuario que no envía el mensaje (destinatario o receptor)
     */

	public Chat(int idChat, int idProyecto, String dnicif_Creador, String dnicif_Empresa, String mensaje, Timestamp fecha, String nombreCreador, String nombreEmpresa, String nombreOtroUsuario) {
		
		this.idChat = idChat;
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.dnicif_Empresa = dnicif_Empresa;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.nombreCreador = nombreCreador;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreOtroUsuario = nombreOtroUsuario;
	}
	
	/**
     * Constructor que no incluye el nombre del destinatario o receptor.
     * 
     * @param idChat El ID del chat
     * @param idProyecto El ID del proyecto al que está vinculado
     * @param dnicif_Creador El DNI o CIF del creador al que está vinculado el proyecto
     * @param dnicif_Empresa El DNI o CIF de la empresa a la que está vinculado el proyecto
     * @param mensaje Un mensaje del chat
     * @param fecha La fecha y hora  en la que se envía el mensaje
     * @param nombreCreador El nombre del creador
     * @param nombreEmpresa El nombre de la empresa
     */
	
	public Chat(int idChat, int idProyecto, String dnicif_Creador, String dnicif_Empresa, String mensaje, Timestamp fecha, String nombreCreador, String nombreEmpresa) {
		
		this.idChat = idChat;
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.dnicif_Empresa = dnicif_Empresa;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.nombreCreador = nombreCreador;
        this.nombreEmpresa = nombreEmpresa;
	}
	
	/**
     * Constructor que no recoge los nombres de los participantes en el chat.
     * 
     * @param idProyecto El ID del proyecto al que está vinculado
     * @param dnicif_Creador El DNI o CIF del creador al que está vinculado el proyecto
     * @param dnicif_Empresa El DNI o CIF de la empresa a la que está vinculado el proyecto
     * @param mensaje Un mensaje del chat
     * @param fecha La fecha y hora  en la que se envía el mensaje
     */
	
	public Chat(int idProyecto, String dnicif_Creador, String dnicif_Empresa, String mensaje, Timestamp fecha) {
		
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.dnicif_Empresa = dnicif_Empresa;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}
	
	/**
     * Constructor que no recoge ni la fecha en la que se envió el mensaje ni los nombres de los participantes.
     * 
     * @param idChat El ID del chat
     * @param idProyecto El ID del proyecto al que está vinculado
     * @param dnicif_Creador El DNI o CIF del creador al que está vinculado el proyecto
     * @param dnicif_Empresa El DNI o CIF de la empresa a la que está vinculado el proyecto
     * @param mensaje Un mensaje del chat
     */
	
	
	public Chat(int idChat, int idProyecto, String dnicif_Creador, String dnicif_Empresa, String mensaje) {
		
		this.idChat = idChat;
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.dnicif_Empresa = dnicif_Empresa;
		this.mensaje = mensaje;
	}
	
	/**
     * Constructor sin el ID del chat, fecha en la que se envía los mensajes ni el nombre de los participantes.
     * 
     * @param idProyecto El ID del proyecto al que está vinculado
     * @param dnicif_Creador El DNI o CIF del creador al que está vinculado el proyecto
     * @param dnicif_Empresa El DNI o CIF de la empresa a la que está vinculado el proyecto
     * @param mensaje Un mensaje del chat
     */
	
	public Chat(int idProyecto, String dnicif_Creador, String dnicif_Empresa, String mensaje) {
		
		this.idProyecto = idProyecto;
		this.dnicif_Creador = dnicif_Creador;
		this.dnicif_Empresa = dnicif_Empresa;
		this.mensaje = mensaje;
	}
	
// Getters & Setters
	
	/**
     * Obtiene el ID del chat.
     * 
     * @return el ID del chat
     */

	public int getIdChat() {
		return idChat;
	}
	
	/**
     * Establece el ID del chat.
     * 
     * @param idChat el nuevo ID del chat
     */

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}
	
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
     * @param dnicif_Creador el DNI o CIF del creador
     */

	public void setDnicif_Creador(String dnicif_Creador) {
		this.dnicif_Creador = dnicif_Creador;
	}
	
	/**
     * Obtiene el DNI o CIF de la empresa.
     * 
     * @return el DNI o CIF de la empresa
     */

	public String getDnicif_Empresa() {
		return dnicif_Empresa;
	}
	
	/**
     * Establece el DNI o CIF de la empresa.
     * 
     * @param dnicif_Empresa el DNI o CIF de la empresa
     */

	public void setDnicif_Empresa(String dnicif_Empresa) {
		this.dnicif_Empresa = dnicif_Empresa;
	}
	
	/**
     * Obtiene el mensaje del chat.
     * 
     * @return el mensaje del chat
     */

	public String getMensaje() {
		return mensaje;
	}
	
	/**
     * Establece el mensaje del chat.
     * 
     * @param mensaje el nuevo mensaje del chat
     */

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
     * Obtiene la fecha y hora en la que se envía el mensaje.
     * 
     * @return la fecha y hora del mensaje
     */

	public Timestamp getFecha() {
		return fecha;
	}
	
	/**
     * Establece la fecha y hora del mensaje.
     * 
     * @param fecha la nueva fecha y hora del mensaje
     */

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	/**
     * Obtiene el nombre del usuario con rol de creador del chat.
     * 
     * @return el nombre del creador del chat
     */
	
	public String getNombreCreador() {
		return nombreCreador;
	}
	
	/**
     * Establece el nombre del usuario con rol creador del chat.
     * 
     * @param nombreCreador el nombre del creador del chat
     */

	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}
	
	/**
     * Obtiene el nombre del usuario con rol de empresa del chat.
     * 
     * @return el nombre de la empresa del chat
     */

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	/**
     * Establece el nombre del usuario con rol Empresa del chat.
     * 
     * @param nombreCreador el nombre de la empresa del chat
     */

	public void setNombreEmpresa(String nombreCreador) {
		this.nombreEmpresa = nombreCreador;
	}
	
	/**
     * Obtiene el nombre del otro usuario que participa en el chat, que no es el que envía el mensaje.
     * 
     * @return el nombre del otro usuario
     */
	
	public String getNombreOtroUsuario() {
		return nombreOtroUsuario;
	}
	
	/**
     * Establece el nombre del otro usuario que participa en el chat, y que no es el que envía el mensaje en ese momento.
     * 
     * @param nombreOtroUsuario el nuevo nombre de otro usuario
     */

	public void setNombreOtroUsuario(String nombreOtroUsuario) {
		this.nombreOtroUsuario = nombreOtroUsuario;
	}
	
	
	/**
     * Método toString() que devuelve una representación en cadena del objeto Chat, con todos sus atributos.
     * 
     * @return una cadena que representa el objeto Chat
     */

	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", idProyecto=" + idProyecto + ", dnicif_Creador=" + dnicif_Creador
				+ ", dnicif_Empresa=" + dnicif_Empresa + ", mensaje=" + mensaje + ", fecha=" + fecha
				+ ", nombreCreador=" + nombreCreador + ", nombreEmpresa=" + nombreEmpresa + ", nombreOtroUsuario="
				+ nombreOtroUsuario + "]";
	}
	

	
}

	

