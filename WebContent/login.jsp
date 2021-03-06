<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
<link rel="icon" href="img/favicon.png" type="image/x-icon" />
<title>�Hola! Ingresa tu Email o identificacion</title>
</head>
<body>
	
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<input type="hidden" name="contexto" id="contexto" value="${context}"/>

	<!-- Encabezado -->
	<header>
		<div class="container">
			<div class="main row">
				<article class="col-lg-2">
					<!-- logo header -->
					<a href="index.jsp"><img src="img/logo_header.png"
						class="img-responsive" alt="Imagen responsive"></a>
					<!-- Fin logo header -->
				</article>

				<!-- logo header -->
				<form action="menu_busqueda.jsp?mod=" method="post">
					<div class="col-lg-3">
						<div id="imaginary_container">
							<div class="form-group">
								<input type="text" name="buscador_inicio" class="form-control"
									id="inputSeleccionado" placeholder="Buscar independiente">
							</div>
						</div>
					</div>

					<div class="col-lg-3">
						<div id="imaginary_container">
							<div class="input-group stylish-input-group form-group">
								<select class="form-control col-md-9" id="sel1" name="ciudad"
									required="required" placeholder="ciudad">
									<option>Ciudad</option>
									<option value="medellin">Medellin</option>
									<option value="bogota">Bogota</option>
								</select> <span class="input-group-addon">
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
					<h4>
						<a href="registrar.jsp"
							style="text-decoration: none; color: #fff;"> Registrate</a> | <a
							href="login.jsp" style="text-decoration: none; color: #fff;">
							Ingresa</a>
					</h4>
					<!-- Fin logo header -->
				</article>

			</div>
	</header>
	<!-- fin encabezado -->

	<section class="main row">
		<div class="container">
			<div class="col-xs-10  col-sm-10 col-md-10 login">

				<h2>Ingresar</h2>
				<hr>
				<div id="loginbox" style="margin-top: 20px;"
					class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<form role="form" name="login" action="<c:out value="${pageContext.request.contextPath}"/>/ControlLogin" method="POST">
						
						<div style="display: none" id="login-alert"
							class="alert alert-danger col-sm-12"></div>

						<form id="loginform" class="form-horizontal" role="form">

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input
									id="login-username" type="text" class="form-control"
									name="email" value="" placeholder="Email"
									required="required">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password" type="password" class="form-control"
									name="password" placeholder="Contrase�a" required="required">
							</div>



							<div class="input-group">
								<div class="checkbox">
									<label> <input id="login-remember" type="checkbox"
										name="remember" value="1"> Recordarme
									</label>
								</div>
							</div>


							<div style="margin-top: 10px" class="form-group">
								<!-- Button -->

								<div class="col-sm-12 controls">
									<button id="btn-signup" type="submit" class="btn btn-success"
										name="enviarlogin" value="eviarlogin">Acceder</button>

									<a href="">�Olvidaste tu contrase�a?</a><br>
									<br>

								</div>
							</div>

						</form>
						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									No tienes una Cuenta! <a href="registrar.jsp" onClick="">
										Registrate </a>
								</div>
							</div>
						</div>
				</div>

			</div>
	</section>
	</div>

	<div class="container">
		<div class="main row">
			<article class="col-lg-12">
				<!-- categorias Visitadas -->
				<h4></h4>
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
	<footer>
		<div class="container-fluid col-xs-12 col-sm-12"
			style="background-color: rgba(7, 7, 15, 0.1);">
			<center>
				<h6>
					Copyright A�o 2017 Tocupo Colombia LTDA. <font color="blue">
						Trabaja con nosotros | T�rminos y condiciones | Pol�ticas de
						privacidad |Ayuda | PQR
						<button type="button" class="btn btn-warning">Descarga
							la APP gratis</button>
		</div>
	</footer>
	<!-- Fin Pie de pagina -->
</body>
<!-- Bootstrap -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- fin bootstrap -->
</html>