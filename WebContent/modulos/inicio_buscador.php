<?php

include 'conexion.jsp';

$idindependiente = $_POST['buscador_inicio'];
$ciudad = $_POST['ciudad'];

$consulta = mysqli_query($conexion,"SELECT idindependiente,nombres,apellidos,tipo_categorias,ciudad_residencia,nombre,titulo,nombre_foto FROM publicaciones 
INNER JOIN CATEGORIAS 
on publicaciones.categorias_idcategorias = categorias.idcategorias
INNER JOIN SERVICIO 
on publicaciones.servicio_idservicio = servicio.idservicio
INNER JOIN INDEPENDIENTE
on publicaciones.independiente_ndocumento = independiente.idindependiente where independiente.nombres LIKE '%$idindependiente%' or independiente.apellidos LIKE '%$idindependiente%' or categorias.tipo_categorias LIKE '%$idindependiente%' or servicio.nombre LIKE '%$idindependiente%' or servicio.categoria LIKE '%$idindependiente%'  or servicio.tipo_servicio LIKE '%$idindependiente%' or publicaciones.titulo LIKE '%$idindependiente%' or publicaciones.descripcion LIKE '%$idindependiente%' and independiente.ciudad_residencia LIKE '%$ciudad%' ");
?>
<link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="estilos.css">
<h1> Publicaciones</h1>
<br>
<?php
      echo "<b>Resultados para: </b>".$idindependiente."<br><br>";


  # code...


if(isset($_POST['enviar'])) {


     while($row=$consulta->fetch_assoc()){
         
 
 ?>
<div class="row">

    <div class="row">
      <div class="col-md-3">
        <div class='col-md-3 col-lg-2'>
                 
                <?php echo '<img src="imgperfiles/'.$row['nombre_foto'].'" width="130" heigth="130">';?>
                 <input type="" name="imagen" id="" class="thumb" hidden="hidden" >
                   
            </div>
      </div>
      
      <div class="col-xs-4 col-sm-4 col-md-3">
        <b>NOMBRE:</b><br>
        <b>CATEGORIA:</b><br>
        <b>OCUPACION:</b><br>
        <b>REPUTACIÓN</b>


      </div>
      <div class="col-xs-8 col-sm-8 col-md-3">
      <?php  echo $row['nombres']." ".$row['apellidos']."<br>"; ?>
        <?php  echo $row['tipo_categorias']."<br>"; ?>
        <?php  echo $row['nombre']."<br>"; ?>


        <!-- Reputacion -->
        <div class="ec-stars-wrapper">
  <a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
  <a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
  <a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
  <a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
  <a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
       </div> | <!-- fin reputacion -->
       <?php  echo $row['ciudad_residencia']."<br>"; ?>
      </div>

      <div class="col-md-3">
      <form action="menu_busqueda.jsp?mod=perfil_servicios" method="post">
        
        <input type="text" name="id_servicio" value=" <?php echo $row['idindependiente']  ?> " hidden="hidden">
<form action="menu_busqueda.jsp?mod=" method="post">
        <b style="color: white;"><a href="#" class="btn btn-success" role="submit" data-toggle="modal" data-target="#login-modal" name="enviar">Contactar</a></b>
        
      </form>
      </div>
    </div>

</div>

<hr class="hr-warning"><br>


<!-- BEGIN # MODAL LOGIN -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" align="center">
          <img class="img-circle img-reponsive" id="img_logo" src="img/logoazul.png" width="150" height="150">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
          </button>
        </div>
                
                <!-- Begin # DIV Form -->
                <div id="div-forms">
                
                    <!-- Begin # Login Form -->
                    
                    <div class="modal-body">
                <div id="div-login-msg msg-lg">
                                <div id="icon-login-msg" class="glyphicon glyphicon-chevron-right"></div>
                                <span id="text-login-msg">Logueate para contactar mas independiente</span>
                            </div>
            <form name="login" action="codigos/codigologin.jsp" method="POST">
                <input id="login_username" class="form-control" type="text" placeholder="Email" name="email" required>
                <input id="login_password" class="form-control" type="password" placeholder="Contraseña" name="password" required>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Recordarme
                                </label>
                            </div>
                  </div>
                <div class="modal-footer">
                            <div>
                                <button type="submit" class="btn btn-primary btn-lg btn-block" name="enviarlogin">Entrar</button>
                            </div>
                </form>

                  <div>
                                <button id="login_lost_btn" type="button" class="btn btn-link">¿Olvidaste tu contraseña?</button>
                               <a href="registrar.jsp"> <button id="" type="button" class="btn btn-link">Registrate</button> </a>
                            </div>
                </div>
                    
                    <!-- End # Login Form -->
                    
                    <!-- Begin | Lost Password Form -->
                    <form id="lost-form" style="display:none;">
                  <div class="modal-body">
                <div id="div-lost-msg">
                                <div id="icon-lost-msg" class="glyphicon glyphicon-chevron-right"></div>
                                <span id="text-lost-msg">Type your e-mail.</span>
                            </div>
                <input id="lost_email" class="form-control" type="text" placeholder="E-Mail (type ERROR for error effect)" required>
                  </div>
                <div class="modal-footer">
                            <div>
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Send</button>
                            </div>
                            <div>
                                <button id="lost_login_btn" type="button" class="btn btn-link">Log In</button>
                                <button id="lost_register_btn" type="button" class="btn btn-link">Register</button>
                            </div>
                </div>
                    </form>
                    <!-- End | Lost Password Form -->
                    
                    <!-- Begin | Register Form -->
                    <form id="register-form" style="display:none;">
                    <div class="modal-body">
                <div id="div-register-msg">
                                <div id="icon-register-msg" class="glyphicon glyphicon-chevron-right"></div>
                                <span id="text-register-msg">Register an account.</span>
                            </div>
                <input id="register_username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" required>
                            <input id="register_email" class="form-control" type="text" placeholder="E-Mail" required>
                            <input id="register_password" class="form-control" type="password" placeholder="Password" required>
                  </div>
                <div class="modal-footer">
                            <div>
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
                            </div>
                            <div>
                                <button id="register_login_btn" type="button" class="btn btn-link">Log In</button>
                                <button id="register_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
                            </div>
                </div>
                    </form>
                    <!-- End | Register Form -->
                    
                </div>
                <!-- End # DIV Form -->
                
      </div>
    </div>
  </div>
    <!-- END # MODAL LOGIN -->

  <?php
}
}
?>

             


<?php
    
?>



