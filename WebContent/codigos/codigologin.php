
<?php
	session_start(); 
?>
<!DOCTYPE html>
<html>
<head>
<head>
	<title>Validando...</title>
	<meta charset="utf-8">
</head>
</head>
<body>
		<?php
		if(isset($_POST['enviarlogin'])){

		include 'conexion.jsp';
		$usuario = $_POST['email'];
        $password = $_POST['password'];


            $sql_cliente = "SELECT rol_idrol FROM cliente WHERE correo LIKE '$usuario'";
		$consulta_cliente = mysqli_query($conexion,$sql_cliente)or die("problemas".mysqli_error($conexion));
        
        if (mysqli_num_rows($consulta_cliente)>0) {
					$row_cliente = mysqli_fetch_array($consulta_cliente);
					$rol_cliente = $row_cliente['rol_idrol'];
                      echo $rol_cliente;
				  	}


			 $sql = "SELECT rol_idrol FROM independiente WHERE correo LIKE '$usuario'";
			$consulta = mysqli_query($conexion,$sql)or die("problemas".mysqli_error($conexion));

				if (mysqli_num_rows($consulta)>0) {
					$row = mysqli_fetch_array($consulta);
					$rol_independiente = $row['rol_idrol'];
                 echo $rol_independiente;
				}

			


				if ($rol_independiente = 1) {
				  		# code...
				  	  	

				$sql = "SELECT * FROM independiente WHERE correo LIKE '$usuario' AND pass LIKE '$password'";
				$consulta = mysqli_query($conexion,$sql)or die("problemas".mysqli_error($conexion));

				if (mysqli_num_rows($consulta)>0) {
					$row_independiente = mysqli_fetch_array($consulta);
					$_SESSION["user"] = $row_independiente['correo'];
				  	echo 'Iniciando sesión para '.$_SESSION['user'].' <p>';
					echo '<script> window.location="../menu_inicio.jsp"; </script>';
				}
			}

					if ($rol_cliente = 2) {
					
						
		$sql_cliente = "SELECT * FROM cliente WHERE correo LIKE '$usuario' AND password LIKE '$password'";
		$consulta_cliente = mysqli_query($conexion,$sql_cliente)or die("problemas".mysqli_error($conexion));
       
        if (mysqli_num_rows($consulta_cliente)>0) {
					$row_cliente = mysqli_fetch_array($consulta_cliente);
					$_SESSION["cliente"] = $row_cliente['correo'];

				  	echo 'Iniciando sesión para '.$_SESSION['cliente'].' <p>';
					echo '<script> window.location="../menu_cliente.jsp"; </script>';
				}else{
					echo '<script> alert("Usuario o contraseña incorrectos.");</script>';
					echo '<script> window.location="../login.jsp"; </script>';
				}

					}else{
					echo '<script> alert("Usuario o contraseña incorrectos.");</script>';
					echo '<script> window.location="../login.jsp"; </script>';
				}
				
			}
		
		?>	
</body>
</html>
