<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creators Hub | Panel Creadores</title>
    <link rel="stylesheet" href="back-creators-hub.css">
    <link rel="stylesheet" href="listados.css">
    <link rel="stylesheet" href="chat.css">

    <!-- Fuentes Google -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

    <!-- Biblioteca Font Awesome (iconos) -->
    <script src="https://kit.fontawesome.com/cf5c140da9.js" crossorigin="anonymous"></script>
    
    <script src="JS/back-creadores.js"></script>
    <script src="JS/gestionProyectos-creador.js"></script>

</head>

<body>

    <div class="contenedor-panel">

        <section class="panel-menu">

            <img src="img/logo-creartors-hub-estrecho.png" alt="Logo de Creartors Hub" id="logo-panel">
            
        

            <div id="msj-bienvenida" data-nombre="<%= session.getAttribute("nombre") %>">¡Bienvenid@ de nuevo!</div>
 

            <div>

                <ul class="menu-vertical" id="pestanias">
                     <li class= "tabs active-tab-creador" id="mis-proyectos"><i class="fa-solid fa-briefcase"></i>Mis Proyectos</li>
                     <li class= "tabs" id="buscar-proyectos"><i class="fa-solid fa-magnifying-glass-plus"></i>Buscar Proyectos</li>
                     <li class= "tabs" id="settings"><i class="fa-solid fa-gear"></i>Preferencias</li>
                     <li class= "tabs" id="help"><i class="fa-solid fa-circle-question"></i>Centro de Ayuda</li>
                     <li class= "tabs" id="salir"><i class="fa-solid fa-right-from-bracket"></i>Salir</li>
                 </ul>

            </div>


        </section>

        <section class="panel-pantalla-creador">


            <div class="panel active-panel">
                <h2>MIS PROYECTOS</h2>
                <p>Aquí podrás consultar tus proyectos activos y cerrados, modificar su estado, revisa las candidaturas y mucho más.</p>
                <div id="listado"></div>
                
                <div class="contenedorModificarProyectos" id="contenedorProyectos" style="display: none;">
                
                	<h2>MODIFICAR EL PROYECTO</h2>

				    <form name="formulario-modificar" action="SV_GestionProyectos" method="post" enctype="multipart/form-data">
						
						<input type="hidden" id="idProyecto" name="idProyecto">
						
				        <label for="titulo_proyecto">Título del proyecto:</label>
				        <input type="text" id="titulo_proyecto" name="titulo_proyecto" required>
				    
				        <label for="categoria">¿Qué tipo de servicios necesitas?</label>
				        <select id="categoria" name="categoria" required>
				            <option value="" disabled selected>Selecciona una categoría</option>
				            <option value="contenido_UGC">Contenido UGC</option>
				            <option value="video">Vídeo</option>
				            <option value="fotografia">Fotografía</option>
				            <option value="ilustracion">Ilustración</option>
				            <option value="diseno_grafico">Diseño Gráfico</option>
				            <option value="motions_graphics">Motions Graphics</option>
				            <option value="artes_plasticas">Artes Plásticas</option>
				        </select>
				    
				        <label for="descripcion">Por favor, describe brevemente en qué consiste el proyecto a continuación:</label>
				        <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
				    
				        <label for="fecha_entrega">Fecha límite de entrega:</label>
				        <input type="date" id="fecha_entrega" name="fecha_entrega" required>
				    
				        <label for="archivo_adjunto">Por favor, adjunta el briefing detallado del proyecto:</label>
				        <input type="file" id="archivo_adjunto" name="archivo_adjunto" accept=".doc,.docx,.pdf" required>
				        <br>
				        
				        <button type="button" onclick="cancelarModificacion()">Cancelar</button>
				        <input type="submit" value="Publicar cambios">
				
				    </form>
			
				</div>
				
				<div id="popVerProyecto" class="popup">
					    <div class="popup-contenido" id="verProyecto">
					        <!-- Aquí se muestran los detalles del proyecto -->
					    </div>
				</div>
				
				<div id="nombreUsuario" data-nombre="<%= session.getAttribute("nombre") %>"></div>
				<!-- Contenedor para el popup de chats -->
				<div id="popupChats" class="popup">
				    <div class="contenidoChat" id="chatProyecto"></div>
				</div>
				
				
            </div>
            
            <div class="panel">
                <h2>PROYECTOS DISPONIBLES</h2>
                <div class="buscador-categorias">
                
                    <form id="buscador-categorias" action="SV_Buscador" method="GET">
				        <label for="categoria">Encuentra un proyecto según su categoría:</label>
				        <select id="categoria-form" name="categoria">
				            <option value="" disabled selected>Selecciona una disciplina</option>
				            <option value="contenido_UGC">Contenido UGC</option>
				            <option value="video">Vídeo</option>
				            <option value="fotografia">Fotografía</option>
				            <option value="ilustracion">Ilustración</option>
				            <option value="diseno_grafico">Diseño Gráfico</option>
				            <option value="motions_graphics">Motions Graphics</option>
				            <option value="artes_plasticas">Artes Plásticas</option>
				        </select>
				        <input type="button" id="buscar-button" value="Buscar">

				    </form>
    
                </div>
                
                <div id="listado-proyectos-disponibles"></div>
                
              	<div id="popVerProyecto" class="popup">
			    <div class="popup-contenido" id="verProyecto"></div>
				</div>
				
				<div id="popAplicar" class="popup" style="display: none;">
				    <div id="popupAplicar" class="popup-contenido"></div>
				</div>

            </div>
            
            <div class="panel">
                <h2>PREFERENCIAS</h2>
                <p>Actualiza tus datos de usuario a continuación:</p>
                
                <div id="formularioPreferencias">
                <form name="formulario-preferencias" action="SV_GestionUsuarios" method="post">
						
					<input type="hidden" id="dnicif" name="dnicif">
					
					<div id="msj-preferencias" data-email="<%= session.getAttribute("email") %>">Tu email es:</div>
					
					<div class="contenedor-columnas">
						
						<div class="columna">
						
				            <label for="pass" id="label-form">Modificar contraseña:</label><br>
				            <input type="text" id="edit-pass" name="edit-pass"><br><br>
				            
				            <label for="nombre" id="label-form">Nombre:</label><br>
				            <input type="text" id="edit-nombre" name="edit-nombre" ><br><br>
				            
				            <label for="apellidos" id="label-form">Apellidos:</label><br>
				            <input type="text" id="edit-apellidos" name="edit-apellidos" ><br><br>  
				            
				            <label for="nombre_empresa" id="label-form">Razón Social:</label><br>
				            <input type="text" id="edit-razonsocial" name="edit-razonsocial" ><br><br>
				            
				            <label for="direccion" id="label-form">Dirección:</label><br>
				            <input type="text" id="edit-direccion1" name="edit-direccion1"><br><br>
				            
				        </div>
				
				        <div class="columna">
				        
				            <label for="direccion2" id="label-form">Dirección 2:</label><br>
				            <input type="text" id="edit-direccion2" name="edit-direccion2"><br><br>
				            
				            <label for="ciudad" id="label-form">Ciudad:</label><br>
				            <input type="text" id="edit-ciudad" name="edit-ciudad"><br><br>
				            
				            <label for="provincia" id="label-form">Provincia:</label><br>
				            <input type="text" id="edit-provincia" name="edit-provincia"><br><br>
				            
				            <label for="codigo_postal" id="label-form">Código postal:</label><br>
				            <input type="text" id="edit-cp" name="edit-cp"><br><br>
				            
				            <label for="telefono" id="label-form">Teléfono:</label><br>
				            <input type="tel" id="edit-telefono" name="edit-telefono"><br><br> 
				            
				                
				        </div>
				     	
				     </div>
				     		<label for="portfolio" id="label-form">Tu porftolio:</label><br>
				     		<input type="text" id="edit-portfolio" name="edit-portfolio"><br><br>
				     		<label for="bio" id="label-form">Tu presentación:</label><br>
						    <textarea id="edit-bio" name="edit-bio"></textarea>
					        
					        <input type="submit" value="Guardar cambios">
					
				</form>
				</div>
				    
			</div>

        
        </section>  

    </div>


</body>
</html>