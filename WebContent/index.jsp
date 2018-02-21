<!DOCTYPE html>
<html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="estilos.css">
	<!-- fin bootstrap -->
	
	<link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah" rel="stylesheet">
	<link rel="stylesheet" href="estilos.css">
	<style>
@import url('https://fonts.googleapis.com/css?family=Gloria+Hallelujah');
</style>
<link rel="icon" href="img/favicon.png" type="image/x-icon"/>
	<title>TeOcupo Colombia |Buscar Independientes </title>
</head>
<body>
<!-- Encabezado -->
<header >
<div class="container">
<div class="main row">
<article class="col-lg-2">
<!-- logo header -->
<img src="img/logo_header.png" class="img-responsive cursormano" alt="Imagen responsive">
<!-- Fin logo header -->
</article>


<!-- logo header -->
<form action="menu_busqueda.jsp?mod=" method="post">
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


<article class="col-lg-3">
<!-- logo header -->
<h4><a href="registrar.jsp" style="text-decoration: none; color: #fff;"> Registrate</a> | <a  href="login.jsp" style="text-decoration: none; color: #fff;"> Ingresa</a> </h4>
<!-- Fin logo header -->
</article>
	
</div>

</header>
<!-- fin encabezado -->

 <section class="main row">
 <div class="container-fluid">
	<aside class="col-xs-12  col-sm-12 col-md-12">
		<div id="carousel-1" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carousel-1" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-1" data-slide-to="1"></li>
				<li data-target="#carousel-1" data-slide-to="2"></li>
			</ol>

			<!-- Contenedor de slider -->
			
			<div class="carousel-inner" role="listbox">
			
				<div class="item active">
					<img src="img/banner/tocupo1.jpg" alt="" width="2000" height="450" class="img-responsive">
					<div class="carousel-caption">
					<form action="registrar.jsp">
						<button type="submit" class="btn btn-success"><h5>Registrate Ahora</h5></button>
						</form>
						<p>...</p>
					</div>
				</div>

				<div class="item">
					<img src="img/banner/tocupo2.jpg" alt=""  width="2000" height="450" class="img-responsive">
					<div class="carousel-caption">
						<button type="button" class="btn btn-success"><h5>Registrate Ahora</h5></button>
						<p>...</p>
					</div>
				</div>

				

				
			</div>
			<!-- controles -->
			<a href="#carousel-1" class="left carousel-control" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Anterior</span>
			</a>

			<a href="#carousel-1" class="right carousel-control" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Anterior</span>
			</a>

		</div>
		
	</aside >
	</div>
    </section>
 </div>

<div class="container">
<div class="main row">
<article class="col-lg-12">
<!-- categorias Visitadas -->
<h4>Categorias Visitadas</h4>
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