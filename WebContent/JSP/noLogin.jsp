<%-- 
    Document   : noLogin
    Created on : 6/10/2017, 11:21:13 PM
    Author     : Jefferson Palacios Torres | Correo: jefferson308@hotmail.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Outside</title>
    </head> 
    <body>
    	<c:set var="context" value="${pageContext.request.contextPath}" />
        <section class="contenido wrapper">
            <h1 style="text-align: center">Te quedaste sin conexión, debes <a href="${context}">iniciar Sesión</a></h1>
        </section>
    </body>
</html>
