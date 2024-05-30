package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.DaoChat;

/**
 * Servlet implementation class SV_Chats
 */

@WebServlet

public class SV_Chats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Chats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 HttpSession sesion = request.getSession();
		 
	        String dnicif = (String) sesion.getAttribute("dnicif");
	        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
	        
	        try {
	            DaoChat daoChat = DaoChat.getInstance();
	            List<Chat> mensajes = daoChat.obtenerMensajes(idProyecto,sesion);
	            
	         // Obtener el nombre del otro usuario
	            String dnicifOtroUsuario = sesion.getAttribute("rol").equals("Creador") ? (String) sesion.getAttribute("dnicif_Empresa") : (String) sesion.getAttribute("dnicif_Creador");
	            String nombreOtroUsuario = daoChat.obtenerNombreUsuario(dnicifOtroUsuario);	
	            
	         // Agregar el nombre del otro usuario a cada mensaje
	            for (Chat mensaje : mensajes) {
	                mensaje.setNombreOtroUsuario(nombreOtroUsuario);
	            }

	            String respuestaJSON = new Gson().toJson(mensajes);

	            System.out.println(respuestaJSON);

	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            
	            PrintWriter out = response.getWriter();
	            
	            out.print(respuestaJSON);
	            out.flush();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            PrintWriter out = response.getWriter();
	            out.write("{\"error\": \"Error al obtener los mensajes del chat\"}");
	            out.flush();
	        }
	

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		HttpSession sesion = request.getSession();
		
        BufferedReader reader = request.getReader();
        
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        int idProyecto = jsonObject.get("idProyecto").getAsInt();
        String mensaje = jsonObject.get("mensaje").getAsString();
        
        String dnicifRemitente = (String) sesion.getAttribute("dnicif");
        String rol = (String) sesion.getAttribute("rol");

        String dnicifCreador = null;
        String dnicifEmpresa = null;

        if(sesion.getAttribute("rol").equals("Creador")) {
        	
            dnicifCreador = dnicifRemitente;
            dnicifEmpresa = (String) sesion.getAttribute("dnicif_Empresa");
            
        } else if(sesion.getAttribute("rol").equals("Empresa")) {
        	
            dnicifEmpresa = dnicifRemitente;
            dnicifCreador = (String) sesion.getAttribute("dnicif_Creador");
        }

        try {
            DaoChat daoChat = DaoChat.getInstance();
            daoChat.enviarMensaje(idProyecto, dnicifCreador, dnicifEmpresa, mensaje);

            response.setStatus(HttpServletResponse.SC_OK);
            String respuestaJSON = "{\"success\": \"Mensaje enviado\"}";

            PrintWriter out = response.getWriter();
            out.print(respuestaJSON);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            String respuestaJSON = "{\"error\": \"Error al guardar el mensaje\"}";

            PrintWriter out = response.getWriter();
            out.print(respuestaJSON);
            out.flush();
        }
    }

}
