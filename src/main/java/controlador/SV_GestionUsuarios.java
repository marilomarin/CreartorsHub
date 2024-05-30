package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.Proyecto;
import modelo.Usuario;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoUsuarios;

/**
 * Servlet implementation class ListarProyectos
 */

@WebServlet("/SV_GestionUsuarios")

@MultipartConfig

public class SV_GestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_GestionUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		HttpSession sesion = request.getSession();
		
		
	//	if(sesion.getAttribute("rol").equals("Admin")) { <- PARA ADMINISTRADORES
		
		
		PrintWriter out = response.getWriter();
		
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		
		// OP. MODIFICAR
		if(opcion == 2) {

			String dnicif = (String) sesion.getAttribute("dnicif");
			
			System.out.println("Estoy en la opción 2 para modificar Usuarios y el DNICIF es " + dnicif);
			
			Usuario u = new Usuario();
			
			try {
				u.obtenerUsuario(dnicif);
				out.print(u.listarUsuarios());
				System.out.println(u.listarUsuarios());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		// OP. LISTAR	
		}else if(opcion == 1) {
			DaoUsuarios usuarios;
			
			try {
				usuarios = new DaoUsuarios();
				out.print(usuarios.listarJSONCreadores());
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// OP. BORRAR
		}else if(opcion==3){
			
			try {
				String dnicif = request.getParameter("dnicif");
				
				DaoUsuarios usuarios = new DaoUsuarios();
				usuarios.borrar(dnicif);
				
				System.out.println("Se ha borrado el usuario con DNI/CIF: " + dnicif);
				
				out.print(usuarios.listarJSON(dnicif));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// OP. LISTAR POR CATEGORIAS	
		}else if(opcion == 4) {
			DaoUsuarios usuarios;
			
			try {
				usuarios = new DaoUsuarios();
				out.print(usuarios.listarJSONCategorias("categoria"));
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}
		

		
		}				
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();

		
		// Método para LISTAR, enviando una respuesta JSON al cliente desde el servidor
		
				String respuestaJSON;
				
				try {
					String dnicif = (String) sesion.getAttribute("dnicif");
					respuestaJSON = DaoUsuarios.getInstance().listarJSON(dnicif);
					
					System.out.println(respuestaJSON);
					
					PrintWriter respuesta = response.getWriter();
					
					respuesta.print(respuestaJSON);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		// Para actualizar los datos
				
		request.getSession();
		
		String dnicif = (String) sesion.getAttribute("dnicif");
		
		String nombre = request.getParameter("edit-nombre");
		String apellidos = request.getParameter("edit-apellidos");
		String razonSocial = request.getParameter("edit-razonsocial");
		String direccionLinea1 = request.getParameter("edit-direccion1");
		String direccionLinea2 = request.getParameter("edit-direccion2");
		String ciudad = request.getParameter("edit-ciudad");
		String provincia = request.getParameter("edit-provincia");
		String cp = request.getParameter("edit-cp");
		String telefono = request.getParameter("edit-telefono");
		String pass = request.getParameter("edit-pass");
		
		
		Usuario u;
		
		try {
			
			u = new Usuario(nombre, apellidos, razonSocial, direccionLinea1, direccionLinea2, ciudad, provincia, cp, telefono, pass);
			
			if (dnicif.isEmpty()) {
				
				System.out.println("-- EL USER NO EXISTE");
				
				DaoUsuarios dao = new DaoUsuarios();
				u.registrarseEmpresa();
				
				
			}else {
				
				System.out.println("-- EL USER EXISTE");
				
				u.setDnicif(dnicif);		
				u.modificarDatos();
				

			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("El usario no se ha podido modificar.");
		}
		
	
		response.sendRedirect("back-empresa.jsp");


	}
}


	
