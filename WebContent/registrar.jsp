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
	<title>¡Hola! Ingresa tu Email o identificacion </title>
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
		
		<h2>Regístrate Independiente</h2>
		<a href="registrar_cliente.jsp"> <button id="btn-signup" type="submit" class="btn btn-sucess " style="float: right; margin-top: -5%;" name="enviarregistro"><i class="icon-hand-right"></i><b>Registrate solo como cliente</b></button></a>
		<hr>
        

<form id="loginform" class="form-horizontal" role="form" action="codigos/codigoregistrar.jsp" method="post" enctype="multipart/form-data"> 
         
    
                        
                         
                      <!--  <div class="form-group"> 
                                    <label for="ejemplo_archivo_1" class="col-md-3 control-label">Foto de perfil</label>
                                     <input type="file" id="ejemplo_archivo_1" name="foto">
                                    
                                </div> -->

                                
                              
                                    
                                
                                  
                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Email</label>
                                    <div class="col-md-9">
                                        <input type="email" class="form-control" name="email" placeholder="Email" required="required">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">Nombres</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="nombres" placeholder="Nombres" required="required">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastname" class="col-md-3 control-label">Apellidos</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="apellidos" placeholder="Apellidos" required="required">
                                    </div>
                                </div>
                                 


                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Contraseña</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="password" placeholder="Entre 8 y 15 caracteres" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Confirmar contraseña</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="rpassword" placeholder="Vuelva a escribir la contraseña" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Ocupacion</label>
                                    <div class="col-md-9">
                                        <select class="form-control col-md-9" id="sel1" name="ocupacion" required="required">
                 <?php 
                        include 'conexion.jsp';
                    $query="select * from categorias"; 
                         $res=mysqli_query($conexion,$query);

                           while($row=mysqli_fetch_array($res)){?>
                    <option value="<?php echo $row['idcategorias']?>">
                     <?php echo $row['tipo_categorias']?>     
                    </option>
                                 <?php } ?>
                                </select>
                                    </div>
                                </div>



                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Membresia</label>
                                    <div class="col-md-9">
                                        <select class="form-control col-md-9" id="sel1" name="membresia" required="required">
                                   <option value="1">Oro $0000.00</option>
                                  <option value="2">Plata $0000.00</option>
                                  <option value="3">Bronce $0000.00</option>
                                </select>
                                    </div>
                                </div>
                                    


                                <div class="checkbox">
                                <label><input type="checkbox" value="1" required="required">Acepto los Términos y Condiciones de Tocupo</label>

                                </div>
                                 <br>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                   <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-success" name="enviarregistro"><i class="icon-hand-right"></i>Realizar Pago y registro</button>
                                         
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