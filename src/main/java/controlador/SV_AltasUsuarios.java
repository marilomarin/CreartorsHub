package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Usuario;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;

/**
 * Servlet implementation class SV_AltasUsuario
 */

@WebServlet("/SV_AltasUsuarios")

@MultipartConfig

	public class SV_AltasUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Aquí le digo a Java dónde guardar las fotos     
	
	private String pathFiles = "/Users/marilomarin/eclipse-workspace/CreatorsHub/src/main/webapp/img/perfiles";
	//Esta es la ruta local para probar ahora
	
	private File uploads = new File(pathFiles);
	//Cuando esté montado en el servidor, le pongo la ruta (/archivos), por ejemplo

      
       
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
            
            if (request.getParameter("suscribirse") != null && request.getParameter("suscribirse").equals("si")) {
         	   boletin = true;
            		} else {
                 boletin = false;
            		}
            
          //Lectura de los datos de la foto
     		Part part = request.getPart("foto_perfil");
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
     		}catch (Exception u) {
     			System.out.println("No se ha podido guarda la foto");
     		}
             
             Usuario u = new Usuario(email, nombre, apellidos, razonSocial, dnicif, direccionLinea1, direccionLinea2, ciudad, provincia, cp, telefono, rol, boletin, categoria, portfolio, bio, pass, uniqueFileName);
          
             
             try {
     			u.registrarseCreador();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			System.out.print("No se ha podido insertar en la base de datos");
     		}
             
             response.sendRedirect("login.html");
             
             
        } else if (request.getParameter("rol").equals("empresa")){
        	rol = "Empresa";
        	
        	if (request.getParameter("suscribirse") != null && request.getParameter("suscribirse").equals("si")) {
          	   boletin = true;
             		} else {
                  boletin = false;
             		}
             

            Usuario u = new Usuario(email, nombre, apellidos, razonSocial, dnicif, direccionLinea1, direccionLinea2, ciudad, provincia, cp, telefono, rol, boletin, categoria, portfolio, bio, pass);
         
            
            try {
    			u.registrarseEmpresa();
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.print("No se ha podido insertar en la base de datos");
    		}
            
            response.sendRedirect("login.html");
            
        	
        }
    

        
       
	}
     
	

}
