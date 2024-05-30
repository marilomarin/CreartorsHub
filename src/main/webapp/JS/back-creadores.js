

 
 // Función para activar las tabs del menú

	function pestanias(d){
	    let tabs = Array.prototype.slice.apply(d.querySelectorAll(".tabs"));
	    let panel = Array.prototype.slice.apply(d.querySelectorAll(".panel"));
		console.log("La función 'pestanias' se está ejecutando");
	
	    d.getElementById("pestanias").addEventListener('click', function(e){
			if (e.target.classList.contains("tabs")) { 
				let i = tabs.indexOf(e.target);
				console.log(`Tab clickeada: ${e.target.id}, Índice: ${i}`);
				
				// Cerrar sesión en el tab "Salir"
	            if (e.target.id === 'salir') {
	                // Llamada para cerrar sesión
	                window.location.href = 'SV_Login?action=logout';
	                return; // Salir de la función para no procesar más
	            }
				
				//Para recorrer las pestañas:
				tabs.map(tab => tab.classList.remove('active-tab-creador'));
				tabs[i].classList.add('active-tab-creador');
				//Para recorrer los bloques de contenido:
				panel.map(panel => panel.classList.remove('active-panel'));
				panel[i].classList.add('active-panel');
				
				// Actualizar el hash en la URL sin recargar la página
	            history.pushState(null, null, '#' + tabs[i].id);
	            
	            //  Para activar la función modificar al hacer click en "PREFERENCIAS"
	            if (e.target.id === 'settings') {
	                obtenerDatosUsuario();
	               }
	           	
	           	// Para listar los creadores en la pestaña PORTFOLIO
	           	if (e.target.id === 'buscar-proyectos') {
               		listarProyectosDisponibles();
	           		}
			}	
		});

		
	}

	// Función para asignar una url a cada tab
	
	function handleHash(){
		 let dest = location.hash.slice(1);
		 if(dest){
			 let tab = document.getElementById(dest);
			 if (tab){
				 console.log(`Tab encontrada para hash: ${dest}`);
				 
				 // Simular un evento de clic en la pestaña
	            tab.dispatchEvent(new Event('click'));
			 }
		 }
	}

	
	//Para listar y mostrar proyectos	
	   	function listarProyectosDisponibles(categoriaSeleccionada) {
	    fetch('SV_GestionProyectos?op=4')
	        .then(response => response.json())
			.then(datos => {
				 console.log("Datos recibidos del servidor:", datos);
	          
			let html = "<div id='contenedorTabla'><table class='tablaListado'>";
		    html += "<thead><tr><th>Proyecto</th><th>Categoría</th><th>Plazo máximo:</th><th>Acciones</th></tr></thead><tbody>";

		    for (let i = 0; i < datos.length; i++) {
				
				// Filtrar por estado "Sin asignar" y por categoría si se proporciona
                if (datos[i].estado === "Sin asignar" && (!categoriaSeleccionada || datos[i].categoria === categoriaSeleccionada)) {
					
		        html += "<tr class='fila'>";
		        html += "<td>" + datos[i].tituloProyecto + "</td>";
		        
		        // Cambiar los nombres de las categorías para hacerlos más estéticos
	    		let categoriaMostrada = datos[i].categoria;
	    			if (datos[i].categoria == "contenido_UGC") {
	        		categoriaMostrada = "<span id='ugc'>Contenido UGC</span>";
	    			}else if (datos[i].categoria == "motions_graphics") {
	        		categoriaMostrada = "<span id='motion'>Motion Graphics</span>";
					}else if (datos[i].categoria == "artes_plasticas") {
	        		categoriaMostrada = "<span id='artes'>Artes Plásticas</span>";
					}else if (datos[i].categoria == "ilustracion") {
	        		categoriaMostrada = "<span id='ilustracion'>Ilustración</span>";
					}else if (datos[i].categoria == "video") {
	        		categoriaMostrada = "<span id='video'>Vídeo</span>";
	        		}else if (datos[i].categoria == "fotografia") {
	        		categoriaMostrada = "<span id='fotografia'>Fotografía</span>";
					}else if (datos[i].categoria == "diseno_grafico") {
	        		categoriaMostrada ="<span id='diseno'>Diseño Gráfico</span>";
	        		}
		        html += "<td>" + categoriaMostrada + "</td>";
		        html += "<td>" + datos[i].fechaEntrega + "</td>";
		
		        html += "<td class='acciones'>";
		        html += "<button class='boton-acc' id='verMas' onclick='verMas(" + datos[i].idProyecto + ", 5)'>Ver más &#128064</button>";
        		html += `<button class='boton-acc' onclick='mostrarPopupAplicar(${datos[i].idProyecto})'>Aplicar &#9757</button>`;
      
		        html += "</td>";
		
		        html += "</tr>";
		    }
		    }
		
		    html += "</tbody></table></div>";
			
		    document.getElementById("listado-proyectos-disponibles").innerHTML = html;
			});
		}
		
		
		// Función para mostrar la categoría seleccionada
		function mostrarCategoriaSeleccionada() {
		    const categoriaSeleccionada = document.getElementById("categoria-form").value;
		    console.log("La categoría seleccionada es", categoriaSeleccionada);
		    listarProyectosDisponibles(categoriaSeleccionada);
		   }
		   
		   
		   	//Para obtener los datos de usuario en la pestaña Preferencias
		function obtenerDatosUsuario() {
	    fetch('SV_GestionUsuarios?op=2')
	        .then(response => response.json())
	        .then(data => {
	            document.getElementById("edit-pass").value = data.pass;
	            document.getElementById("edit-nombre").value = data.nombre;
	            document.getElementById("edit-apellidos").value = data.apellidos;
	            document.getElementById("edit-razonsocial").value = data.razonSocial;
	            document.getElementById("edit-direccion1").value = data.direccionLinea1;
	            document.getElementById("edit-direccion2").value = data.direccionLinea2;
	            document.getElementById("edit-ciudad").value = data.ciudad;
	            document.getElementById("edit-provincia").value = data.provincia;
	            document.getElementById("edit-cp").value = data.cp;
	            document.getElementById("edit-telefono").value = data.telefono;
	            document.getElementById("edit-portfolio").value = data.portfolio;
            	document.getElementById("edit-bio").value = data.bio;
	            
	        })
		        .catch(error => console.error('Error:', error));
		}
		
		
		// BOTÓN VER MÁS (POPUP)
		function verMas(idProyecto) {
	    fetch('SV_GestionProyectos?idProyecto=' + idProyecto + '&op=2')
	        .then(response => response.json())
	        .then(data => {
				console.log("Datos del proyecto:", data);
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
			
		
		
		// FUNCIÓN PARA APLICAR CANDIDATURA (POP UP)
		
			function mostrarPopupAplicar(idProyecto) {
		    document.getElementById("popupAplicar").innerHTML = `
		        <h2>¡Te estamos buscando!</h2>
		        <h3>Presenta tu candidatura a continuación:</h3><br><br>
		        <p>Estás aplicando para el Proyecto con ID:<strong> ${idProyecto}</strong></p><br><br>
		        <form id="formAplicar" method="post" action="SV_GestionCandidaturas">
		            <input type="hidden" name="idProyecto" value="${idProyecto}">
		            <label for="precio">¿Cuál sería tu tarifa completa (€) por desarrollar este proyecto:</label><br>
		            <p id="letra-peq">*Por favor, introduce los decimales con puntos (.)</p><br><br>
					<input type="text" id="precio" name="precio" required><br><br>
		            <button type="submit" id="enviar-candidatura">¡Enviar mi candidatura!</button>
		        </form>
		        <button class="cerrar-popup" onclick="cerrarPopupAplicar()">Cerrar</button>
		    `;
		
		    // Mostrar la pop-up
		    document.getElementById("popAplicar").style.display = "block";
			}
		
			// Función para ocultar la pop-up
			function cerrarPopupAplicar() {
			    document.getElementById("popAplicar").style.display = "none";
			}
				



	
				

//Se activa cuando se carga la página:
	document.addEventListener("DOMContentLoaded", function(){
		
		
		pestanias(document);
		
		handleHash(document);
		
		if (location.hash === '#buscar-proyectos') {
        listarProyectosDisponibles();
    	}
    
		
		
	    // Obtener el nombre de usuario 
	    var nombre = document.getElementById("msj-bienvenida").getAttribute("data-nombre");
	
	    // Actualizar el mensaje de bienvenida con el nombre de usuario
	    document.getElementById("msj-bienvenida").innerHTML = "¡Bienvenid@ de nuevo <strong>" + nombre + "</strong>!";
	    
	    console.log("Nombre de usuario captado:", nombre);
	    
	    
	    // Obtener el email
	    var email = document.getElementById("msj-preferencias").getAttribute("data-email");
	
	    document.getElementById("msj-preferencias").innerHTML = "Tu email es: <strong>" + email + "</strong>";
	    
	    console.log("Email captado:", email);
	    
	    
	    
	    // Para filtrar cuando se hace click en "buscar" en portfolios
	    const button = document.getElementById("buscar-button");
	    if (button) {
	        button.addEventListener("click", function(event) {
	            event.preventDefault(); 
	            mostrarCategoriaSeleccionada();
	        })
		}
	   
	    
	});
	

	