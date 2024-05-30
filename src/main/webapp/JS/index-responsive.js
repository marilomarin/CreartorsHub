
document.addEventListener("DOMContentLoaded", function() {
	
    const dropServicios = document.querySelectorAll('.dropdown-menu');
    const dropContent = document.querySelectorAll('.titulo-submenu');
    const iconoMenu = document.getElementById('toggle-icon');
    const iconoCerrar = document.getElementById('boton-cerrar-menu');

    iconoMenu.addEventListener('click', function () {
        document.querySelector('.menu-items').classList.toggle('active');
    });

    iconoCerrar.addEventListener('click', function () {
        document.querySelector('.menu-items').classList.remove('active');
    });

    dropServicios.forEach(menu => {
        menu.addEventListener('click', function () {
            const submenu = this.querySelector('.dropdown-menu-servicios');
            submenu.classList.toggle('active');
            
            // Desplazar opc2 hacia abajo cuando se activa la sección de servicios
            if (submenu.classList.contains('active')) {
                opc2.style.marginTop = (submenu.offsetHeight + 2) + 'px';
            } else {
                opc2.style.marginTop = '0';
            }
            
   		 });
   		 
	  });
        
     dropContent.forEach(menu => {
        menu.addEventListener('click', function () {
            const submenuContent = this.querySelector('.dropdown-content');
            submenuContent.classList.toggle('active');
   		 });
   		 
	  });
	
	
	
	
	
	
	console.log("El JS está activo")
	
	const titulos = document.querySelectorAll('.titulo-footer');
	console.log(titulos); 

    titulos.forEach(titulo => {
		
        titulo.addEventListener('click', function() {
			
            const ul = this.nextElementSibling;
            console.log("El elementoes" +ul);
            
            if (ul && ul.classList.contains('lista-footer')) {
                ul.classList.toggle('active');
            }
        });
    });
    
 });