

<h1>Sub-categoria</h1>



<div class="form-inline ">

  <div class="form-group " style="margin-top: 1%;">
    
  

  </div>
   
</div>

<form action="codigos/codigoofrecer2.jsp" method="post">
<div class="form-inline ">

  <div class="form-group  col-sm-12 col-md-12" style="margin-top: 1%;">
    <label for="text"><h3>Titulo del servicio:</h3></label><br>
    -------
  </div>

</div>
<div class="form-inline ">

  <div class="form-group form-group  col-sm-12 col-md-12" style="margin-top: 1%;">
    <label for="email"><h3>Descripción servicio:</h3></label><br>
    ------------
  <br>
  
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
    <label for="email">Valor Aprox.</label>
   <select class="form-control" id="exampleSelect1">
      <option value="">$</option>
      <option value="">US$</option>
     
    </select>
  </div>

    <input type="number" name="precio" class="form-control" id="pwd" placeholder="Ej: 100000"><br><br>


    <center>
    <div class="col-sm-12 controls">
      <button id="btn-signup" type="submit" class="btn btn-success" name="enviarofrecer" value="eviarlogin">CONTACTAR</button>

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