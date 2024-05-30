


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
				}else if (datos[i].categoria == "diseno_grafico") {
        		categoriaMostrada = "Diseño Gráfico";
				}
    
        	html += "<td id='bloque'><strong>Categoría:</strong><br> " + categoriaMostrada + "</td>";
      		html += "<td id='bloque'><strong>Plazo máximo:</strong><br> " + datos[i].fechaEntrega + "</td>";
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
			// Añadir el icono de chat solo si el estado es "Asignado" o "Pendiente de revisión"
      		// Si no, mostrar el icono de candidatos
        	if (datos[i].estado === "Pendiente" || datos[i].estado === "Asignado") {
            html += "<td id='icono'><a href='#' onclick='abrirChat("+datos[i].idProyecto+")'><i class='fa-solid fa-comments'></i></a></td>";
        	} else if (datos[i].estado === "Cerrado") {
			html += "<td></td>"; 
			} else {
            html += "<td id='icono'><a href='#' onclick='mostrarCandidatos("+datos[i].idProyecto+")'><i class='fa-solid fa-users'></i></a></td>";
        	}
        	// Añadir el botón de borrar sólo cuando NO está cerrado, asignado o pendiente
			if (datos[i].estado === "Cerrado" || datos[i].estado === "Asignado" || datos[i].estado === "Pendiente") {
			html += "<td></td>"; 
        	} else {
      		html += "<td id='icono'><a href='#' onclick='borrar("+datos[i].idProyecto+")'><i class='fa-solid fa-circle-xmark'></i></a></td>";
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
    	document.querySelector("form[name='formulario-proyecto']").addEventListener("submit", function(){
        document.querySelector(".contenedorModificarProyectos").style.display = "none";
    });
    

	
		window.onload = function() {
	
    	llamada();
    }
    
	
	
	});
	
	//BOTÓN CHAT
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


    // BOTÓN MOSTRAR CANDIDATOS (POP UP)
	function mostrarCandidatos(idProyecto) {
  	  fetch('SV_GestionCandidaturas?idProyecto=' + idProyecto)
	    .then(response => response.json())
	    .then(data => {
            console.log("Datos recibidos:", data); 

            var contenidoCandidatos = document.getElementById("listaCandidatos");
            var html = "<h2>Listado de Candidatos</h2>";
            html += `<p>¡Tienes trabajo por delante!</p><br><br>
            		 <p>${data.length} candidatos han aplicado al proyecto. Estas son sus tarifas:</p><br><br>`;
            
            for (var i = 0; i < data.length; i++) {
				
				// Verificar si el estado es "Rechazado" y establecer la opción por defecto en el selector
    			let estadoSeleccionado = data[i].estado;

    			console.log("Estado seleccionado:", estadoSeleccionado);

    
                html += `
                    <div class="candidato">
                        <span>Oferta ${i + 1}   ->    <strong>${data[i].precio}€ </strong></span>
                        <select id="estadoSelect_${i}" class="estado-select">
                        	<option value="Pendiente" ${estadoSeleccionado === "Pendiente" ? "selected" : ""}>Pendiente &#128309;</option>
			                <option value="Aceptada" ${estadoSeleccionado === "Aceptada" ? "selected" : ""}>Aceptada &#9989</option>
			                <option value="Rechazada" ${estadoSeleccionado === "Rechazada" ? "selected" : ""}>Rechazada &#128308;</option>
                        </select>
                        <button class="enviar-candidaturas" onclick="actualizarEstado(${idProyecto}, '${data[i].dnicif_Creador}', '${data[i].precio}', ${i})">Actualizar</button><br><br>
                    </div>
                `;
            }

			    html += `<button class="cerrar-popup" onclick="cerrarPopupCandidatos()"><i class="fa-solid fa-circle-xmark"></i> Volver al listado</button>`;
		
            
            contenidoCandidatos.innerHTML = html;
            
            // Mostrar el popup
            var popup = document.getElementById("popupCandidatos");
            popup.style.display = "block";
        })
        .catch(error => console.error('Error al obtener los candidatos:', error));
	}
    
	    function cerrarPopupCandidatos() {
		    document.getElementById("popupCandidatos").style.display = "none";
		}
		
		

	    // ACTUALIZAR EL ESTADO  Y MANDAR A BD
		function actualizarEstado(idProyecto, dnicif_Creador, precio, index) {
		    var estadoSelect = document.getElementById("estadoSelect_" + index);
		    if (estadoSelect !== null) {
		        var estadoSeleccionado = estadoSelect.value;
		        console.log("El estado seleccionado es:" + estadoSeleccionado);
		
		        // Construir el cuerpo de la solicitud
		        var body = {
		            idProyecto: idProyecto,
		            dnicif_Creador: dnicif_Creador,
		            precio: precio,
		            estado: estadoSeleccionado
		        };
		
		        // Realizar una solicitud POST al servidor para actualizar el estado
		        fetch('SV_GestionCandidaturas', {
		            method: 'POST',
		            headers: {
		                'Content-Type': 'application/json'
		            },
		            body: JSON.stringify(body)
		        })
		    }
		}



	
	