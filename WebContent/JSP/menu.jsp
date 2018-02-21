<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<script src="Complementos/js/js_franwork/jQuery3.2.js"></script>

<header id="header" class="encabezado">
            <nav class="navH">
                <div class="logo">
                    <img class="image" src="Complementos/Imagenes/datapets final.png" alt="">
                </div>
          
         
                <div class="logueado">
                    <label><i class="fa fa-user-circle-o" aria-hidden="true"></i> USUARIO: <c:out value="${user.nombre}" /> <c:out value="${user.apellidos}" /></label>
                    <label>ROL: <c:out value="${user.codigoRol}" /></label>
                </div>
             </nav>
        </header>
        
        <c:set var="context" value="${pageContext.request.contextPath}" />
       <div class="contenedor-menu">
               <a href="#" class="btn-menu">Menu <i class="icono fa fa-list" aria-hidden="true"></i></a>
               
               <ul class="Menu">
                   <li><a href="#"><i class="icono izquierda fa fa-home" aria-hidden="true"></i>INICIO</a></li>
                   <li><a  href="#"><i class="icono izquierda fa fa-user-circle" aria-hidden="true"></i>ENTIDADES<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                       <ul >
                            <li><a  href="${context}/ControlEmpleado">Empleados</a></li>
                            <li><a  href="${context}/ControlCliente">Clientes</a></li>
                         
                       </ul>
                   </li>
                   <li><a  href="#"><i class="icono izquierda fa fa-calendar" aria-hidden="true"></i>CITAS<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                        <ul >
                            <li><a href="${context}/ControlCita">Asignacion de citas</a></li>
                            
                         
                       </ul>
                   </li>
                   <li><a  href="#"><i class="icono izquierda fa fa-book" aria-hidden="true"></i>INFORMES<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                          <ul >
                            <li><a  href="${context}/ControlReportes?informe=CITA">Informe general de citas</a></li>

                       </ul>
                   </li> 
                    <li><a  href="#"><i class="icono izquierda fa fa-unlock-alt" aria-hidden="true"></i>SEGURIDAD<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                       <ul >
                            <li><a  href="${context}/ControlRoles">Roles</a></li>
                            <li><a  href="${context}/ControlUsuario">Usuarios</a></li>
                            <li><a  href="JSP/buscar_parametros.jsp">Parametros</a></li>
                         
                       </ul>
                   </li>
                    <li><a href="${context}/ControlLogin"><i class="icono izquierda fa fa-home" aria-hidden="true"></i>CERRAR SESIÓN</a></li>
               </ul>
        </div>