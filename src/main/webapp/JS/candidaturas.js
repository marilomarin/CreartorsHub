
window.addEventListener("DOMContentLoaded", function(){

    
	function misCandidaturas(){
		fetch('SV_GestionCandidaturas?op=1')
		.then(response => response.json())
		.then(data => pintarTabla(data))
		
	}
	
	
	window.pintarTabla = function pintarTabla(datos){
		
		let html = "<div id='contenedorTabla'><table class='tablaListado'>";
			
        for (var i = 0; i < datos.length; i++) {
       	 	html += "<tr class='fila'>";
        	html += "<td id='bloque'><strong>Proyecto:</strong><br> " + datos[i].tituloProyecto + "</td>";
        	
        	// Cambiar los nombres de las categorías para hacerlos más estéticos
    		let categoriaMostrada = datos[i].categoria;
    			if (datos[i].categoria == "contenido_UGC") {
        		categoriaMostrada = "UGC";
    			}else if (datos[i].categoria == "motions_graphics") {
        		categoriaMostrada = "Motion Graphics";
				}else if (datos[i].categoria == "artes_plasticas") {
        		categoriaMostrada = "Artes Plásticas";
				}else if (datos[i].categoria == "ilustracion") {
        		categoriaMostrada = "Ilustración";
				}else if (datos[i].categoria == "video") {
        		categoriaMostrada = "Vídeo";
        		}else if (datos[i].categoria == "fotografia") {
        		categoriaMostrada = "Fotografía";
				}
    
        	html += "<td id='bloque'><strong>Categoría:</strong><br> " + categoriaMostrada + "</td>";
      		html += "<td id='bloque'><strong>Fecha de entrega:</strong><br> " + datos[i].fechaEntrega + "</td>";
      		html += "<td id='bloque'><strong>Estado:</strong><br> ";
      			if (datos[i].estado === "Sin asignar") {
    			html += "<span id='open'>Recibiendo candidaturas</span>";
				} else if (datos[i].estado === "Asignado"){
    			html += "<span id='assigned'>Creator asignado</span>";
				} else if (datos[i].estado === "Cerrado"){
    			html += "<span id='closed'>Finalizado</span>";
				} else if (datos[i].estado === "Pendiente"){
    			html += "<span id='pending'>Pendiente de revisión</span>";
				}

      		html += "<td id='icono'><a href='#' onclick='verProyecto("+datos[i].idProyecto+")'><i class='fa-solid fa-eye'></i></a></td>";
      		// No mostrar el icono de "Modificar" si el proyecto ha finalizado
        	if (datos[i].estado === "Cerrado") {
            html += "<td></td>"; 
        	} else {
            html += "<td id='icono'><a href='#' onclick='mostrarFormularioModificar(" + datos[i].idProyecto + ", 2)'><i class='fa-solid fa-pen-to-square'></i></a></td>";
			}
      		html += "<td id='icono'><a href='#' onclick='borrar("+datos[i].idProyecto+")'><i class='fa-solid fa-trash'></i></a></td>";
      		// Añadir el icono de chat solo si el estado es "Creator asignado" o "Pendiente de revisión"
        	if (datos[i].estado === "Pendiente" || datos[i].estado === "Asignado") {
            html += "<td id='icono'><a href='#' "+datos[i].idProyecto+")'><i class='fa-solid fa-comments'></i></a></td>";
        	} else {
            html += "<td></td>"; 
        	}
      		
      		
      	  	html += "</tr>"; // Cierre de la fila
    }	
   			html += "</table>"; // Cierre del contenedor de resultados
	
		document.getElementById("listado-candidaturas").innerHTML = html;
	
			
	}
	
		
		window.onload = function() {
	
    	misCandidaturas();
    }
    
 
 });