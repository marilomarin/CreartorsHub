document.addEventListener("DOMContentLoaded", function() {
    
    // Obtener el parámetro de error de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');

    // Verificar si hay un mensaje de error en la URL
    if (error) {
        // Mostrar el mensaje de error en el elemento errorMessage
        document.getElementById('errorMessage').innerText = error;
    }
});