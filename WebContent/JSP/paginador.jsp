<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<input type="hidden" name="f_paginaActual" id="f_paginaActual" value="<c:out value="${f_paginaActual}" />"/>
<input type="hidden" name="numeroPaginas" id="numeroPaginas" value="<c:out value="${numeroPaginas}" />"/>
<c:if test="${numeroPaginas > 1}">
    <table border='0'>
        <tr>
            <c:set var="numeroRegistro" value="${1}"/>
            <c:set var="registrosMostrados" value="${1}"/>
            <c:if test="${f_paginaActual > 1}">
            	<td>
                    <a href='javascript:paginadorOnClick(${1})'>««</a>
                </td>
                <td>
                    <a href='javascript:paginadorOnClick(${f_paginaActual - 1})'>«</a>
                </td>
            </c:if>
            <c:forEach items="${cantidadPaginas}" var="objPaginador">
                <c:if test="${registrosMostrados <= 3 && (f_paginaActual -1 ) <= numeroRegistro}">
                    <td>
                        <a href='javascript:paginadorOnClick(<c:out value="${numeroRegistro}"/>)'>
                            <c:if test="${numeroRegistro == f_paginaActual}">
                                <font color='red'><b><c:out value="${numeroRegistro}"/></b></font>
                                </c:if>
                                <c:if test="${numeroRegistro != f_paginaActual}">
                                    <c:out value="${numeroRegistro}"/>
                                </c:if>
                        </a>
                    </td>
                    <c:set var="registrosMostrados" value="${registrosMostrados + 1}"/>
                </c:if>
                <c:set var="numeroRegistro" value="${numeroRegistro + 1}"/>
                
            </c:forEach>
            <c:if test="${numeroRegistro > 3 && (f_paginaActual+1) < numeroRegistro}">
                <td>...</td>
            </c:if>
            <c:if test="${f_paginaActual < numeroPaginas}">
                <td>
                    <a href='javascript:paginadorOnClick(${f_paginaActual + 1})'>»</a>
                </td>
            </c:if>
            <c:if test="${f_paginaActual < numeroPaginas}">
                <td>
                    <a href='javascript:paginadorOnClick(${numeroPaginas})'>»»</a>
                </td>
            </c:if>
            <td>
                <input ID="irA" class="irA" type='button' name='irA' value='Ir A' onclick='javascript:irAOnClick()'>
                <input type="number" id="paginaIrA" name="paginaIrA" value="<c:out value="${paginaIrA}"/>" style="width: 40px" size="1" maxlength="3" minlength="1"/>
            </td>
        </tr>
    </table>
</c:if>

<script>

function paginadorOnClick(paginaActual){
	$("#accion").val("L");
	$("#paginaIrA").val("");
	$("#f_paginaActual").val(paginaActual);
	$("#formLista").submit();
}

function irAOnClick(){
	var paginaIrA = $("#paginaIrA").val();
	
	if(paginaIrA == ""){
		alert("Digite el numero de pagina a la que desea ir");
		$("#paginaIrA").focus();
		return;
	}
	if(paginaIrA > $("#numeroPaginas").val()){
		alert("La pagina que solicitas no tiene registros que mostrar");
		return;
	}else if(paginaIrA < 1){
		paginaIrA = 1;
	}
	
	$("#accion").val("L");
	$("#f_paginaActual").val(paginaIrA);
	$("#formLista").submit();
}

</script>