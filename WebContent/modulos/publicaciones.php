<?php

include 'conexion.jsp';

$log = mysqli_query($conexion,"SELECT * FROM independiente WHERE correo ='$correo'");

          $row = mysqli_fetch_array($log);
          
          $idindependiente = $row['idindependiente'];

$consulta = mysqli_query($conexion,"SELECT idindependiente,nombres,apellidos,tipo_categorias,nombre,titulo,nombre_foto FROM publicaciones 
INNER JOIN CATEGORIAS 
on publicaciones.categorias_idcategorias = categorias.idcategorias
INNER JOIN SERVICIO 
on publicaciones.servicio_idservicio = servicio.idservicio
INNER JOIN INDEPENDIENTE
on publicaciones.independiente_ndocumento = independiente.idindependiente where independiente.idindependiente = $idindependiente;");
?>
<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="estilos.css">
<h1> Publicaciones</h1>
<br><br>
<?php
      while($row=$consulta->fetch_assoc()){
         
 
 ?>
<div class="row">

    <div class="row">
      <div class="col-md-3">
        <div class='col-md-3 col-lg-2'>
                 
                <?php echo '<img src="imgperfiles/'.$row['nombre_foto'].'" width="130" heigth="130">';?>
                 <input type="file" name="imagen" id="upload2" class="thumb" >
                   
            </div>
      </div>
      
      <div class="col-md-2">
        <b>NOMBRE:</b><br>
        <b>OCUPACION:</b><br>
        <b>SERVICIO:</b><br>
        <b>TITULO:</b><br>
        <b>REPUTACIÓN</b>

      </div>
      <div class="col-md-7">
      <?php  echo $row['nombres']." ".$row['apellidos']."<br>"; ?>
        <?php  echo $row['tipo_categorias']."<br>"; ?>
        <?php  echo $row['nombre']."<br>"; ?>
        <?php  echo $row['titulo']."<br>"; ?>
        <!-- Reputacion -->
        <div class="ec-stars-wrapper">
	<a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
	<a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
	<a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
	<a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
	<a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
       </div> <!-- fin reputacion -->
      </div>
    </div>

</div>

<hr class="hr-warning"><br>
<?php
}
?>
