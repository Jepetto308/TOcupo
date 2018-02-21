/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import Business.ActionRoles;
import Business.ActionUsuario;
import Utils.Encriptador;
import Utils.Utilidades;
import entity.Empleado;
import entity.PermisosUsuarios;
import entity.Usuario;

/**
 *
 * @author WILMAR
 */
public class ControlUsuario extends HttpServlet {
	
	ActionUsuario oActionUsuario = new ActionUsuario();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
    	
    	response.setContentType("text/html;charset=UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//        response.setDateHeader("Expires", 0); // Proxies.
        
        String accion = request.getParameter("accion");
        accion = "".equals(accion) || accion == null? "L":accion;
        
        switch (accion) {
        	case "L":
        		listarUsuarios(request, response);
        		break;
        	case "G":
    			guardarUsuario(request, response);
    			break;
    		case "D":
    			editarUsuario(request, response);
    			break;
    		case "E":
    			eliminarUsuario(request, response);
    			break;
        	case "N":
    			nuevoUsuario(request, response);
    			break;
        	case "V":
    			validarUsername(request, response);
    			break;
        	case "EX":
    			exportar(request, response);
    			break;
        	case "P":
    			permisosUsuario(request, response);
    			break;
        	case "PP":
    			permisosUsuarioDiscriminado(request, response);
    			break;
        	default:
        		listarUsuarios(request, response);
        		break;
        }
        
    }
    
    
    public void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
        List listaUsuarios = oActionUsuario.listarUsuarios(request);
        
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.getRequestDispatcher("/JSP/buscar_usuarios.jsp").forward(request,response);
    }
    
    public void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
    	int idUsuario = Integer.parseInt(request.getParameter("hidUsuario").trim());
    	Map parametros = new HashMap();
    	
        Map proceso = oActionUsuario.consultarUsuarioVista(idUsuario, parametros,request);
        request.setAttribute("oUsuario", proceso.get("oUsuario"));
        request.setAttribute("listaRoles", proceso.get("listaRoles"));
        
        request.getRequestDispatcher("/JSP/editar_usuarios.jsp").forward(request,response);
    }
    
    public void guardarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
       	Usuario oUsuario = new Usuario();
        llenarUsuario(request, oUsuario);            
        
        Map proceso = oActionUsuario.insertarActualizarUsuario(oUsuario,request);
        
        request.setAttribute("oUsuario", proceso.get("oUsuario"));
        request.setAttribute("listaRoles", proceso.get("listaRoles"));
        request.setAttribute("okMessage", proceso.get("okMessage"));
        request.getRequestDispatcher("/JSP/editar_usuarios.jsp").forward(request,response);
    }
    
    public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
    	int idUsuario = Integer.parseInt(request.getParameter("hidUsuario"));
    	Map parametros = new HashMap();
    	Map proceso = oActionUsuario.eliminarUsuario(idUsuario, parametros);
    	
    	request.setAttribute("okMessage", proceso.get("okMensaje"));
    	nuevoUsuario(request, response);
    }
    
    public void nuevoUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
    	Map parametros = new HashMap();
    	Usuario oUsuario = new Usuario();
        request.setAttribute("oUsuario", oUsuario);
        
        ActionRoles oActionRol = new ActionRoles();
        request.setAttribute("listaRoles", oActionRol.listarRoles(request));
        
        request.getRequestDispatcher("/JSP/editar_usuarios.jsp").forward(request,response);
    }
    
    public void llenarUsuario(HttpServletRequest request, Usuario oUsuario) {
    	String strm = "";
    	strm = request.getParameter("hidUsuario");
    	oUsuario.setIdUsuario(strm);
    	strm = request.getParameter("numeroIdentificacion");
    	oUsuario.setNumeroIdentificacion(strm);
    	strm = request.getParameter("nombre");
    	oUsuario.setNombre(strm);
    	strm = request.getParameter("apellidos");
    	oUsuario.setApellidos(strm);
    	strm = request.getParameter("sexo");
    	oUsuario.setSexo(strm);
    	strm = request.getParameter("username");
    	oUsuario.setUsername(strm);
    	strm = request.getParameter("password");
    	Encriptador oEncriptador = new Encriptador();
    	oUsuario.setPassword(oEncriptador.Encriptar(strm));
    	strm = request.getParameter("rol");
    	oUsuario.setCodigoRol(strm);
    	strm = request.getParameter("telefono");
    	oUsuario.setTelefono(strm);
    	strm = request.getParameter("estadoUsuario");
    	oUsuario.setEstado(strm); 
    }
    
    public void validarUsername(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
    	String username = request.getParameter("username");
    	response.getWriter().print(oActionUsuario.existeUsuarioByUsername(username));
    }
    
    public void exportar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    	String formato = request.getParameter("formato");
    	if("".equals(formato) || null == formato){
    		formato = "pdf";
    	} 
    	
    	if("pdf".equalsIgnoreCase(formato)){
    		response.setContentType("application/pdf");
    	}else{ 
    		response.setContentType("application/vnd.ms-excel");
    	}
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + "Usuarios" + "."+formato+"\";");
    	OutputStream out = response.getOutputStream();
    	String rutaJasper = getServletContext().getRealPath("/Jasper");
    	Map parametros = new HashMap();
    	parametros.put("request", request);
    	
    	oActionUsuario.exportar(request,rutaJasper, parametros, out, false, formato);
    }
    
    public void permisosUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
    	String subAccion = request.getParameter("subAccion");
    	List listaPermi = new ArrayList();
        String user = request.getParameter("username");
        String modulo = request.getParameter("modulo");
        
        if(!new Utilidades().esVacio(request.getParameter("listaPermisosUsuarioModel"))) {
	        JSONArray jsonArray= new JSONArray(request.getParameter("listaPermisosUsuarioModel"));
	        if(jsonArray.length()>0) {
		        for (int i = 0; i < jsonArray.length(); i++) {
		        	PermisosUsuarios oPermiso = new PermisosUsuarios();
		        	oPermiso.setActivo(jsonArray.getJSONObject(i).getString("Activo"));
		        	oPermiso.setCodigoOpcion(jsonArray.getJSONObject(i).getString("Codigo"));
		        	oPermiso.setCodigoRecurso(jsonArray.getJSONObject(i).getString("Permiso"));
		        	oPermiso.setLogin(user);
		        	listaPermi.add(oPermiso);
				}
	        }
        }
        
        if("GuardarP".equalsIgnoreCase(subAccion) && listaPermi.size() > 0) {
        	oActionUsuario.insertarActualizarPermisosUsuario(listaPermi,modulo, user, request);
        }
        
        List listaPermisosUsuario = oActionUsuario.listaPermisosUsuario(request,modulo,user);
        List listaPermisos = oActionUsuario.listaPermisos(request);
        request.setAttribute("listaPermisosUsuario", listaPermisosUsuario);
        request.setAttribute("listaPermisos", listaPermisos);
        request.setAttribute("username", user);
        
        request.getRequestDispatcher("/JSP/modal_modulos.jsp").forward(request,response);
    }
    
    public void permisosUsuarioDiscriminado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
//        List listaPermisosUsuario = oActionUsuario.listaPermisosUsuario(request);
//        JSONArray listaPermisosUser = new JSONArray(listaPermisosUsuario);
//    	response.getWriter().print(listaPermisosUser);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
