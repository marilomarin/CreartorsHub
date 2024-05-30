package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class SV_Sesion
 */

@WebServlet

public class SV_Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession sesion; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Sesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession sesion = request.getSession();
		
		System.out.println("Atributo 'nombre' en la sesión: " + sesion.getAttribute("nombre"));
		
        String nombreUsuario = (String) sesion.getAttribute("nombre");

        if (nombreUsuario != null) {
            System.out.println("Nombre capturado: " + nombreUsuario);
        } else {
            System.out.println("Nombre de usuario no encontrado en la sesión");
        }
		
		
	}
	 
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
