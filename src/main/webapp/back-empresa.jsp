<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creators Hub | Panel Empresas</title>
    <link rel="stylesheet" href="back-creators-hub.css">
    <link rel="stylesheet" href="listados.css">
    <link rel="stylesheet" href="chat.css">

    <!-- Fuentes Google -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

    <!-- Biblioteca Font Awesome (iconos) -->
    <script src="https://kit.fontawesome.com/cf5c140da9.js" crossorigin="anonymous"></script>
    
    <script src="JS/back-empresas.js"></script>
    <script src="JS/gestionProyectos-empresa.js"></script>

</head>

<body>

    <div class="contenedor-panel">

        <section class="panel-menu">

            <img src="img/logo-creartors-hub-estrecho.png" alt="Logo de Creartors Hub" id="logo-panel">
            
        

            <div id="msj-bienvenida" data-nombre="<%= session.getAttribute("nombre") %>">�Bienvenid@ de nuevo!</div>
 

            <div>

                <ul class="menu-vertical" id="pestanias">
                     <li class= "tabs active-tab-empresa" id="mis-proyectos"><i class="fa-solid fa-briefcase"></i>Mis Proyectos</li>
                     <li class= "tabs" id="nuevo-proyecto"><i class="fa-solid fa-bullhorn"></i>Publicar Nuevo Proyecto</li>
                     <li class= "tabs" id="portfolios"><i class="fa-solid fa-address-book"></i>Portfolio de Creators</li>
                     <li class= "tabs" id="settings"><i class="fa-solid fa-gear"></i>Preferencias</li>
                     <li class= "tabs" id="help"><i class="fa-solid fa-circle-question"></i>Centro de Ayuda</li>
                     <li class= "tabs" id="salir"><i class="fa-solid fa-right-from-bracket"></i>Salir</li>
                 </ul>

            </div>


        </section>

        <section class="panel-pantalla-empresa">

            <div class="panel active-panel">
                <h2>MIS PROYECTOS</h2>
                <p>Aqu� podr�s consultar tus proyectos activos y cerrados, modificar su estado, revisa las candidaturas y mucho m�s.</p>
                <div id="listado"></div>
                
                <div class="contenedorModificarProyectos" id="contenedorProyectos" style="display: none;">
                
                	<h2>MODIFICAR EL PROYECTO</h2>

				    <form name="formulario-modificar" action="SV_GestionProyectos" method="post" enctype="multipart/form-data">
						
						<input type="hidden" id="idProyecto" name="idProyecto">
						
				        <label for="titulo_proyecto">T�tulo del proyecto:</label>
				        <input type="text" id="titulo_proyecto" name="titulo_proyecto" required>
				    
				        <label for="categoria">Categor�a de servicios:</label>
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
				    
				        <label for="descripcion">Descripci�n del proyecto:</label>
				        <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
				    
				        <label for="fecha_entrega">Fecha m�xima de finalizaci�n:</label>
				        <input type="date" id="fecha_entrega" name="fecha_entrega" required>
				    
				        <label for="archivo_adjunto">�Necesitas adjuntar un nuevo briefing?</label>
				        <input type="file" id="archivo_adjunto" name="archivo_adjunto" accept=".doc,.docx,.pdf" required>
				        <br>
				        
				        <button type="button" onclick="cancelarModificacion()">Cancelar</button>
				        <input type="submit" value="Publicar cambios">
				
				    </form>
			
				</div>
				
				<!-- Contenedor para el popup de ver Proyectos -->
				<div id="popVerProyecto" class="popup">
					    <div class="popup-contenido" id="verProyecto"></div>
				</div>
				
				<!-- Contenedor para el popup de candidatos -->
				<div id="popupCandidatos" class="popup">
				    <div class="contenidoCandidatos" id="listaCandidatos"></div>
				</div>
				
				
				 <div id="nombreUsuario" data-nombre="<%= session.getAttribute("nombre") %>"></div>
				<!-- Contenedor para el popup de chats -->
				<div id="popupChats" class="popup">
				    <div class="contenidoChat" id="chatProyecto"></div>
				</div>
								
				
				
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
                    
                        <label for="fecha_entrega">�Cu�l es la fecha m�xima para desarrollar del proyecto?</label>
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
                <div class="buscador-categorias">
                
                    <form id="buscador-categorias" action="SV_Buscador" method="GET">
				        <label for="categoria">Encuentra un <strong>CreArtor</strong> seg�n su categor�a:</label>
				        <select id="categoria-form" name="categoria">
				            <option value="" disabled selected>Selecciona una disciplina</option>
				            <option value="contenido_UGC">Contenido UGC</option>
				            <option value="video">V�deo</option>
				            <option value="fotografia">Fotograf�a</option>
				            <option value="ilustracion">Ilustraci�n</option>
				            <option value="diseno_grafico">Dise�o Gr�fico</option>
				            <option value="motions_graphics">Motions Graphics</option>
				            <option value="artes_plasticas">Artes Pl�sticas</option>
				        </select>
				        <input type="button" id="buscar-button" value="Buscar">

				    </form>
    
                </div>
                <div id="listado-creadores"></div>
            </div>
            
            <div class="panel">
                <h2>PREFERENCIAS</h2>
                <p>Actualiza tus datos de usuario a continuaci�n:</p>
                
                <div id="contenedorFormularios">
                <form name="formulario-preferencias" action="SV_GestionUsuarios" method="post">
						
					<input type="hidden" id="dnicif" name="dnicif">
					
					<div id="msj-preferencias" data-email="<%= session.getAttribute("email") %>">Tu email es:</div>
					
					<div class="contenedor-columnas">
						
						<div class="columna">
						
				            <label for="pass" id="label-form">Modificar contrase�a:</label><br>
				            <input type="text" id="edit-pass" name="edit-pass"><br><br>
				            
				            <label for="nombre" id="label-form">Nombre:</label><br>
				            <input type="text" id="edit-nombre" name="edit-nombre" ><br><br>
				            
				            <label for="apellidos" id="label-form">Apellidos:</label><br>
				            <input type="text" id="edit-apellidos" name="edit-apellidos" ><br><br>  
				            
				            <label for="nombre_empresa" id="label-form">Raz�n Social:</label><br>
				            <input type="text" id="edit-razonsocial" name="edit-razonsocial" ><br><br>
				            
				            <label for="direccion" id="label-form">Direcci�n:</label><br>
				            <input type="text" id="edit-direccion1" name="edit-direccion1"><br><br>
				            
				        </div>
				
				        <div class="columna">
				        
				            <label for="direccion2" id="label-form">Direcci�n 2:</label><br>
				            <input type="text" id="edit-direccion2" name="edit-direccion2"><br><br>
				            
				            <label for="ciudad" id="label-form">Ciudad:</label><br>
				            <input type="text" id="edit-ciudad" name="edit-ciudad"><br><br>
				            
				            <label for="provincia" id="label-form">Provincia:</label><br>
				            <input type="text" id="edit-provincia" name="edit-provincia"><br><br>
				            
				            <label for="codigo_postal" id="label-form">C�digo postal:</label><br>
				            <input type="text" id="edit-cp" name="edit-cp"><br><br>
				            
				            <label for="telefono" id="label-form">Tel�fono:</label><br>
				            <input type="tel" id="edit-telefono" name="edit-telefono"><br><br> 
				            
				                
				        </div>
				     
				     </div>
					        
					        <input type="submit" value="Guardar cambios">
					
				</form>
				</div>
				    
			</div>

        
        </section>  

    </div>


</body>
</html>