window.addEventListener("DOMContentLoaded", function(){

function llamada(idProyecto, op){
		console.log(idProyecto);
		fetch('SV_GestionProyectos?idProyecto='+idProyecto+"&op="+op)
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
			document.getElementById("idProyecto").value = datos.idProyecto;
			document.getElementById("titulo_proyecto").value = datos.tituloProyecto;
			document.getElementById("categoria").value = datos.categoria;
			document.getElementById("descripcion").value = datos.descripcion;
			document.getElementById("fecha_entrega").value = datos.fechaEntrega;
			 console.log("ID obtenido al pintar el formulario:", datos.idProyecto);

		}
		
		
		
		window.onload = function(){
			let idProyecto = getParameterByName("idProyecto");
			let op = getParameterByName("op");
			llamada(idProyecto,op);
		}
		
		
		                
});