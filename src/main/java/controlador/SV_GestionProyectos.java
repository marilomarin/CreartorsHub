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

import dao.DaoProyectos;
import dao.DaoUsuarios;

/**
 * Servlet implementation class ListarProyectos
 */

@WebServlet("/SV_GestionProyectos")

@MultipartConfig

public class SV_GestionProyectos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession sesion;
	
	// Aquí le digo a Java dónde guardar las fotos     
	
	private String pathFiles = "/Users/marilomarin/eclipse-workspace/CreatorsHub/src/main/webapp/archivosSubidos";
	//Esta es la ruta local para probar ahora
	
	private File uploads = new File(pathFiles);
	//Cuando esté montado en el servidor, le pongo la ruta (/archivos), por ejemplo
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_GestionProyectos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Tengo que activar aquí también la sesión ¿?
		//sesion = request.getSession();

		HttpSession sesion = request.getSession();
		
		if(sesion.getAttribute("rol").equals("Empresa")) {
		
		
		PrintWriter out = response.getWriter();
		
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		
		// OP. MODIFICAR
		if(opcion == 2) {

			int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
			
			System.out.println("Estoy en la opción 2 del GET y el id es " + idProyecto);
			
			Proyecto p = new Proyecto();
			
			try {
				p.obtenerPorId(idProyecto);
				out.print(p.dameJson());
				System.out.println(p.dameJson());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		// OP. LISTAR	
		}else if(opcion == 1) {
			DaoProyectos proyectos;
			
			try {
				proyectos = new DaoProyectos();
				// ORIGINAL out.print(proyectos.listarJSON());
				String dnicif = (String) sesion.getAttribute("dnicif");
				out.print(proyectos.listarJSON(dnicif));
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// OP. BORRAR
		}else if(opcion==3){
			
			try {
				int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
				
				DaoProyectos proyectos = new DaoProyectos();
				proyectos.borrar(idProyecto);
				
				System.out.println("Se ha borrado el ID: " + idProyecto);
				
				String dnicif = (String) sesion.getAttribute("dnicif");
				out.print(proyectos.listarJSON(dnicif));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}
		
		}				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		if(sesion.getAttribute("rol").equals("Empresa")) {
		
		// Método para LISTAR, enviando una respuesta JSON al cliente desde el servidor
		
				String respuestaJSON;
				
				try {
					String dnicif = (String) sesion.getAttribute("dnicif");
					respuestaJSON = DaoProyectos.getInstance().listarJSON(dnicif);
					
					System.out.println(respuestaJSON);
					
					PrintWriter respuesta = response.getWriter();
					
					respuesta.print(respuestaJSON);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		// Para actualizar los datos
				
		request.getSession();
		
		if(sesion.getAttribute("rol").equals("Empresa")) {
		
		String tituloProyecto = request.getParameter("titulo_proyecto");
		String categoria = request.getParameter("categoria");
		String descripcion = request.getParameter("descripcion");
		String fechaEntrega = request.getParameter("fecha_entrega");
//		String archivoAdjunto = request.getParameter("archivo_adjunto");
		String idProyecto = request.getParameter("idProyecto");
		
		//Lectura de los datos del archivo
		Part part = request.getPart("archivo_adjunto");
		Path path = Paths.get(part.getSubmittedFileName()); //Para obtener el nombre del archivo
		String fileName = path.getFileName().toString();
		
		//Preparo el camino para enviar los datos (buffer):
		InputStream input = part.getInputStream();
		
		//Creo el contenedor donde lo voy a guardar:
		File file = new File (uploads, fileName); //PENDIENTE: Hay que crear la excepción para que no se ponga cualquier nombre, o no se repita
		String uniqueFileName = fileName;
		
		// Verificar si el archivo ya existe
		if (file.exists()) {
		    // Si el archivo existe, agregar un número adicional al nombre hasta encontrar un nombre único
		    int count = 1;
		    String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
		    String extension = fileName.substring(fileName.lastIndexOf('.'));
		    
		    do {
		        uniqueFileName = baseName + "_" + count + extension;
		        file = new File(uploads, uniqueFileName);
		        count++;
		    } while (file.exists());
		}
		
		
		//Copio los datos dentro del contenedor usando el buffer
		try {
		Files.copy(input, file.toPath());
		}catch (Exception p) {
			System.out.println("No se ha podido copiar el archivo");
		}
		
		Proyecto p;
		
		try {
			
			p = new Proyecto(tituloProyecto, categoria, descripcion, fechaEntrega, uniqueFileName);
			
			if (idProyecto.isEmpty()) {
				
				System.out.println("-- EL ID ESTÁ VACÍO");
				
				DaoProyectos dao = new DaoProyectos();
				p.insertar();
				
				//p.insertar();
				
			}else {
				
				System.out.println("-- EL ID NO ESTÁ VACÍO");
				
				int idInt = Integer.parseInt(idProyecto);
				p.setIdProyecto(idInt);		
				p.actualizar();
				
				
				//p.actualizar(Integer.parseInt(id));	

			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("El contenido no se ha podido modificar.");
		}
		
	
		response.sendRedirect("back-empresa.jsp#mis-proyectos");
	
	} else {
		System.out.println("El usuario no tiene permiso para realizar esta acción");
	}


		}
	}
}
