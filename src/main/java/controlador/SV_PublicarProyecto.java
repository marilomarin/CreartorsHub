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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class SV_PublicarProyecto
 */


@WebServlet("/SV_PublicarProyecto")

@MultipartConfig

public class SV_PublicarProyecto extends HttpServlet {
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
    public SV_PublicarProyecto() {
        super();
        // TODO Auto-generated constructor stub
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
		
		HttpSession sesion = request.getSession();
		
		if(sesion.getAttribute("rol").equals("Empresa")) {
	
		
		String tituloProyecto = request.getParameter("titulo_proyecto");
		String categoria = request.getParameter("categoria");
		String descripcion = request.getParameter("descripcion");
		String fechaEntrega = request.getParameter("fecha_entrega");
		
		// Obtener el DNICIF de la sesión
        String dnicif_Empresa = (String) sesion.getAttribute("dnicif");
		
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
		
		
        Proyecto p = new Proyecto(tituloProyecto, categoria, descripcion, fechaEntrega, uniqueFileName,dnicif_Empresa);
        
     // Asignar el DNICIF de la empresa al proyecto
        p.setDnicif_Empresa(dnicif_Empresa);
     
        
        try {
			p.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("No se ha podido insertar en la base de datos");
		}
        
        response.sendRedirect("back-empresa.jsp#mis-proyectos");

        
	}else {
		System.out.println("El usuario no tiene permiso para realizar esta acción");
	}

	}
}


