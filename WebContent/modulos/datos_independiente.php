<h1> Datos Independiente</h1>
<hr>
<form action="" method="post" >
<center >
<div class="foto_perfil_datos img-responsive" >
<?php

include 'conexion.jsp';

$correo = $_SESSION['user'] ;
$log = mysqli_query($conexion,"SELECT * FROM independiente WHERE correo ='$correo'");

if (mysqli_num_rows($log)>0) {
					$row = mysqli_fetch_array($log);
					$_SESSION["user"] = $row['correo'];
				  	echo '<img src="imgperfiles/'.$row['nombre_foto'].'" width="130" heigth="130">';
					
				
?>

</div>
<hr>
<h3>Personales</h3>
<div class="form-inline ">

  <div class="form-group " style="margin-top: 1%;">
    <label for="email">Nombres</label>
    <input type="email" class="form-control" id="email" value="<?php echo $row['nombres'] ?>">
  </div>

  <div class="form-group">
    <label for="pwd">Apellidos</label>
    <input type="text" class="form-control" id="pwd" value="<?php echo $row['apellidos'] ?>">
  </div>

</div>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Telefono</label>
    <input type="email" class="form-control" id="email">
  </div>

  <div class="form-group">
    <label for="pwd">Celular</label>
    <input type="text" class="form-control" id="pwd">
  </div>

</div>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Direccion</label>
    <input type="email" class="form-control" id="email">
  </div>

</div>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Departamento</label>
    <select class="form-control" id="exampleSelect1" >
      <option value="">Seleccione departamento</option>
      <option value="">1</option>
      <option value="">2</option>
      <option value="">3</option>
      <option value="">4</option>
    </select>
  </div>

  <div class="form-group">
    <label for="pwd">Ciudad</label>
    <select class="form-control" id="exampleSelect1">
      <option value="">Seleccione ciudad</option>
      <option value="">1</option>
      <option value="">2</option>
      <option value="">3</option>
      <option value="">4</option>
    </select>
  </div>


</div>
<hr>
<div class="form-inline " style="margin-top: 1%;">

   <div class="form-group">
    <label for="exampleSelect1">Ocupacion</label>
    <select class="form-control" id="exampleSelect1">
      <option value="<?php 
        echo $row['categorias_idcategorias']; ?>">
       <?php 
        $categoria = $row['categorias_idcategorias'];


       $log2 = mysqli_query($conexion,"SELECT * FROM categorias WHERE idcategorias ='$categoria'");

       if (mysqli_num_rows($log2)>0) {
                    $rowcat = mysqli_fetch_array($log2);
                    $tipo = $rowcat['tipo_categorias'];
                   echo $tipo;
                    
                } ?> </option>

                <option>--------------</option>

                <?php 
                 
                 $query="select * from categorias"; 
                 $res=mysqli_query($conexion,$query);

                while($row=mysqli_fetch_array($res)){?>
<option value="<?php echo $row['idcategorias']?>"> <?php echo $row['tipo_categorias']?></option>
<?php } ?>
      
    </select>
  </div>

  <a href="#"><h2><span class="glyphicon glyphicon-plus"> </span></h2></a>

</div>

<hr>
<h3>Referencias Personales</h3>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Nombre y apellido</label>
    <input type="email" class="form-control" id="email">
  </div>

  <div class="form-group">
    <label for="pwd">numero</label>
    <input type="text" class="form-control" id="pwd">
  </div>


</div>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Nombre y apellido</label>
    <input type="email" class="form-control" id="email">
  </div>

  <div class="form-group">
    <label for="pwd">numero</label>
    <input type="text" class="form-control" id="pwd">
  </div>


</div>

<hr>
<h3>Referencias Familiares</h3>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Nombre y apellido</label>
    <input type="email" class="form-control" id="email">
  </div>

  <div class="form-group">
    <label for="pwd">numero</label>
    <input type="text" class="form-control" id="pwd">
  </div>


</div>

<div class="form-inline " style="margin-top: 1%;">

  <div class="form-group ">
    <label for="email">Nombre y apellido</label>
    <input type="email" class="form-control" id="email">
  </div>

  <div class="form-group">
    <label for="pwd">numero</label>
    <input type="text" class="form-control" id="pwd">
  </div>


</div>
<div class="form-inline " style="margin-top: 3%;">

<button id="btn-signup" type="submit" class="btn btn-success" name="enviarlogin" value="eviarlogin">Actualizar</button>

</div>

</center>
</form>
<?php
}

?>