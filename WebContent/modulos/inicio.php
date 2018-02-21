<h1> Hola! <?php
include 'conexion.jsp';

$correo = $_SESSION['user'] ;
$log = mysqli_query($conexion,"SELECT * FROM independiente WHERE correo ='$correo'");

if (mysqli_num_rows($log)>0) {
                    $row = mysqli_fetch_array($log);
                    $nombres = $row['nombres'];
                   
                    
                }

 echo "  ".$nombres.''." "." "." " ?></h1>
Aún no Actualizas todos tus datos, ve a <span class="glyphicon glyphicon-user"></span>configuracion -> <a href="menu_inicio.jsp?mod=datos">Datos Personales.</a>


