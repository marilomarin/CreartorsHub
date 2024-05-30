window.addEventListener("DOMContentLoaded", function(){

function llamada(id, op){
		console.log(id);
		fetch('SV_GestionProyectos?id='+id+"&op="+op)
			.then(response => response.json())
			.then(data => pintarFormulario(data))
	}
	

 // Funcion para otener el valor de un parametro en el GET 
	function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));	   
	}
		
		
		function pintarFormulario(datos){
			document.getElementById("id").value = datos.id;
			document.getElementById("titulo_proyecto").value = datos.tituloProyecto;
			document.getElementById("categoria").value = datos.categoria;
			document.getElementById("descripcion").value = datos.descripcion;
			document.getElementById("fecha_entrega").value = datos.fechaEntrega;
			 console.log("ID obtenido al pintar el formulario:", datos.id);

		}
		
		
		
		window.onload = function(){
			let id = getParameterByName("id");
			let op = getParameterByName("op");
			llamada(id,op);
		}
		
		
		                
});