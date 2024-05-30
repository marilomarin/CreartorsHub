document.addEventListener("DOMContentLoaded", function() {
    let index = 0;
    const valoraciones = document.querySelectorAll('.valoracion');
    
    function cambiarTexto() {
      valoraciones.forEach(valoracion => valoracion.style.display = 'none');
      index = (index + 1) % valoraciones.length;
      valoraciones[index].style.display = 'block';
    }
    
    cambiarTexto(); 
    setInterval(cambiarTexto, 5000); // Cambiar cada 5 segundos
  });
  