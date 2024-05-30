package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Candidatura;
import modelo.Proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.DaoCandidaturas;
import dao.DaoProyectos;

/**
 * Servlet implementation class SV_GestionCandidaturas
 */

@WebServlet

public class SV_GestionCandidaturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession sesion;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_GestionCandidaturas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		
		PrintWriter out = response.getWriter();

		int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
			
			System.out.println("Estoy en la opción principal del GET y el id es " + idProyecto);
		
			
			DaoCandidaturas candidaturas;
				
				try {
					//candidaturas = new DaoCandidaturas();
					//out.print(candidaturas.listarJSONCandidaturas(idProyecto));
										
					candidaturas = new DaoCandidaturas();
			        String jsonCandidaturas = candidaturas.listarJSONCandidaturas(idProyecto);
			        System.out.println("Candidaturas JSON: " + jsonCandidaturas);
			        out.print(jsonCandidaturas);
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		
		if(sesion.getAttribute("rol").equals("Creador")) {
			
			System.out.println("El usuario tiene rol de Creador y puede insertar candidaturas");
	
			// PARA INSERTAR/APLICAR
		    int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
		    double precio = Double.parseDouble(request.getParameter("precio"));
		    String dnicif_Creador = (String) sesion.getAttribute("dnicif");
		
		    Candidatura c = new Candidatura(idProyecto, dnicif_Creador, precio, "Pendiente");
        
		    try {
				c.aplicar();
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("No se ha podido insertar la candidtura en la base de datos");
			}
	        
	        response.sendRedirect("back-creador.jsp#mis-proyectos");

	        
		}else if(sesion.getAttribute("rol").equals("Empresa"))  {
			
			System.out.println("El usuario tiene rol de Empresa y puede aceptar/rechazar candidaturas");
			
			// PARA GESTIONAR CANDIDATURAS
			// Leer el cuerpo de la solicitud JSON
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // Parsear el cuerpo de la solicitud JSON usando Gson
            Gson gson = new Gson();
            JsonObject jsonBody = gson.fromJson(sb.toString(), JsonObject.class);

            try {
                String idProyecto = jsonBody.get("idProyecto").getAsString();
                String dnicif_Creador = jsonBody.get("dnicif_Creador").getAsString();
                double precio = jsonBody.get("precio").getAsDouble();
                String estado = jsonBody.get("estado").getAsString();

                System.out.println("Datos de candidatura: " + idProyecto + ", " + dnicif_Creador + ", " + precio + ", " + estado);

                Candidatura c = new Candidatura(estado);
                int idInt = Integer.parseInt(idProyecto);
                c.setIdProyecto(idInt);
                c.setDnicif_Creador(dnicif_Creador); 
                c.setPrecio(precio); 
                
             // Debug: Verificar que el objeto Candidatura esté configurado correctamente
                System.out.println("Candidatura IDProyecto: " + c.getIdProyecto() + ", Tipo: " + Integer.valueOf(c.getIdProyecto()).getClass().getName());
                System.out.println("Candidatura Estado: " + c.getEstado() + ", Tipo: " + c.getEstado().getClass().getName());
                System.out.println("Candidatura Precio: " + c.getPrecio() + ", Tipo: " + Double.valueOf(c.getPrecio()).getClass().getName());
                System.out.println("Candidatura DNI/CIF Empresa: " + c.getDnicif_Creador() + ", Tipo: " + c.getDnicif_Creador().getClass().getName());

                
                try {
					c.actualizarEstado();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            } catch (NumberFormatException e) {
                e.printStackTrace();}
        }
    }
}

