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
        		}else if (datos[i].categoria == "fotografia") {
        		categoriaMostrada = "Fotografía";
				}
    
        	html += "<td id='bloque'><strong>Categoría:</strong><br> " + categoriaMostrada + "</td>";
      		html += "<td id='bloque'><strong>Plazo máximo:</strong><br> " + datos[i].fechaEntrega + "</td>";
      		html += "<td id='bloque'><strong>Estado:</strong><br> ";
      			if (datos[i].estado === "Sin asignar") {
    			html += "<span id='open'>Recibiendo candidaturas</span>";
				} else if (datos[i].estado === "Asignado"){
    			html += "<span id='assigned'>Abierto</span>";
				} else if (datos[i].estado === "Cerrado"){
    			html += "<span id='closed'>Finalizado</span>";
				} else if (datos[i].estado === "Pendiente"){
    			html += "<span id='pending'>Pendiente de revisión</span>";
				}

      		html += "<td id='icono'><a href='#' onclick='verProyecto("+datos[i].idProyecto+")'><i class='fa-solid fa-eye'></i></a></td>";
      		// Añadir el icono de chat solo si el proyecto está activo
        	if (datos[i].estado === "Pendiente" || datos[i].estado === "Asignado") {
            html += "<td id='icono'><a href='#' onclick='abrirChat("+datos[i].idProyecto+")'><i class='fa-solid fa-comments'></i></a></td>";
        	} else {
            html += "<td></td>"; 
        	}
      		
      		
      	  	html += "</tr>"; // Cierre de la fila
    }	
   			html += "</table>"; // Cierre del contenedor de resultados
	
		document.getElementById("listado").innerHTML = html;
	
			
	}

	
		window.onload = function() {
	
    	llamada();
    }
    
	
	
	});
	
	//BOTÓN CHAT (POP-UP)
	function abrirChat(idProyecto) {
		
	    console.log("Abriendo chat del proyecto con ID: " + idProyecto);
	    var nombreUsuario = document.getElementById("nombreUsuario").getAttribute("data-nombre");
		console.log("Nombre de usuario captado II para chat:", nombreUsuario);

	    
		    fetch('SV_Chats?idProyecto=' + idProyecto)
	        .then(response => response.json())
	        .then(data => {
				
				console.log(data);
				
	            // Historial de mensajes
		        let html = "<h2>Chat del Proyecto</h2><br><br>";
		        if (data.length > 0) {
		            html += "<ul class='chat-general'>";
		            data.forEach(mensaje => {
						let esMensajePropio = mensaje.nombreCreador === nombreUsuario;
		                let mensajeClase = esMensajePropio ? 'mensaje-propio' : 'mensaje-otro';
		                let nombreMostrar = esMensajePropio ? 'Tú escribiste' : 'El otro usuario escribió';
		                
		                html += "<div class='chat-mensaje " + mensajeClase + "'>";
		                html += "<span class='fecha-mensaje'> " + nombreMostrar + " el " + mensaje.fecha + "</span>:<br>";
		                html += mensaje.mensaje;
		                html += "</div>";
	                });
	                html += "</div>";
	            } else {
	                html += "<p>Aún no hay mensajes en el chat.</p>";
	            }
			            
	            // Introducir mensaje nuevo
	            html += "<form id='formEnviarMensaje'>";
	            html += "<textarea id='mensaje' placeholder='Escribe tu mensaje' required></textarea><br>";
	            html += "<button id='boton-enviar' type='submit'>Enviar</button>";
	            html += "</form>";
	            
	            html += "<button class='cerrar-popup' onclick='cerrarPopupChat()'><i class='fa-solid fa-circle-xmark'></i> Cerrar</button>";
	
	            // Mostrar el popup
	            document.getElementById("chatProyecto").innerHTML = html;
	            document.getElementById("popupChats").style.display = "block";
	            
	            // Manejar el envío del formulario
	            document.getElementById("formEnviarMensaje").addEventListener("submit", function(event) {
	                event.preventDefault();
	                let mensaje = document.getElementById("mensaje").value;
	                enviarMensaje(idProyecto, mensaje);
	            });
	        })
	        .catch(error => console.error('Error al obtener el chat:', error));
	}
	
	function enviarMensaje(idProyecto, mensaje) {
	    // Construir el objeto del mensaje
	    let mensajeObj = {
	        idProyecto: idProyecto,
	        mensaje: mensaje
	    };
	
	    // Enviar el mensaje al servidor
	    fetch('SV_Chats', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify(mensajeObj)
	    })
	    .then(response => response.json())
	    .then(data => {
	        console.log('Mensaje enviado:', data);
	        // Agregar el nuevo mensaje al historial de mensajes existente
	        let nuevoMensajeHtml = "";
	        if (data.success) {
	            let nombreUsuario = document.getElementById("nombreUsuario").getAttribute("data-nombre");
	            let mensajeClase = data.nombreCreador === nombreUsuario ? 'mensaje-propio' : 'mensaje-otro';
	            let nombreMostrar = data.nombreCreador === nombreUsuario ? nombreUsuario : data.nombreCreador;
	            nuevoMensajeHtml += "<div class='chat-mensaje " + mensajeClase + "'>";
	            nuevoMensajeHtml += "<b>" + nombreMostrar + "</b> escribió el <span class='fecha-mensaje'>" + data.fecha + "</span>:<br>";
	            nuevoMensajeHtml += data.mensaje;
	            nuevoMensajeHtml += "</div>";
	        } else {
	            console.error("Error al enviar el mensaje:", data.error);
	        }
	        // Agregar el nuevo mensaje al historial de mensajes
	        let historialMensajes = document.getElementById("historialMensajes");
	        historialMensajes.innerHTML += nuevoMensajeHtml;
	    })
	    .catch(error => console.error('Error al enviar el mensaje:', error));
	}

	
	// Función para cerrar el popup del chat
	function cerrarPopupChat() {
	    document.getElementById("popupChats").style.display = "none";
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
	                <button class="cerrar-popup" onclick="cerrarPopupProyecto()"><i class="fa-solid fa-circle-xmark"></i> Volver al listado</button>
	            `;
	
	            // Mostrar la pop-up
	            document.getElementById("popVerProyecto").style.display = "block";
	        });
	}
	
			// Función para ocultar la pop-up
			function cerrarPopupProyecto() {
			    document.getElementById("popVerProyecto").style.display = "none";
			}

	


	
	