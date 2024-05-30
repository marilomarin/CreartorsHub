package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;

/**
 * Servlet implementation class SV_AltasUsuario
 */

@WebServlet("/SV_AltasUsuarios")

	public class SV_AltasUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_AltasUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Método para encriptar la  contraseña
    
   	public static String getMD5(String input) {
           try {
               MessageDigest md = MessageDigest.getInstance("MD5");
               byte[] messageDigest = md.digest(input.getBytes());
               BigInteger number = new BigInteger(1, messageDigest);
               String hashtext = number.toString(16);

               while (hashtext.length() < 32) {
                   hashtext = "0" + hashtext;
               }
               return hashtext;
           } catch (NoSuchAlgorithmException e) {
               throw new RuntimeException(e);
           }
       }
          

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String email = request.getParameter("email");
		String pass = getMD5(request.getParameter("pass")); 
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String razonSocial = request.getParameter("razon_social");
		String dnicif = request.getParameter("cif_nie");
		String direccionLinea1 = request.getParameter("direccion");
		String direccionLinea2 = request.getParameter("direccion2");
		String ciudad = request.getParameter("ciudad");
		String provincia = request.getParameter("provincia");
		String cp = request.getParameter("codigo_postal");
		String telefono = request.getParameter("telefono");
        String rol = request.getParameter("rol");
        boolean boletin = request.getParameter("suscribirse") != null;
        String categoria = request.getParameter("categoria");
        String portfolio = request.getParameter("portfolio");
        String bio = request.getParameter("bio");
      

        // Verificación para Empresas
        if (request.getParameter("rol").equals("creador")) {
            rol = "Creador";
        } else if (request.getParameter("rol").equals("empresa")){
        	rol = "Empresa";
        }
    

        
       if (request.getParameter("suscribirse") != null && request.getParameter("suscribirse").equals("si")) {
    	   boletin = true;
       		} else {
            boletin = false;
       		}
        
        
        Usuario u = new Usuario(email, nombre, apellidos, razonSocial, dnicif, direccionLinea1, direccionLinea2, ciudad, provincia, cp, telefono, rol, boletin, categoria, portfolio, bio, pass);
     
        
        try {
			u.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("No se ha podido insertar en la base de datos");
		}
        
        response.sendRedirect("login.html");
        
	}
     
	

}
