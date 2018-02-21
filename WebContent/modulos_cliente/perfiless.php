<?php
session_start();
include 'codigos/conexion.jsp';

if(isset($_SESSION['cliente'])) {



$correo = $_SESSION['cliente'] ;
$log = mysqli_query($conexion,"SELECT * FROM cliente WHERE correo ='$correo'");

if (mysqli_num_rows($log)>0) {
                    $row = mysqli_fetch_array($log);
                    $nombres = $row['primer_nombre'];
                   $idindependiente = $row['idcliente'];
                    
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
<form action="menu_busqueda_cliente.jsp?mod=" method="post">
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
<h1> Servicio del independiente <?php echo $_POST['id_independiente'];?> </h1>

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
<?php
}else{
    echo '<script>window.location="menu_busqueda.jsp?mod=";</script>';
}

