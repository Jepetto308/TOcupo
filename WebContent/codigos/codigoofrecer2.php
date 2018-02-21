
<?php
	session_start(); 
	include "../conexion.jsp"
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

		$correo = $_SESSION['user'] ;
$log = mysqli_query($conexion,"SELECT * FROM independiente WHERE correo ='$correo'");

if (mysqli_num_rows($log)>0) {
                    $row = mysqli_fetch_array($log);
                    $idindependiente = $row['idindependiente'];
                   echo $idindependiente;
                    
                }
			
			if(isset($_POST['enviarofrecer'])){
				include 'conexion.jsp';
			$titulo = $_POST['titulo'];
			$descripcion = $_POST['descripcion'];
			$fecha = date('Y-m-d');
			$precio = $_POST['precio'];
			$categoria = $_POST['categoria'];
			$subcategoria = $_POST['subcategoria'];
             
             /*$formatos = array('.jpg', '.png', '.gif');
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
             }*/

				

				if (isset($_POST['enviarofrecer'])) {

					$registro = "INSERT INTO publicaciones (titulo,descripcion,fecha,precio,independiente_ndocumento,categorias_idcategorias,servicio_idservicio) VALUES ('$titulo','$descripcion','$fecha',$precio, $idindependiente, $categoria, $subcategoria);";
					
					$log = mysqli_query($conexion,$registro) or die(mysqli_error($conexion));
					echo '<script> alert("Registro exitoso"); </script>';
					echo '<script> window.location="../menu_inicio.jsp?mod=ofrecer"; </script>';
				}
				else{
					echo '<script> alert("Las contraseñas no coinciden.");</script>';
					echo '<script> window.location="../registrar.jsp"; </script>';
				}
			}
		?>	
</body>
</html>
