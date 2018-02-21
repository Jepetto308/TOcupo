

<h1>Ofrecer</h1>



<div class="form-inline ">

  <div class="form-group " style="margin-top: 1%;">
    <label for="email">Categoria: </label>
   <?php 
        $categoria = $row['categorias_idcategorias'];


       $log2 = mysqli_query($conexion,"SELECT * FROM categorias WHERE idcategorias ='$categoria'");

       if (mysqli_num_rows($log2)>0) {
                    $rowcat = mysqli_fetch_array($log2);
                    $tipocategoria = $rowcat['tipo_categorias'];
                    $idcategoria = $rowcat['idcategorias'];
                   echo $tipocategoria;
                    
                } ?>


<label for="email">Tipo Servicio: </label>
<?php 
        $categoria = $_POST['servicio'];


       $log2 = mysqli_query($conexion,"SELECT * FROM servicio WHERE idservicio ='$categoria'");

       if (mysqli_num_rows($log2)>0) {
                    $rowcat = mysqli_fetch_array($log2);
                    $tiposubcategoria = $rowcat['nombre'];
                    $idsubcategoria = $rowcat['idservicio'];
                   echo $tiposubcategoria;
                    
                } 

                /* codigo = idindependiente,nombre servicio,numero aleatorio */
                /* Para identificar publicacion y mis fotos publicacion */
                $codigo = $rowcat['independiente_ndocumento'].$rowcat['nombre'].rand(100,9999999999); 
                /* fin codigo */
                ?> 

  </div>
   
</div>

<form action="codigos/codigoofrecer2.jsp" method="post">
<div class="form-inline ">

  <div class="form-group  col-sm-12 col-md-8" style="margin-top: 1%;">
    <label for="text">Titulo del servicio</label>
    <input type="text" name="titulo" class="form-control" id="email" value="" size="70" placeholder="Ej: Arreglo de chapas a domicilio">
  </div>

</div>

<div class="form-inline ">

  <div class="form-group" style="margin-top: 3%;">
    <label for="email">Descripción servicio</label>
  <br>
  <textarea class="form-control" name="descripcion" rows="5" cols="70"  id="comment"></textarea>
  </div>

</div>


<!-- References: https://github.com/fancyapps/fancyBox -->
<link rel="stylesheet" href="//frontend.reklamor.com/fancybox/jquery.fancybox.css" media="screen">
<script src="//frontend.reklamor.com/fancybox/jquery.fancybox.js"></script>


	<div class="row">
		<div class='list-group gallery'>
            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-4'>
                <img id="image" width="200" height="200" src="img/subirfoto.png" class="thumb_p" />
                 <input type="file" name="imagen" id="upload" class="thumb_p">
                
            </div> <!-- col-6 / end -->

           <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image2" width="100" height="100" src="img/subirfoto.png" class="thumb" /> 
                 <input type="file" name="imagen" id="upload2" class="thumb" >
                   
            </div> <!-- col-6 / end -->

            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image3" width="100" height="100" src="img/subirfoto.png" class="thumb" /> 
                 <input type="file" name="imagen" id="upload3" class="thumb">
                   
            </div> <!-- col-6 / end -->

            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image4" width="100" height="100" src="img/subirfoto.png" class="thumb"/> 
                 <input type="file" name="imagen" id="upload4" class="thumb">
                   
            </div> <!-- col-6 / end -->
           
            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image5" width="100" height="100" src="img/subirfoto.png" class="thumb" /> 
                 <input type="file" name="imagen" id="upload5" class="thumb">
                   
            </div> <!-- col-6 / end -->

            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image6" width="100" height="100" src="img/subirfoto.png"  class="thumb" /> 
                 <input type="file" name="imagen" id="upload6" class="thumb">
                   
            </div> <!-- col-6 / end -->

            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image7" width="100" height="100" src="img/subirfoto.png" class="thumb" /> 
                 <input type="file" name="imagen" id="upload7" class="thumb">
                   
            </div> <!-- col-6 / end -->

           <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image8" width="100" height="100" src="img/subirfoto.png" class="thumb" /> 
                 <input type="file" name="imagen" id="upload8" class="thumb">
                   
            </div> <!-- col-6 / end -->

            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-2'>
                <img id="image9" width="100" height="100" src="img/subirfoto.png" class="thumb" /> 
                 <input type="file" name="imagen" id="upload9" class="thumb">
                   
            </div> <!-- col-6 / end -->
            
            
        </div> <!-- list-group / end -->
	</div> <!-- row / end -->


<div class="form-inline " style="margin-top: 0%;">

  <div class="form-group" class="form-control" id="exampleSelect1" ">
    <label for="email">Precio del servicio</label>
   <select class="form-control" id="exampleSelect1">
      <option value="">$</option>
      <option value="">US$</option>
     
    </select>
  </div>

    <input type="number" name="precio" class="form-control" id="pwd" placeholder="Ej: 100000"><br><br>

<input type="text" name="categoria" value="<?php echo $idcategoria; ?>" hidden="hidden">

<input type="text" name="subcategoria" value="<?php echo $idsubcategoria; ?>" hidden="hidden">
    <center>
    <div class="col-sm-12 controls">
      <button id="btn-signup" type="submit" class="btn btn-success" name="enviarofrecer" value="eviarlogin">Ofrecer Servicio</button>

     </div>
     </center>
     <br><br>

  </div>
  </form>
<script type="text/javascript">

    
document.getElementById("upload").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};



document.getElementById("upload2").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image2").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};


document.getElementById("upload3").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image3").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};


document.getElementById("upload4").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image4").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};
     


document.getElementById("upload5").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image5").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};


document.getElementById("upload6").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image6").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};


document.getElementById("upload7").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image7").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};

document.getElementById("upload8").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image8").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};

document.getElementById("upload9").onchange = function() {
  var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
  reader.onload = function(e) {
    //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
    document.getElementById("image9").src = e.target.result;
  };
 
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
};
</script>