<?php

include 'codigos/conexion.jsp';







?>



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
	<title>Tocupo Colombia |Avisos de independientes </title>
</head>
<body>
<!-- Encabezado -->
<header >
<div class="container">
<div class="main row">
<article class="col-lg-2">
<!-- logo header -->
<a href="index2.jsp"><img src="img/logo_header.png" class="img-responsive cursormano" alt="Imagen responsive"></a>
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
 <div class="container-fluid">
	<aside class="col-xs-12  col-sm-12 col-md-12">
		
		
	</aside >
	</div>
    </section>
 </div>

<div class="container">
<div class="main row">
<article class="col-lg-12">
<!-- Modulacion -->
<div class="container" style="margin-top: 10px;">
    <div class="row">
        <div class="col-sm-3 col-md-3">
            <div class="panel-group" id="accordion">

                <div class="panel panel-default">
                <h5><b><center>Personaliza tu busqueda</center></b></h5>
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-th">
                            </span>Categorias</a>
                        </h3>
                    </div>
                    <div id="collapseOne" class="panel-collapse in">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=publicaciones">Servicios del hogar</a> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=clientes">Construcción </a>
                                    </td>
                                </tr>
                                
                            </table>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span class="glyphicon glyphicon-folder-close">
                            </span>Servicios</a>
                        </h3>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-pencil text-primary"></span><a href="menu_inicio.jsp?mod=membresia">---</a>
                                    </td>
                                </tr>
                                
                                
                            </table>
                        </div>
                    </div>
                </div>
                
                
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"><span class="glyphicon glyphicon-file">
                            </span>Ciudad</a>
                        </h3>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-tasks"></span><a href="menu_inicio.jsp?mod=nivel">---</a>
                                    </td>
                                </tr>
                                
                                
                            </table>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span class="glyphicon glyphicon-user">
                            </span>Barrio</a>
                        </h3>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=datos">---</a>
                                    </td>
                                </tr>
                                
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9">
            <div class="well">
                
                <?php
if(@ $_GET['mod']=="")
	{
	require_once("modulos/inicio_buscador.jsp");
	}
	else
	if(@ $_GET['mod']=="membresia")
	{
	require_once("modulos/membresia.jsp");
	}
	else
    if(@ $_GET['mod']=="perfil_servicios")
    {
    require_once("modulos/perfil_servicios.jsp");
    }

		
?>
            </div>
        </div>
    </div>
</div>

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
    <button type="button" class="btn btn-success colorbotones" >Descarga la APP gratis</button>
                    
    </div>
</footer>
<!-- Fin Pie de pagina -->
</body>
<!-- Bootstrap -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- fin bootstrap -->
</html>

