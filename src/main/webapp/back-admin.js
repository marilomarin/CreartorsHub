
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
				tabs.map(tab => tab.classList.remove('active-tab'));
				tabs[i].classList.add('active-tab');
				//Para recorrer los bloques de contenido:
				panel.map(panel => panel.classList.remove('active-panel'));
				panel[i].classList.add('active-panel');
				
				// Actualizar el hash en la URL sin recargar la página
	            history.pushState(null, null, '#' + tabs[i].id);
	            
	            //  Para activar la función modificar al hacer click en "PREFERENCIAS"
	            if (e.target.id === 'settings') {
	                obtenerDatosUsuario();
	           
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
		};
		
		function obtenerDatosUsuario() {
	    fetch('SV_GestionUsuarios?op=2')
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('La solicitud falló con estado ' + response.status);
	            }
	            return response.json();
	        })
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



	window.addEventListener("DOMContentLoaded", function(){
		
		pestanias(document);
		
		handleHash(document);
		
	
		
	    // Obtener el nombre de usuario 
	    var nombre = document.getElementById("msj-bienvenida").getAttribute("data-nombre");
	
	    // Actualizar el mensaje de bienvenida con el nombre de usuario
	    document.getElementById("msj-bienvenida").innerHTML = "¡Bienvenid@ de nuevo <strong>" + nombre + "</strong>!";
	    
	    console.log("Nombre de usuario captado:", nombre);
	    
	    
	    // Obtener el email
	    var email = document.getElementById("msj-preferencias").getAttribute("data-email");
	
	    document.getElementById("msj-preferencias").innerHTML = "Tu email es: <strong>" + email + "</strong>";
	    
	    console.log("Email captado:", email);
	    
	});
	

