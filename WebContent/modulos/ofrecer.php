

<h2>Elige un Servicio de tu Categoria para ofrecer</h2>



<div class="form-inline ">

  <div class="form-group " style="margin-top: 1%;">
    <label for="email">Categoria: </label>
   <?php 
        $categoria = $row['categorias_idcategorias'];


       $log2 = mysqli_query($conexion,"SELECT * FROM categorias WHERE idcategorias ='$categoria'");

       if (mysqli_num_rows($log2)>0) {
                    $rowcat = mysqli_fetch_array($log2);
                    $tipo = $rowcat['tipo_categorias'];
                   echo $tipo;
                    
                }


  
$log2 = mysqli_query($conexion,"SELECT * FROM servicio WHERE categorias_idcategorias ='$categoria'");
if (mysqli_num_rows($log2)>0) {
                    $rowcat = mysqli_fetch_array($log2);
                    $tipo = $rowcat['nombre'];
                   
                    
                }
                 ?>


<label for="email">Tipo Servicio: </label>
  </div>
   
</div>

<form action="menu_inicio.jsp?mod=ofrecer2" method="post">
<div class="form-group">
    <label for="exampleSelect1">Servicios</label>
    <select class="form-control" id="exampleSelect1" name="servicio">
      <option value="<?php 
        echo $rowcat['idservicio']; ?>">
       <?php 
        
       echo $rowcat['nombre'];

       

        ?> </option>

                

                <?php 
                 
                 $query="select * from servicio"; 
                 $res=mysqli_query($conexion,$log2);

                while($row=mysqli_fetch_array($log2)){?>
<option value="<?php echo $row['idservicio']?>"> <?php echo $row['nombre']?></option>
<?php } ?>
      
    </select>
  </div>

<br>

    <center>
    <div class="col-sm-12 controls">
    
      <button id="btn-signup" type="submit" class="btn btn-success" name="enviarservicio" value="Eviar login">Continuar</button>
    </form>
     </div>
     </center>
     <br><br>

  </div>


