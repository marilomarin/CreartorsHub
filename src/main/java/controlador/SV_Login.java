package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.DaoUsuarios;


/**
 * Servlet implementation class SV_Login
 */

@WebServlet


public class SV_Login extends HttpServlet {
	
	//Instancio la sesión:
	HttpSession sesion; 
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
    
    
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	sesion.invalidate();
    	response.sendRedirect("login.html");
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
	    if ("logout".equals(action)) {
	        cerrarSesion(request, response);
	        
	        System.out.println("Se ha cerrado la sesión.");
	    }

	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		
		sesion = request.getSession();
		
		String email = request.getParameter("email");
		String pass = getMD5(request.getParameter("pass"));
		
		Usuario u = new Usuario();
		u.setEmail(email);
		
		try {
			if(u.iniciarSesion(pass)) {
				
				sesion = request.getSession();
				
				sesion.setAttribute("dnicif", u.getDnicif());
				sesion.setAttribute("nombre", u.getNombre());
				sesion.setAttribute("email", u.getEmail());
				sesion.setAttribute("rol", u.getRol());
				
				// Obtener los datos de la sesión
		        String nombre = (String) sesion.getAttribute("nombre");
		        String dnicif = (String) sesion.getAttribute("dnicif");
		        sesion.getAttribute("email");
		        String rol = (String) sesion.getAttribute("rol");
				
				// Imprimir los datos en la consola
				System.out.println("Se ha iniciado sesión. El usuario es: " + nombre + ", DNI/CIF " + dnicif + ", email " + email + ", con rol de " +rol );
		
				
				if(sesion.getAttribute("rol").equals("Empresa")) {
					
					response.sendRedirect("back-empresa.jsp");
				
				} else {
					
					response.sendRedirect("back-creador.jsp");
					
				}
			
			}else {
				
				System.out.println("No se ha podido iniciar sesión");
				
				response.sendRedirect("login.html?error=Los datos introducidos no son correctos");
				
				pw.close();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	}


		
	}
}

// Para el login, en las págs protegidas hay que preguntar si hay una sesión creada o no
// Y preguntamos por el id (para saber si está logueado) y después el permiso



