window.addEventListener("DOMContentLoaded", function(){

    
	function llamada(){
		fetch('SV_GestionProyectos?op=1')
		.then(response => response.json())
		.then(data => pintarTabla(data))
		
	}
	
	
	window.pintarTabla = function pintarTabla(datos){
		
		let html = "<table class='tablas-listado'>";
			
        for (var i = 0; i < datos.length; i++) {
       	 	html += "<tr class='fila'>";
        	html += "<td><strong>Título:</strong> " + datos[i].tituloProyecto + "</td>";
        	html += "<td><strong>Categoría:</strong> " + datos[i].categoria + "</td>";
      	 	html += "<td><strong>Descripción:</strong> " + datos[i].descripcion + "</td>";
      		html += "<td><strong>Fecha de entrega:</strong> " + datos[i].fechaEntrega + "</td>";
      	  	html += "<td><a href='modificar-proyecto.html?id=" + datos[i].id + "&op=2'>Modificar Proyecto</a></td>";
      		html += "<td><a href=#' onclick='borrar("+datos[i].id+")'>Borrar</a></td>";
      		html += "<td><a href=# "+datos[i].id+")'>Aplicar</a></td>";
      	  	html += "</tr>"; // Cierre de la fila
    }	
   			html += "</table>"; // Cierre del contenedor de resultados
	
		document.getElementById("listado").innerHTML = html;
	
			
	}
	
		window.onload = function() {
	
    	llamada();
    }
  
	
	
	});
	
	//La función de borrar realmente habría que hacerla con css y bonita
	
	function borrar(id){
		
		if(confirm("Por favor, confirma que quieres BORRAR los datos.")){
			fetch('SV_GestionProyectos?id='+id+'&op=3')
			.then(response => response.json())
			.then(data => pintarTabla(data))
		}else{
			
		}
	}

	
	