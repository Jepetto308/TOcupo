<h1> Hola! <?php
include 'conexion.jsp';

$correo = $_SESSION['cliente'] ;
$consulta = mysqli_query($conexion,"SELECT * FROM independiente WHERE correo ='$correo'");

if (mysqli_num_rows($consulta)>0) {
                    $row = mysqli_fetch_array($consulta);
                    $nombres = $row['nombres'];
                   
                    
                }

 echo "  ".$nombres.''." "." "." " ?></h1>
Aún no contactas nuestros independientes.


