package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoProyectos;
import dao.DaoUsuarios;
import dao.DaoUsuarios;

/**
 * La clase Usuario representa a los usuarios que participan en la aplicación.
 * Los usuarios pueden realizar unos métodos u otros en función de su rol, que puede ser administrador, creador o empresa.
 * 
 * @author Mariló Marín Moreno
 * @version 27/05/2024 V2.0
 */

public class Usuario {


	protected String email;
	protected String nombre;
	protected String apellidos;
	protected String razonSocial;
	protected String dnicif;
	protected String direccionLinea1;
	protected String direccionLinea2;
	protected String ciudad;
	protected String provincia;
	protected String cp;
	protected String telefono;
	protected String rol;
	protected boolean boletin;
	protected String categoria;
	protected String portfolio;
	protected String bio;
	protected String pass;
	protected String fotoPerfil;

// Constructores
	
	/**
     * Constructor general por defecto.
     */

	public Usuario() {
	}
	
	/**
     * Constructor general de usuario con todos los parámetros que pueden intervenir.
     * Incluye los campos de la bd especialmente diseñados para los usuarios con rol de Creador, como pueden ser la foto de perfil, la bio, portfolio o categoría.
     * 
     * @param email el email del usuario
     * @param nombre el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param razonSocial la razón social del usuario
     * @param dnicif el DNI o CIF del usuario
     * @param direccionLinea1 la primera línea de dirección del usuario
     * @param direccionLinea2 la segunda línea de dirección del usuario
     * @param ciudad la ciudad del usuario
     * @param provincia la provincia del usuario
     * @param cp el código postal del usuario
     * @param telefono el teléfono del usuario
     * @param rol el rol del usuario
     * @param boletin si el usuario está suscrito al boletín
     * @param categoria la categoría del usuario con rol de creador
     * @param portfolio el portafolio del usuario con rol de creador
     * @param bio la biografía del usuario con rol de creador
     * @param pass la contraseña del usuario
     * @param fotoPerfil la foto de perfil del usuario con rol de creador
     */
	
	
	public Usuario(String email, String nombre, String apellidos, String razonSocial, String dnicif, String direccionLinea1, String direccionLinea2, 
			String ciudad, String provincia, String cp, String telefono, String rol, boolean boletin, String categoria, String portfolio, String bio, String pass, String fotoPerfil) {

		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.dnicif = dnicif;
		this.direccionLinea1 = direccionLinea1;
		this.direccionLinea2 = direccionLinea2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.rol = rol;
		this.boletin = boletin;
		this.categoria = categoria;
		this.portfolio = portfolio;
		this.bio = bio;
		this.pass = pass;
		this.fotoPerfil = fotoPerfil;
		
		}
	
	/**
     * Constructor que incluye todos los campos necesario para insertar usuarios con rol de empresa.
     * Se excluyen atributos como la foto de perfil, la bio, portfolio o categoría.
     * 
     * @param email el email del usuario
     * @param nombre el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param razonSocial la razón social del usuario
     * @param dnicif el DNI o CIF del usuario.
     * @param direccionLinea1 la primera línea de dirección del usuario
     * @param direccionLinea2 la segunda línea de dirección del usuario
     * @param ciudad la ciudad del usuario
     * @param provincia la provincia del usuario
     * @param cp el código postal del usuario
     * @param telefono el teléfono del usuario
     * @param rol el rol del usuario
     * @param boletin si el usuario está suscrito al boletín
     * @param pass la contraseña del usuario
     */

	public Usuario(String email, String nombre, String apellidos, String razonSocial, String dnicif,
			String direccionLinea1, String direccionLinea2, String ciudad, String provincia, String cp, String telefono, String rol, boolean boletin, String pass) {

		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.dnicif = dnicif;
		this.direccionLinea1 = direccionLinea1;
		this.direccionLinea2 = direccionLinea2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.rol = rol;
		this.boletin = boletin;
		this.pass = pass;
	}
	
	/**
     * Constructor que excluye el email, el rol, el dnicif y los campos adicionales para los usuarios con rol de creador.
     * 
     * @param nombre el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param razonSocial la razón social del usuario
     * @param direccionLinea1 la primera línea de dirección del usuario
     * @param direccionLinea2 la segunda línea de dirección del usuario
     * @param ciudad la ciudad del usuario
     * @param provincia la provincia del usuario
     * @param cp el código postal del usuario
     * @param telefono el teléfono del usuario
     * @param boletin si el usuario está suscrito al boletín
     * @param pass la contraseña del usuario
     */
	
	public Usuario(String nombre, String apellidos, String razonSocial, String direccionLinea1, String direccionLinea2, String ciudad, String provincia, String cp, String telefono, boolean boletin, String pass) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.direccionLinea1 = direccionLinea1;
		this.direccionLinea2 = direccionLinea2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.boletin = boletin;
		this.pass = pass;
	}
	
	/**
     * Constructor que excluye el email, el dnicif, el rol, el boletín y los campos adicionales para los usuarios con rol de creador.
     * 
     * @param nombre el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param razonSocial la razón social del usuario
     * @param direccionLinea1 la primera línea de dirección del usuario
     * @param direccionLinea2 la segunda línea de dirección del usuario
     * @param ciudad la ciudad del usuario
     * @param provincia la provincia del usuario
     * @param cp el código postal del usuario
     * @param telefono el teléfono del usuario
     * @param pass la contraseña del usuario
     */
	
	public Usuario(String nombre, String apellidos, String razonSocial, String direccionLinea1, String direccionLinea2, String ciudad, String provincia, String cp, String telefono, String pass) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.direccionLinea1 = direccionLinea1;
		this.direccionLinea2 = direccionLinea2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.pass = pass;
	}
	
	/**
     * Constructor general que excluye todos los atributos del usuario con rol de creador, excepto la categoría.
     * 
     * @param email el email del usuario
     * @param nombre el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param razonSocial la razón social del usuario
     * @param dnicif el DNI o CIF del usuario
     * @param direccionLinea1 la primera línea de dirección del usuario
     * @param direccionLinea2 la segunda línea de dirección del usuario
     * @param ciudad la ciudad del usuario
     * @param provincia la provincia del usuario
     * @param cp el código postal del usuario
     * @param telefono el teléfono del usuario
     * @param rol el rol del usuario
     * @param boletin si el usuario está suscrito al boletín
     * @param categoria la categoría del usuario con rol de creador
     * @param pass la contraseña del usuario
     */
	
	public Usuario(String email, String nombre, String apellidos, String razonSocial, String dnicif,
			String direccionLinea1, String direccionLinea2, String ciudad, String provincia, String cp, String telefono, String rol, boolean boletin, String categoria, String pass) {

		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.dnicif = dnicif;
		this.direccionLinea1 = direccionLinea1;
		this.direccionLinea2 = direccionLinea2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.rol = rol;
		this.boletin = boletin;
		this.categoria = categoria;
		this.pass = pass;
		
		}
	
	/**
     * Constructor general que incluye todos los atributos del usuario con rol de creador, excepto la foto de perfil.
     * 
     * @param email el email del usuario
     * @param nombre el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param razonSocial la razón social del usuario
     * @param dnicif el DNI o CIF del usuario
     * @param direccionLinea1 la primera línea de dirección del usuario
     * @param direccionLinea2 la segunda línea de dirección del usuario
     * @param ciudad la ciudad del usuario
     * @param provincia la provincia del usuario
     * @param cp el código postal del usuario
     * @param telefono el teléfono del usuario
     * @param rol el rol del usuario
     * @param boletin si el usuario está suscrito al boletín
     * @param categoria la categoría del usuario con rol de creador
     * @param portfolio el portafolio del usuario con rol de creador
     * @param bio la biografía del usuario con rol de creador
     * @param pass la contraseña del usuario
     */
	
	public Usuario(String email, String nombre, String apellidos, String razonSocial, String dnicif, String direccionLinea1, String direccionLinea2, 
			String ciudad, String provincia, String cp, String telefono, String rol, boolean boletin, String categoria, String portfolio, String bio, String pass) {

		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.dnicif = dnicif;
		this.direccionLinea1 = direccionLinea1;
		this.direccionLinea2 = direccionLinea2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
		this.telefono = telefono;
		this.rol = rol;
		this.boletin = boletin;
		this.categoria = categoria;
		this.portfolio = portfolio;
		this.bio = bio;
		this.pass = pass;
		
		}
	
	/**
     * Constructor con solo email y contraseña, para el inicio de sesión.
     * 
     * @param email el email del usuario
     * @param pass la contraseña del usuario
     */
	
	public Usuario(String email, String pass) {

		this.email = email;
		this.pass = pass;
	}
	
	/**
     * Constructor con DNI/CIF, email, nombre y rol para identificar al usuario en la sesión.
     * 
     * @param dnicif el DNI o CIF del usuario
     * @param email el email del usuario
     * @param nombre el nombre del usuario
     * @param rol el rol del usuario
     */
	
	public Usuario(String dnicif, String email, String nombre, String rol) {

		this.dnicif = dnicif;
		this.email = email;
		this.nombre = nombre;
		this.rol = rol;
	}
	

// Getters & Setters
	
    /**
     * Obtiene el email del usuario.
     * 
     * @return el email del usuario
     */
	
	public String getEmail() {
		return email;
	}
	
	 /**
     * Establece el email del usuario.
     * 
     * @param email el email del usuario
     */

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
     * Obtiene el nombre del usuario.
     * 
     * @return el nombre del usuario
     */

	public String getNombre() {
		return nombre;
	}
	
	/**
     * Establece el nombre del usuario.
     * 
     * @param nombre el nombre del usuario
     */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	 /**
     * Obtiene los apellidos del usuario.
     * 
     * @return los apellidos del usuario
     */

	public String getApellidos() {
		return apellidos;
	}
	
	/**
     * Establece los apellidos del usuario.
     * 
     * @param apellidos los apellidos del usuario
     */

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
     * Obtiene la razón social del usuario.
     * 
     * @return la razón social del usuario
     */

	public String getRazonSocial() {
		return razonSocial;
	}
	
	/**
     * Establece la razón social del usuario.
     * 
     * @param razonSocial la razón social del usuario
     */

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	/**
     * Obtiene el DNI o CIF del usuario.
     * 
     * @return el DNI o CIF del usuario
     */

	public String getDnicif() {
		return dnicif;
	}
	
	/**
     * Establece el DNI o CIF del usuario.
     * 
     * @param dnicif el DNI o CIF del usuario
     */

	public void setDnicif(String dnicif) {
		this.dnicif = dnicif;
	}
	
	/**
     * Obtiene la primera línea de dirección del usuario.
     * 
     * @return la primera línea de dirección del usuario
     */

	public String getDireccionLinea1() {
		return direccionLinea1;
	}
	
	/**
     * Establece la primera línea de dirección del usuario.
     * 
     * @param direccionLinea1 la primera línea de dirección del usuario
     */

	public void setDireccionLinea1(String direccionLinea1) {
		this.direccionLinea1 = direccionLinea1;
	}
	
	/**
     * Obtiene la segunda línea de dirección del usuario.
     * 
     * @return la segunda línea de dirección del usuario
     */

	public String getDireccionLinea2() {
		return direccionLinea2;
	}
	
	/**
     * Establece la segunda línea de dirección del usuario.
     * 
     * @param direccionLinea2 la segunda línea de dirección del usuario
     */

	public void setDireccionLinea2(String direccionLinea2) {
		this.direccionLinea2 = direccionLinea2;
	}
	
	/**
     * Obtiene la ciudad del usuario.
     * 
     * @return la ciudad del usuario
     */

	public String getCiudad() {
		return ciudad;
	}
	
	/**
     * Establece la ciudad del usuario.
     * 
     * @param ciudad la ciudad del usuario
     */

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/**
     * Obtiene la provincia del usuario.
     * 
     * @return la provincia del usuario
     */

	public String getProvincia() {
		return provincia;
	}
	
	/**
     * Establece la provincia del usuario.
     * 
     * @param provincia la provincia del usuario
     */

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	/**
     * Obtiene el código postal del usuario.
     * 
     * @return el código postal del usuario
     */

	public String getCp() {
		return cp;
	}
	
	/**
     * Establece el código postal del usuario.
     * 
     * @param cp el código postal del usuario
     */

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	/**
     * Obtiene el teléfono del usuario.
     * 
     * @return el teléfono del usuario
     */

	public String getTelefono() {
		return telefono;
	}
	
	/**
     * Establece el teléfono del usuario.
     * 
     * @param telefono el teléfono del usuario
     */

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
     * Obtiene si el usuario está suscrito al boletín.
     * 
     * @return boletin true si el usuario está suscrito al boletín y false en caso contrario
     */

	public boolean isBoletin() {
		return boletin;
	}
	
	/**
     * Establece si el usuario está suscrito al boletín.
     * 
     * @param boletin 1 si el usuario está suscrito al boletín y 0 en caso contrario
     */

	public void setBoletin(boolean boletin) {
		this.boletin = boletin;
	}
	
	/**
     * Obtiene el rol del usuario.
     * 
     * @return el rol del usuario
     */

	public String getRol() {
		return rol;
	}
	
	/**
     * Establece el rol del usuario.
     * 
     * @param rol el rol del usuario
     */

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	/**
     * Obtiene la categoría del usuario, en caso de que tenga rol de creador.
     * 
     * @return la categoría del usuario con rol creador
     */
	
	
	public String getCategoria() {
		return categoria;
	}
	
	/**
    * Establece la categoría del usuario, en caso de que tenga rol de creador.
    * 
    * @param categoria la categoría del usuario con rol de creador
    */

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
     * Obtiene el portafolio del usuario, en caso de que tenga rol de creador.
     * 
     * @return el portafolio del usuario con rol de creador
     */
	
	
	public String getPortfolio() {
		return portfolio;
	}
	
	/**
     * Establece el portafolio del usuario, en caso de que tenga rol de creador.
     * 
     * @param portfolio el portafolio del usuario con rol de creador
     */

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	
	/**
     * Obtiene la biografía del usuario, en caso de que tenga rol de creador.
     * 
     * @return la biografía del usuario con rol de creador
     */
	
	
	public String getBio() {
		return bio;
	}
	
	/**
     * Establece la biografía del usuario, en caso de que tenga rol de creador.
     * 
     * @param bio la biografía del usuario con rol de creador
     */

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	/**
     * Obtiene la contraseña del usuario.
     * 
     * @return la contraseña del usuario
     */

	public String getPass() {
		return pass;
	}
	
	/**
     * Establece la contraseña del usuario.
     * 
     * @param pass la contraseña del usuario
     */

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/**
     * Obtiene la foto de perfil del usuario, en caso de que tenga rol de creador.
     * 
     * @return la foto de perfil del usuario con rol de creador
     */
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	/**
     * Establece la foto de perfil del usuario, en caso de que tenga rol de creador.
     * 
     * @param fotoPerfil la foto de perfil del usuario con rol de creador 
     */

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	
	/**
     * Método toString() que devuelve una representación en cadena del objeto Usuario, con todos sus atributos.
     * 
     * @return una cadena que representa el objeto Usuario
     */
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellidos=" + apellidos + ", razonSocial="
				+ razonSocial + ", dnicif=" + dnicif + ", direccionLinea1=" + direccionLinea1 + ", direccionLinea2="
				+ direccionLinea2 + ", ciudad=" + ciudad + ", provincia=" + provincia + ", cp=" + cp + ", telefono="
				+ telefono + ", rol=" + rol + ", boletin=" + boletin + ",  categoria=" + categoria +", portfolio=" + portfolio + ", bio=" 
				+ bio + ", pass=" + pass + ", fotoPerfil=" + fotoPerfil +"]";
	}

	
	/**
     * Método para iniciar sesión con la contraseña proporcionada.
     * 
     * @param pass la contraseña del usuario
     * @return true si la autenticación es exitosa y false en caso de que la contraseña y email no coincidan
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
	
		public boolean iniciarSesion(String pass) throws SQLException {
			
			boolean ok = false;
			
			DaoUsuarios dao = new DaoUsuarios();
			
			Usuario u = DaoUsuarios.getInstance().login(this, pass); // Esto conecta con la bd y comprueba que devuelve un objeto
			
			if(u != null) {
				
				ok = true;
				this.dnicif = u.getDnicif();
				this.email = u.getEmail();
				this.nombre = u.getNombre();
				this.rol = u.getRol();
				
			}
			
			return ok;
			
		}
		
	
	/**
     * Método para que un usuario se registre como <strong>creador</strong> en la base de datos.
     * Se lleva a cabo a través del DAO.
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
	
	public void registrarseCreador() throws SQLException {
		
		DaoUsuarios.getInstance().insertarCreador(this);
		
	}
	
	
	/**
     * Método para que un usuario se registre como <strong>empresa</strong> en la base de datos.
     * Se lleva a cabo a través del DAO.
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
	
	public void registrarseEmpresa() throws SQLException {
		
		DaoUsuarios.getInstance().insertarEmpresa(this);
				
	}
	
	/**
     * Método para modificar los datos del usuario en la base de datos.
     * Se lleva a cabo a través del DAO.
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
	
	public void modificarDatos() throws SQLException {
		
		DaoUsuarios dao = new DaoUsuarios();
		dao.actualizar(this);
				
	}
	
	/**
     * Método para borrar usuarios en la base de datos. Este método sólo puede usarse por usuarios con rol de <strong>administrador</strong>.
     * Se lleva a cabo a través del DAO.
     * 
     * @param dnicif el DNI o CIF del usuario
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
	
	public void borrar(String dnicif) throws SQLException {
		
		DaoUsuarios dao = new DaoUsuarios();
		dao.borrar(dnicif);
		
	}
	
	 /**
     * Método que obtiene el usuario de la base de datos por su DNI o CIF.
     * 
     * @param dnicif el DNI o CIF del usuario
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
	
	public void obtenerUsuario(String dnicif) throws SQLException {
		
		DaoUsuarios dao = new DaoUsuarios();
		Usuario u = dao.obtenerPorDnicif(dnicif);
			
		this.setEmail(u.getEmail());
		this.setNombre(u.getNombre());
		this.setApellidos(u.getApellidos());
		this.setRazonSocial(u.getRazonSocial());
		this.setDnicif(u.getDnicif());
		this.setDireccionLinea1(u.getDireccionLinea1());
		this.setDireccionLinea2(u.getDireccionLinea2());
		this.setCiudad(u.getCiudad());
		this.setProvincia(u.getProvincia());
		this.setCp(u.getCp());
		this.setTelefono(u.getTelefono());
		this.setRol(u.getRol());
		this.setBoletin(u.isBoletin());
		this.setCategoria(u.getCategoria());
		this.setPortfolio(u.getPortfolio());
		this.setBio(u.getBio());
		this.setPass(u.getPass());
		this.setFotoPerfil(u.getFotoPerfil());
		
			
	}
	
	/**
     * Método que devuelve una representación JSON del usuario.
     * 
     * @return una cadena JSON que representa al usuario
     */
	
	public String listarUsuarios() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
	



}