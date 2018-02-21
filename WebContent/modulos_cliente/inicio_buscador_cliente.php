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
on publicaciones.independiente_ndocumento = independiente.idindependiente where independiente.nombres LIKE '%$idindependiente%' or independiente.apellidos LIKE '%$idindependiente%' or categorias.tipo_categorias LIKE '%$idindependiente%' or servicio.nombre LIKE '%$idindependiente%' or publicaciones.titulo LIKE '%$idindependiente%' or publicaciones.descripcion LIKE '%$idindependiente%' and independiente.ciudad_residencia LIKE '%$ciudad%' ");
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
      <form action="perfiless.jsp" method="post">
        
        <input type="text" name="id_servicio" value=" <?php echo $row['idindependiente']  ?> " hidden="hidden">
        
        

<input type="text" name="id_independiente" value=" <?php echo $row['idindependiente']  ?> " hidden="hidden">
        <b style="color: white;"><button class="btn btn-success" role="submit" name="enviar">Contactar</button></b>
        
      </form>
      </div>
    </div>

</div>

<hr class="hr-warning"><br>



<!-- BEGIN # MODAL LOGIN -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      
    

  </div>
    <!-- END # MODAL LOGIN -->

  <?php
}
}
?>

             


<?php
    
?>



