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
    <li><a href="menu_cliente.jsp?mod=">Mi cuenta</a></li>
    <li class="divider"></li>
    <li><a href="salir.jsp">salir</a></li>
  </ul>
   
</div>

<div class="btn-group">

  <div class=" dropdown-toggle cursormano" data-toggle="dropdown">
  
  <h4>| <span class="glyphicon glyphicon-lock" style="color: #fff;"> </span>
  <font color="#fff">
 Ayuda
 </font>
</h4></a>
  </div>


  <ul class="dropdown-menu" role="menu">
    <li><a href="">Panel de ayudas.</a></li>
       
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
<div class="main row well">
<article class="col-lg-6 " >
<!-- Modulacion -->
<h3> Datos del independiente <?php 
$id_independiente = $_POST['id_independiente'];
#echo $id_independiente;
?> </h3>
<div class="col-xs-12 col-sm-12 col-md-4">
<?php



$log = mysqli_query($conexion,"SELECT * FROM independiente WHERE idindependiente ='$id_independiente'");

if (mysqli_num_rows($log)>0) {
                    $row = mysqli_fetch_array($log);
                    $_SESSION["user"] = $row['correo'];
                    echo '<br> <img src="imgperfiles/'.$row['nombre_foto'].'" width="170" heigth="170"> <br>';
                    
                }
?></div><br>
<div class="col-xs-6 col-sm-6 col-md-6">

<!-- Button trigger modal -->

<button id="btn-signup"  class="btn btn-info" data-toggle="modal" data-target="#myModal" style="border-radius: 5px;">Chatea Conmigo</button>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
       
      </div>
      <div class="modal-body">
        <div style="text-align: center;">
<div class="container">
    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-primary">
                <div class="panel-heading" id="accordion"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="text-decoration: none; color: white;">
                    <span class="glyphicon glyphicon-comment" ></span> Chatea con JOSE
                    <div class="btn-group pull-right">
                        <a type="button" class="btn btn-default btn-xs" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </a>
                    </div>
                    </a>
                </div>
            <div class="panel-collapse collapse" id="collapseOne">
                <div class="panel-body">
                    <ul class="chat" style="list-style: none; float: left; width: 80%;">
                        <li class="left clearfix"><span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff&text=YO" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix" style="background-color: #ccc; border-radius: 10px; float: ;">
                                <div class="header" >
                                    <strong class="primary-font " style="">Maria</strong> <small class="pull-right text-muted">
                                        <span class="glyphicon glyphicon-time" style="float:left;"></span>Hace 12 min</small>
                                </div>
                                <p >
                                    Hola<br>
                                    como estas?
                                </p>
                            </div>
                        </li>
                        <li class="right clearfix"><span class="chat-img pull-right">
                            <img src="http://placehold.it/50/FA6F57/fff&text=JOSE" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix" style=" ">
                                <div class="header">
                                    <small class=" text-muted"><span class="glyphicon glyphicon-time"></span>Hace 13 min</small>
                                    <strong class="pull-right primary-font">Independiente</strong>
                                </div>
                                <p>
                                    Hola
                                </p>
                            </div>
                        </li>
                        <li class="left clearfix"><span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff&text=YO" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix" style="background-color: #ccc; border-radius: 10px; float: ;">
                                <div class="header" >
                                    <strong class="primary-font " style="">Maria</strong> <small class="pull-right text-muted">
                                        <span class="glyphicon glyphicon-time" style="float:left;"></span>Hace 14 min</small>
                                </div>
                                <p >
                                    Puedes cotizarme un servicio?

                                    
                                </p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="btn-input" type="text" class="form-control input-sm" placeholder="Escribe tu mensaje..." />
                        <span class="input-group-btn">
                            <button class="btn btn-warning btn-sm" id="btn-chat">
                                Enviar</button>
                        </span>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>

</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        
      </div>
    </div>
  </div>
</div>
</div><br><br>
<div class="col-xs-6 col-sm-6 col-md-6">
<button id="btn-signup" type="submit" class="btn btn-success" name="enviarlogin" value="eviarlogin" style="border-radius: 5px;">Ver Certificados</button></div><br><br>
 <div class="ec-stars-wrapper col-xs-12 col-sm-12 col-md-12">
  <a href="#" data-value="1" title="Votar con 1 estrellas" style="color: #29C948;">&#9733;</a>
  <a href="#" data-value="2" title="Votar con 2 estrellas" style="color: #29C948;">&#9733;</a>
  <a href="#" data-value="3" title="Votar con 3 estrellas" style="color: #29C948;">&#9733;</a>
  <a href="#" data-value="4" title="Votar con 4 estrellas" style="color: #29C948;">&#9733;</a>
  <a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
       </div> <!-- fin reputacion --><br><br>
 

<!-- categorias visitadas -->

<div class="col-xs-6 col-sm-6 col-md-2">
        
        <b>NOMBRE:</b><br>
        <b>APELLIDO:</b><br>
        <b>TELEFONO:</b><br>
        <b>CELULAR:</b><br>
        <b>CIUDAD:</b><br>
        <b>BARRIO</b><br><br>
        <b>Estado:</b><br>
        
         

      </div>

      <div class="col-xs-6 col-sm-6 col-md-4">
      <?php  echo $row['nombres']."<br>"; ?>
        <?php  echo $row['apellidos']."<br>"; ?>
        Poner <br>
        Poner <br>
        <?php echo $row['ciudad_residencia']."<br>"; ?>
        Poner<br><br>
        <font color="#green"><b>En linea</b></font>

        <!-- Reputacion -->
       
       
      </div>

      
      </article>

<article class="col-lg-6 " style="background-color: rgba(0, 132, 183, 0.08); border-radius: 15px;" >
      <H3>Informacion del servicio </H3>
<br><br>
<div class="col-xs-6 col-sm-6 col-md-6">
<b>Categoria:</b>  Servicios del hogar
</div>
<div class="col-xs-6 col-sm-6 col-md-6">
  <b>Tipo de Servicio:</b> Plomero
  </div><br><br><br>
  
  <div class="col-xs-12 col-sm-12 col-md-12">
  <b>Descripción:</b> Analisis de BD, creacion de modelos entidad relacion
  </div><br><br><br>
   <div class="col-xs-12 col-sm-12 col-md-8">
  <b>Valor aproximado del servicio: $</b> ******** 
  </div><br><br>
   <div class="col-xs-12 col-sm-12 col-md-8">
 
  <h3>Otros Servicios</h3>
              
  <table class="table">
    <thead>
      <tr>
        <th>Nombre</th>
        <th>Valor Aprox</th>
        
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Baños</td>
        <td>12.000</td>
        
      </tr>
      <tr>
        <td>Escapes de agua</td>
        <td>20.500</td>
        
      </tr>
      <tr>
        <td>Puertas</td>
        <td>20.500</td>
        
      </tr>
      
    </tbody>
  </table>

  </div><br><br>
<div class="col-xs-12 col-sm-12 col-md-8">
   <button id="btn-signup" type="submit" class="btn btn-success" name="enviarlogin" value="eviarlogin" style="border-radius: 5px;">Ver imagenes</button><br><br>
  </div><br>

</article>
<hr class="hr-warning"><br>

<article class="col-lg-12" >
<!-- Modulacion -->
<h3> Pregúntale al Independiente </h3>

 <div class="col-xs-12 col-md-12">
    <div class="form-group">
  
  <textarea class="form-control" rows="5" id="comment"></textarea>
  Tiempo aproximado de respuestas 54 minutos
</div>
    <div class="form-group col-xs-6 col-md-3">
        <div class="input-group">
            <button id="btn-signup" type="submit" class="btn btn-success" name="enviarlogin" value="eviarlogin" style="border-radius: 5px;">Preguntar</button>
            
        </div>
    </div>
</div>

      </article>
<hr class="hr-warning"><br>

<article class="col-lg-12 " >
      <H4>Últimas Preguntas</H4>

</article>

<article class="col-lg-12 " >
      <H3>Opiniones <?php echo $id_independiente; ?></H3>

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

