window.addEventListener("DOMContentLoaded", function(){

    
	function llamada(){
		fetch('SV_GestionProyectos?op=1')
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
	
		document.getElementById("listado").innerHTML = html;
	
			
	}
	
	
	// BOTÓN MODIFICAR
    window.mostrarFormularioModificar = function mostrarFormularioModificar(idProyecto, op) {
        fetch('SV_GestionProyectos?idProyecto=' + idProyecto + '&op=' + op)
            .then(response => response.json())
            .then(data => {
                // Rellenar el formulario con los datos del proyecto
                document.getElementById("idProyecto").value = data.idProyecto;
                document.getElementById("titulo_proyecto").value = data.tituloProyecto;
                document.getElementById("categoria").value = data.categoria;
                document.getElementById("descripcion").value = data.descripcion;
                document.getElementById("fecha_entrega").value = data.fechaEntrega;
                
                // Mostrar el formulario
                document.querySelector(".contenedorModificarProyectos").style.display = "block";
                
                // Ocultar el listado
            	document.getElementById("listado").style.display = "none";
            
            });
    };
    
    
    // BOTON PUBLICAR MODIFICACIONES
    // Función para ocultar el formulario cuando se envía
    document.querySelector("form[name='formulario-proyecto']").addEventListener("submit", function(event){
        document.querySelector(".contenedorModificarProyectos").style.display = "none";
    });
    

	
		window.onload = function() {
	
    	llamada();
    }
    
	
	
	});
	
	// BOTÓN CANCELAR MODIFICACIÓN (y mostrar el listado de nuevo)
	function cancelarModificacion() {
		
	    // Ocultar el formulario de modificación
	    document.querySelector(".contenedorModificarProyectos").style.display = "none";
	    
	    // Mostrar el listado de proyectos
	   document.getElementById("listado").style.display = "flex";
	   
	 }
	    

	
	// BOTÓN BORRAR
	function borrar(idProyecto){
		
		if(confirm("Por favor, confirma que quieres BORRAR los datos.")){
			fetch('SV_GestionProyectos?idProyecto='+idProyecto+'&op=3')
			.then(response => response.json())
			.then(data => pintarTabla(data))
		}else{
			
		}
	}
	
	// BOTÓN VER PROYECTO (POP-UP)
	function verProyecto(idProyecto) {
	    fetch('SV_GestionProyectos?idProyecto=' + idProyecto + '&op=2')
	        .then(response => response.json())
	        .then(data => {
	            // Rellenar la pop-up con los detalles del proyecto
	            document.getElementById("verProyecto").innerHTML = `
	                <h2>${data.tituloProyecto}</h2><br><br>
	                <p><strong>Categoría:</strong> ${data.categoria}</p><br><br>
	                <p><strong>Descripción:</strong> ${data.descripcion}</p><br><br>
	                <p><strong>Fecha máxima de entrega:</strong> ${data.fechaEntrega}</p><br><br>
	                <p><strong>Briefing del proyecto:</strong> <a href="${data.archivoAdjunto}" download class="button-descargar">Descargar <i class="fa-solid fa-file-arrow-down"></i></a></p><br><br>
	                <button class="cerrar-popup" onclick="cerrarPopup()"><i class="fa-solid fa-circle-xmark"></i> Volver al listado</button>
	            `;
	
	            // Mostrar la pop-up
	            document.getElementById("popVerProyecto").style.display = "block";
	        });
	}
	
			// Función para ocultar la pop-up
			function cerrarPopup() {
			    document.getElementById("popVerProyecto").style.display = "none";
			}

	


	
	