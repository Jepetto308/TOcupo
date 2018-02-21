<?php
session_start();
include 'codigos/conexion.jsp';

if(isset($_SESSION['user'])) {



$correo = $_SESSION['user'] ;
$log = mysqli_query($conexion,"SELECT * FROM independiente WHERE correo ='$correo'");

if (mysqli_num_rows($log)>0) {
                    $row = mysqli_fetch_array($log);
                    $nombres = $row['nombres'];
                   $idindependiente = $row['idindependiente'];
                    
                }


$consulta = mysqli_query($conexion,"SELECT idindependiente,nombres,apellidos,tipo_categorias,nombre,titulo,nombre_foto FROM publicaciones 
INNER JOIN CATEGORIAS 
on publicaciones.categorias_idcategorias = categorias.idcategorias
INNER JOIN SERVICIO 
on publicaciones.servicio_idservicio = servicio.idservicio
INNER JOIN INDEPENDIENTE
on publicaciones.independiente_ndocumento = independiente.idindependiente where independiente.idindependiente = $idindependiente;");
$num_publicaciones = mysqli_num_rows($consulta);

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
<form action="menu_busqueda_iniciada.jsp?mod=" method="post">
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


<article class="col-lg-4" style="margin-top: 1%;">
<!-- logo header -->
<div class="btn-group">

  <div class=" dropdown-toggle cursormano" data-toggle="dropdown">
   <h4> <span class="glyphicon glyphicon-user" style="color: #fff;"> </span>
   <font color="#fff">
<?php echo "  ".$nombres.'<span class="caret"></span>'." "." "." " ?>
</font>
</h4>
  </div>

 
 
  <ul class="dropdown-menu" role="menu">
    <li><a href="menu_inicio.jsp?mod=">Mi cuenta</a></li>
    <li class="divider"></li>
    <li><a href="salir.jsp">salir</a></li>
  </ul>
   
</div>

<div class="btn-group">

  <div class=" dropdown-toggle cursormano" data-toggle="dropdown">
  
  <h4>| <span class="glyphicon glyphicon-lock" style="color: #fff;"> </span>
  <font color="#fff">
 Ofrecer
 </font>
</h4></a>
  </div>


  <ul class="dropdown-menu" role="menu">
    <li><a href="menu_inicio.jsp?mod=ofrecer">Servicios</a></li>
    <li class="divider"></li>
    <li><a href="salir.jsp">...</a></li>
  </ul>
</div>

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
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-th">
                            </span>Servicios</a>
                        </h3>
                    </div>
                    <div id="collapseOne" class="panel-collapse in">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=publicaciones">Publicaciones</a> <span class="label label-success"><?php echo $num_publicaciones; ?></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=clientes">Mis clientes</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=servicios">Servicios realizados</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=preguntas">Preguntas</a>
                                        <span class="label label-success">0</span>
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
                            </span>Facturación</a>
                        </h3>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-pencil text-primary"></span><a href="menu_inicio.jsp?mod=membresia">Membresia</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-flash text-success"></span><a href="menu_inicio.jsp?mod=guardadas">Guardadas</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-file text-info"></span><a href="menu_inicio.jsp?mod=cotizaciones">Cotizaciones</a>
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
                            </span>Reputación</a>
                        </h3>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-tasks"></span><a href="menu_inicio.jsp?mod=nivel">Nivel</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-comment text-success"></span><a href="menu_inicio.jsp?mod=comentarios">Comentarios</a>
                                        <span class="badge">42</span>
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
                            </span>Configuración</a>
                        </h3>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=datos">Datos Personales</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=certificados">Mis certificados</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=cambiar">Cambiar Contraseña</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="menu_inicio.jsp?mod=salir">Salir</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-trash text-danger"></span><a href="menu_inicio.jsp?mod=eliminar_cuenta" class="text-danger">
                                            Eliminar Cuenta</a>
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
