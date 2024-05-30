

  
document.addEventListener("DOMContentLoaded", function() {
    
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
            document.getElementById("edit-telefono").value = data.telefono;
            document.getElementById("edit-direccion1").value = data.direccionLinea1;
            document.getElementById("edit-direccion2").value = data.direccionLinea2;
            document.getElementById("edit-ciudad").value = data.ciudad;
            document.getElementById("edit-provincia").value = data.provincia;
            document.getElementById("edit-cp").value = data.cp;
        })
        .catch(error => console.error('Error:', error));
});
