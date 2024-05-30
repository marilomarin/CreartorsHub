<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creators Hub | Panel Empresas</title>
    <link rel="stylesheet" href="back-creators-hub.css">
    <link rel="stylesheet" href="listados.css">

    <!-- Fuentes Google -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

    <!-- Biblioteca Font Awesome (iconos) -->
    <script src="https://kit.fontawesome.com/cf5c140da9.js" crossorigin="anonymous"></script>
    
    <script src="back-admin.js"></script>
    <script src="gestionProyectos.js"></script>

</head>

<body>

    <div class="contenedor-panel">

        <section class="panel-menu">

            <img src="img/logo-creartors-hub-estrecho.png" alt="Logo de Creartors Hub" id="logo-panel">
            
        

            <div id="msj-bienvenida" data-nombre="<%= session.getAttribute("nombre") %>">�Bienvenid@ de nuevo!</div>
 

            <div>

                <ul class="menu-vertical" id="pestanias">
                     <li class= "tabs active-tab" id="home"><i class="fa-solid fa-house"></i>Inicio</li>
                     <li class= "tabs" id="mis-proyectos"><i class="fa-solid fa-briefcase"></i>Mis Proyectos</li>
                     <li class= "tabs" id="nuevo-proyecto"><i class="fa-solid fa-bullhorn"></i>Publicar Nuevo Proyecto</li>
                     <li class= "tabs" id="portfolios"><i class="fa-solid fa-address-book"></i>Portfolio de Creators</li>
                     <li class= "tabs" id="chats"><i class="fa-solid fa-comments"></i>Chats</li>
                     <li class= "tabs" id="settings"><i class="fa-solid fa-gear"></i>Preferencias</li>
                     <li class= "tabs" id="help"><i class="fa-solid fa-circle-question"></i>Centro de Ayuda</li>
                     <li class= "tabs" id="salir"><i class="fa-solid fa-right-from-bracket"></i>Salir</li>
                 </ul>

            </div>


        </section>

        <section class="panel-pantalla">

            <div class="panel active-panel">
                <h2>INICIO</h2>
                <div></div>
                <div></div>
            </div>

            <div class="panel">
                <h2>MIS PROYECTOS</h2>
                <p>Aqu� podr�s consultar tus proyectos activos y cerrados, modificar su estado, revisa las candidaturas y mucho m�s.</p>
                <div id="listado"></div>
            </div>

            
            <div class="panel">
                <h2>PUBLICA UN NUEVO PROYECTO</h2>
                
                <p>�Aqu� es donde la creatividad cobra vida! </p>
                <p>Dinos qu� est�s buscando y conecta con los mejores talentos creativos ;)</p>
                <div id="contenedorProyectos">
                    <form name="formulario-proyecto" action="SV_PublicarProyecto" method="post" enctype="multipart/form-data">
		
                        <input type="hidden" id="id" name="id">
                        
                        <label for="titulo_proyecto">T�tulo del proyecto:</label>
                        <input type="text" id="titulo_proyecto" name="titulo_proyecto" required>
                    
                        <label for="categoria">�Qu� tipo de servicios necesitas?</label>
                        <select id="categoria" name="categoria" required>
                            <option value="" disabled selected>Selecciona una categor�a</option>
                            <option value="contenido_UGC">Contenido UGC</option>
                            <option value="video">V�deo</option>
                            <option value="fotografia">Fotograf�a</option>
                            <option value="ilustracion">Ilustraci�n</option>
                            <option value="diseno_grafico">Dise�o Gr�fico</option>
                            <option value="motions_graphics">Motions Graphics</option>
                            <option value="artes_plasticas">Artes Pl�sticas</option>
                        </select>
                    
                        <label for="descripcion">Por favor, describe brevemente en qu� consiste el proyecto a continuaci�n:</label>
                        <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
                    
                        <label for="fecha_entrega">Fecha l�mite de entrega:</label>
                        <input type="date" id="fecha_entrega" name="fecha_entrega" required>
                    
                        <label for="archivo_adjunto">Por favor, adjunta el briefing detallado del proyecto:</label>
                        <input type="file" id="archivo_adjunto" name="archivo_adjunto" accept=".doc,.docx,.pdf" required>
                        <br>
                       <!-- <input type="submit" value="¡Publicar proyecto!"> -->
                        <input type="hidden" name="op" id="op">
                        <input type="submit" value="�Todo listo! Publicar ahora" onclick="setOperation('publicar')">
                
                    </form>
                </div>

            </div>
            
            <div class="panel">
                <h2>LISTADO DE CREADORES</h2>
                <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed consequat, eros sit amet ultrices malesuada, arcu magna dictum mi, vitae ultricies ligula risus sit amet leo. Integer condimentum lobortis libero, quis posuere nulla pellentesque nec. Nullam fringilla, velit id consequat pretium, dolor eros fringilla eros, ac molestie eros justo id libero. Quisque sit amet ante sed leo varius fermentum. Nullam id nisi nec sem cursus fermentum. Duis auctor ligula a nisi congue faucibus. Cras fringilla, leo in sodales finibus, ligula arcu aliquet odio, a ullamcorper ligula lectus vel neque. Fusce vitae eros a libero lacinia vestibulum. Maecenas convallis, lectus at tristique fringilla, dui nulla eleifend dui, eget commodo nisi eros et velit. Ut auctor turpis vel justo tincidunt, eget malesuada nisl eleifend. Sed ullamcorper odio nec dolor eleifend, eget posuere lectus scelerisque. Nullam auctor elit eu lacus mattis, in tincidunt risus pulvinar. Donec non tortor vitae metus eleifend pharetra. Phasellus ut nisi sed leo congue consequat sed eget nisl."</p>
            </div>

            <div class="panel">
                <h2>CHATS</h2>
                <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed consequat, eros sit amet ultrices malesuada, arcu magna dictum mi, vitae ultricies ligula risus sit amet leo. Integer condimentum lobortis libero, quis posuere nulla pellentesque nec. Nullam fringilla, velit id consequat pretium, dolor eros fringilla eros, ac molestie eros justo id libero. Quisque sit amet ante sed leo varius fermentum. Nullam id nisi nec sem cursus fermentum. Duis auctor ligula a nisi congue faucibus. Cras fringilla, leo in sodales finibus, ligula arcu aliquet odio, a ullamcorper ligula lectus vel neque. Fusce vitae eros a libero lacinia vestibulum. Maecenas convallis, lectus at tristique fringilla, dui nulla eleifend dui, eget commodo nisi eros et velit. Ut auctor turpis vel justo tincidunt, eget malesuada nisl eleifend. Sed ullamcorper odio nec dolor eleifend, eget posuere lectus scelerisque. Nullam auctor elit eu lacus mattis, in tincidunt risus pulvinar. Donec non tortor vitae metus eleifend pharetra. Phasellus ut nisi sed leo congue consequat sed eget nisl."</p>
            </div>

        
        </section>  

    </div>


</body>
</html>