<!DOCTYPE html>
<html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="estilos.css">
	<!-- fin bootstrap -->
	<link rel="shortcut icon" href="imagenes/logos/favicon.png" />
	<link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah" rel="stylesheet">
	<link rel="stylesheet" href="estilos.css">
	<style>
@import url('https://fonts.googleapis.com/css?family=Gloria+Hallelujah');
</style>
<link rel="icon" href="img/favicon.png" type="image/x-icon"/>
	<title>Hola! Ingresa tu Email o identificacion </title>
</head>
<body>
<!-- Encabezado -->
<header >
<div class="container">
<div class="main row">
<article class="col-lg-2">
<!-- logo header -->
<a href="index.jsp"><img src="img/logo_header.png" class="img-responsive" alt="Imagen responsive"></a>
<!-- Fin logo header -->
</article>


<!-- logo header -->
<form action="menu_busqueda.jsp?mod=" method="post">
        <div class="col-lg-3">
            <div id="imaginary_container"> 
                <div class="form-group">
                    <input type="text" name="buscador_inicio" class="form-control" id="inputSeleccionado" placeholder="Buscar independiente" >
                  
                </div>
                
            </div>

        </div>
        


        <div class="col-lg-3">
            <div id="imaginary_container"> 
                <div class="input-group stylish-input-group form-group">
                    <select class="form-control col-md-9" id="sel1" name="ciudad" required="required" placeholder="ciudad">
                                   <option>Ciudad</option>
                                  <option value="medellin">Medellin</option>
                                  <option value="bogota">Bogota</option>
                                </select>
                    <span class="input-group-addon">
                        <button type="submit" name="enviar">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>  
                    </span>
                </div>
            </div>

        </div>
</form>
<!-- Fin logo header -->


<article class="col-lg-3">
<!-- logo header -->
<h4><a href="registrar.jsp" style="text-decoration: none; color: #fff;"> Registrate</a> | <a  href="login.jsp" style="text-decoration: none; color: #fff;"> Ingresa</a> </h4>
<!-- Fin logo header -->
</article>
	
</div>

</header>
<!-- fin encabezado -->

 <section class="main row">
 <div class="container">
	<div class="col-xs-10  col-sm-10 col-md-10 login">
		
		<h2>Registrar Cliente</h2>
		 
        <a href="registrar.jsp">

         <button id="btn-signup" type="submit" class="btn btn-sucess " style="float: right; margin-top: -5%;" name="enviarregistro"><i class="icon-hand-right"></i><b>Registrate como independiente</b></button></a>
		<hr>
        <br>
        

<form id="loginform" class="form-horizontal" role="form" action="codigos/codigoregistrar_cliente.jsp" method="post" enctype="multipart/form-data"> 
         
    
                        
                         
                        <!--<div class="form-group">
                                    <label for="ejemplo_archivo_1" class="col-md-3 control-label">Foto de perfil</label>
                                     <input type="file" id="ejemplo_archivo_1" name="foto">
                                    
                                </div>-->

                                
                              
                                    
                                
                                  
                                <div class="form-group">
                                    <label for="text" class="col-md-3 control-label">Primer Nombre</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="primer_nombre" placeholder="ingrese primer nombre" required="required">
                                    </div>
                                </div>
                                    
                                 <div class="form-group">
                                    <label for="text" class="col-md-3 control-label">Segundo Nombre</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="segundo_nombre" placeholder="Ingrese su segundo nombre" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="lastname" class="col-md-3 control-label">Primer Apellido</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="primer_apellido" placeholder="Ingrese su primer apellido" required="required">
                                    </div>
                                </div>
                                 


                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Segundo apellido</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="segundo_apellido" placeholder="Ingrese su segundo apellido" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="text" class="col-md-3 control-label">Telefono</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="telefono" placeholder="Ingrese su telefono" required="required">
                                    </div>
                                </div>

                                  <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Correo</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="correo" placeholder="correo" required="required">
                                    </div>
                                </div>

                                          <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Contrase&ntilde;a</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="password" placeholder="Entre 8 y 15 caracteres" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Confirmar contrase&ntilde;a</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="rpassword" placeholder="Vuelva a escribir la contrase&ntilde;a" required="required">
                                    </div>
                                </div>
                                



                                 <br>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                   <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-success" name="registro_cliente"><i class="icon-hand-right"></i>Registrar</button>
                                         
                                    </div>
                                </div>
                                
                               
                                
                                
                                
                            
                    

               
               
                
         

	</div>
</form>
	</div>
    </section>
 </div>

<div class="container">
<div class="main row">
<article class="col-lg-12">
<!-- categorias Visitadas -->
<h4></h4>
<!-- categorias visitadas -->
</article>
</div>
</div>


<div class="container">
<div class="main row">
<article class="col-lg-12">
<!-- destacados -->
<h4>----</h4>
<!-- Fin destacados -->
</article>
</div>
</div>

<!-- Pie de pagina -->
<footer >
<div class="container-fluid col-xs-12 col-sm-12" style="background-color: rgba(7, 7, 15, 0.1);">
	<center><h6>Copyright © 2017 Tocupo Colombia LTDA. <font color="blue"> Trabaja con nosotros | Términos y condiciones | Políticas de privacidad |Ayuda | PQR 
	<button type="button" class="btn btn-warning" >Descarga la APP gratis</button>
					
	</div>
</footer>
<!-- Fin Pie de pagina -->
</body>
<!-- Bootstrap -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- fin bootstrap -->
</html>