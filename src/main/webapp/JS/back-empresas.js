

 
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
				tabs.map(tab => tab.classList.remove('active-tab-empresa'));
				tabs[i].classList.add('active-tab-empresa');
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
	           	if (e.target.id === 'portfolios') {
               		listarCreadores();
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
	
	
	//Para listar y mostrar creadores	
	   	function listarCreadores(categoriaSeleccionada) {
	    fetch('SV_GestionUsuarios?op=1')
	        .then(response => response.json())
			.then(datos => {
	          
	       
			let html = "<div id='contenedorTabla'><table class='tablaListado'>";
		    html += "<thead><tr><th>Foto</th><th>Nombre</th><th>Categoría</th><th>Portfolio</th><th>Presentación</th><th>Contactar</th></tr></thead><tbody>";

		    for (let i = 0; i < datos.length; i++) {
				
				// Si se proporciona una categoría, filtrar los creadores
                if (!categoriaSeleccionada || datos[i].categoria === categoriaSeleccionada) {
					
		        html += "<tr class='fila'>";
		        
		        html += "<td><img src='img/perfiles/" + datos[i].fotoPerfil + "' alt='Foto de perfil' class='foto-perfil'></td>";
		        
		        html += "<td>" + datos[i].nombre + "</td>";
		        
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
		        html += "<td>" + categoriaMostrada + "</td>";
		        html += "<td>" + datos[i].portfolio + "</td>";
		        html += "<td>" + datos[i].bio + "</td>";
		
		        html += "<td id='icono'>";
		        html += "<a href='#' onclick='abrirChat(\"" + datos[i].email + "\")'><i class='fa-solid fa-comments'></i></a>";
		        html += "</td>";
		
		        html += "</tr>";
		    }
		    }
		
		    html += "</tbody></table></div>";
			
		    document.getElementById("listado-creadores").innerHTML = html;
			});
		}
		
		
		// Función para mostrar la categoría seleccionada
		function mostrarCategoriaSeleccionada() {
		    const categoriaSeleccionada = document.getElementById("categoria-form").value;
		    console.log("La categoría seleccionada es", categoriaSeleccionada);
		    listarCreadores(categoriaSeleccionada);
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
	        })
		        .catch(error => console.error('Error:', error));
		}
				

//Se activa cuando se carga la página:
	document.addEventListener("DOMContentLoaded", function(){
		
		
		pestanias(document);
		
		handleHash(document);
		
		if (location.hash === '#portfolios') {
        listarCreadores();
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
	

	