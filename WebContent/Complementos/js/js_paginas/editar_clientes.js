var contexto;

$(document).ready(function(){

	contexto = $("#contexto").val();
	$("#nombrePais").attr('readonly', true);
	$("#nombreMunicipio").attr('readonly', true);
	
	$("#btnGuardar").click(function(){
		$("#accion").val("G");
		$("#formCliente").submit();
	});
	
	$("#btnNuevo").click(function(){
		$("#accion").val("N");
		$("#formCliente").submit();
	});
	
	$("#btnEliminar").click(function (){
		
		var eliminar = confirm("Esta seguro de eliminar este Registro?");
		if(!eliminar){return;}
		
		$("#accion").val("E");
		$("#formCliente").submit();
	});

	$("#emergentePais").click(function (event){
		event.preventDefault();
		modalPais();
	});
	
	$("#codigoPais").change(function (){
		codigoPaisChange();
	});
	
	$("#emergenteMunicipio").click(function (event){
		event.preventDefault();
		modalMunicipio();
	});
	
	$("#codigoMunicipio").change(function (){
		codigoMunicipioChange();
	});
	
});

function volver(){
	window.history.back();
}

function codigoPaisChange(){
	
	$("#codigoPais").val($("#codigoPais").val().toUpperCase());
	if($("#codigoPais").val() == ""){
		$("#nombrePais").val("");
		return;
	}
	
	$.ajax({url: contexto + "/ControlPais?accion=C&codigoPais="+$("#codigoPais").val(),
	    method: "GET",
	    success: function (objPais) {
	        if (objPais == "N") {
	        	alert("No existe algun pais con codigo: "+$("#codigoPais").val());
//	        	$("#codigoPais").val("");
	        	$("#codigoPais").focus();
	        	$("#nombrePais").val("");
	        } else {
	        	objPais = JSON.parse(objPais);
	        	$("#nombrePais").val(objPais.nombrePais);
	        }
	}});
}

function modalPais() {
	var emer = window.open(contexto+"/ControlPais",'&quot','left=120,top=100,width=980,height=440');
}


function codigoMunicipioChange(){
	
	$("#codigoMunicipio").val($("#codigoMunicipio").val().toUpperCase());
	if($("#codigoMunicipio").val() == ""){
		$("#nombreMunicipio").val("");
		return;
	}
	
	$.ajax({url: contexto + "/ControlMunicipio?accion=C&codigoMunicipio="+$("#codigoMunicipio").val(),
	    method: "GET",
	    success: function (objMunicipio) {
	        if (objMunicipio == "N") {
	        	alert("No existe algun municipio con codigo: "+$("#codigoMunicipio").val());
//	        	$("#codigoMunicipio").val("");
	        	$("#codigoMunicipio").focus();
	        	$("#nombreMunicipio").val("");
	        } else {
	        	objMunicipio = JSON.parse(objMunicipio);
	        	$("#nombreMunicipio").val(objMunicipio.nombreMunicipio);
	        }
	}});
}

function modalMunicipio() {
	var emer = window.open(contexto+"/ControlMunicipio",'&quot','left=120,top=100,width=980,height=440');
}

/*modal pais de cliente*/
var paginamodal;
function modalCliente_pais() {
paginamodal = window.open (contexto+"/ControlPais",'&quot','left=120,top=100,width=980,height=440');

}

/*modal municipio de cliente*/
var paginamodal;
function modalCliente_municipio() {
paginamodal = window.open ('modal_municipio_cliente.jsp','&quot','left=120,top=100,width=980,height=440');
}




/*Bloque de input*/

  function activar_input(){
               var campo = $('#cPais').val();
               if((campo!==null)&&(campo!==''))
               {
                   $('#nPais').attr('disabled',false) ;
               }else{
                   $('#nPais').attr('disabled',true);
               }
           }  
           
               function activar_inputM(){
               var campo = $('#cMun').val();
               if((campo!==null)&&(campo!==''))
               {
                   $('#nMun').attr('disabled',false) ;
               }else{
                   $('#nMun').attr('disabled',true);
               }
           }  
