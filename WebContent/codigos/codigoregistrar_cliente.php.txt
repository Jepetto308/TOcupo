
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
			
			if(isset($_POST['registro_cliente'])){
				include 'conexion.jsp';
			$primer_nombre = $_POST['primer_nombre'];
			$segundo_nombre = $_POST['segundo_nombre'];
			$primer_apellido = $_POST['primer_apellido'];
			$segundo_apellido = $_POST['segundo_apellido'];
			$telefono = $_POST['telefono'];
            $correo = $_POST['correo'];
            $password = $_POST['password'];
            $rpassword = $_POST['rpassword'];
             
           

       
				

				if ($password = $rpassword) {

					$registro = "INSERT INTO cliente (primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,
					telefono,correo,password,rol_idrol) VALUES ('$primer_nombre','$segundo_nombre', '$primer_apellido',
					'$segundo_apellido','$telefono', '$correo','$password',2);";
					
					$log = mysqli_query($conexion,$registro) or die(mysqli_error($conexion));
					echo '<script> window.location="../login.jsp"; </script>';
				}
				else{
					echo '<script> alert("Las contraseñas no coinciden.");</script>';
					echo '<script> window.location="../codigoregistrar_cliente.jsp"; </script>';
				}
			}
		?>	
</body>
</html>
