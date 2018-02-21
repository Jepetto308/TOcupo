
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
			
			if(isset($_POST['enviarregistro'])){
				include 'conexion.jsp';
			$usuario = $_POST['email'];
			$nombres = $_POST['nombres'];
			$apellidos = $_POST['apellidos'];
			$membresia = $_POST['membresia'];
             $password = $_POST['password'];
             $rpassword = $_POST['rpassword'];
             $ocupacion = $_POST['ocupacion'];
             $formatos = array('.jpg', '.png', '.gif');
             $carpeta = 'imgperfiles';
             $nombrefoto = $_FILES['foto']['name'];
             $nombretmpfoto = $_FILES['foto']['tmp_name'];
             $rutaimg = "../imgperfiles/".$nombrefoto;
             $ext = substr($nombrefoto, strrpos($nombrefoto, '.'));

             if (in_array($ext, $formatos)) {
             	if (move_uploaded_file($nombretmpfoto, "../imgperfiles/$nombrefoto")) {
             		echo "felicitaciones";
             	}
             }else{
             	echo 'Archivo no permitido';
             }

				

				if ($password = $rpassword) {

					$registro = "INSERT INTO independiente (idindependiente,tipo_documento,nombres,apellidos,fecha_nacimiento,fecha_expedicion,correo,telefono,celular,direccion,ocupacion,pais_residencia,departamento_residencia,ciudad_residencia,referencia_personal1,referencia_personal2,referencia_ocupacional1,referencia_ocupacional2,num_personal1,num_personal2,num_ocupacional1,num_ocupacional2,pass,foto_perfil,nombre_foto,rol_idrol,reputacion_idreputacion,membresia_idmembresia,categorias_idcategorias) VALUES (NULL,'','$nombres','$apellidos','','','$usuario',1,1,'','','','','','','','','',1,1,1,1,'$password','$rutaimg','$nombrefoto',1,1,$membresia,$ocupacion);";
					
					$log = mysql_query($conexion,$registro) or die(mysqli_error($conexion));
					echo '<script> window.location="../login.jsp"; </script>';
				}
				else{
					echo '<script> alert("Las contraseñas no coinciden.");</script>';
					echo '<script> window.location="../registrar.jsp"; </script>';
				}
			}
		?>	
</body>
</html>
