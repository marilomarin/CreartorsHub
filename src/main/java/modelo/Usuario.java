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
 * La clase Usuario representa a un usuario genérico de la aplicación. Los
 * usuarios pueden tener diferentes roles, como administrador, creador o
 * empresa.
 * 
 * @author: Mariló Marín
 * @version: 2024 V1.0
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


// Constructores 

	public Usuario() {
	}

	
	
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
	
	public Usuario(String email, String pass) {

		this.email = email;
		this.pass = pass;
	}
	
	public Usuario(String dnicif, String email, String nombre, String rol) {

		this.dnicif = dnicif;
		this.email = email;
		this.nombre = nombre;
		this.rol = rol;
	}
	

// Getters & Setters
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDnicif() {
		return dnicif;
	}

	public void setDnicif(String dnicif) {
		this.dnicif = dnicif;
	}

	public String getDireccionLinea1() {
		return direccionLinea1;
	}

	public void setDireccionLinea1(String direccionLinea1) {
		this.direccionLinea1 = direccionLinea1;
	}

	public String getDireccionLinea2() {
		return direccionLinea2;
	}

	public void setDireccionLinea2(String direccionLinea2) {
		this.direccionLinea2 = direccionLinea2;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isBoletin() {
		return boletin;
	}

	public void setBoletin(boolean boletin) {
		this.boletin = boletin;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	
	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	
	
// ToString 
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellidos=" + apellidos + ", razonSocial="
				+ razonSocial + ", dnicif=" + dnicif + ", direccionLinea1=" + direccionLinea1 + ", direccionLinea2="
				+ direccionLinea2 + ", ciudad=" + ciudad + ", provincia=" + provincia + ", cp=" + cp + ", telefono="
				+ telefono + ", rol=" + rol + ", boletin=" + boletin + ",  categoria=" + categoria +", portfolio=" + portfolio + ", bio=" + bio + ", pass=" + pass + "]";
	}

	
	// Método para iniciar sesión
	
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
		
	
// Método para insertar en bd
	
	public void insertar() throws SQLException {
		
		/*DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);*/
		
		DaoUsuarios.getInstance().insertar(this);
		
				
	}
	
	public void actualizar() throws SQLException {
		
		DaoUsuarios dao = new DaoUsuarios();
		dao.actualizar(this);
		
		//DaoProyectos.getInstance().actualizar(this);
		
				
	}
	
	
	public void borrar(String dnicif) throws SQLException {
		
		DaoUsuarios dao = new DaoUsuarios();
		dao.borrar(dnicif);
		
		//DaoProyectos.getInstance().borrar(id);
		
				
	}
	
	public void obtenerPorDnicif(String dnicif) throws SQLException {
		
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
		
			
	}
	
	
	public String dameJson() {
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		return json;
		
	}
	



}