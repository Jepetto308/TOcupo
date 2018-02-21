package Business;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entity.Cita;
import entity.Cliente;
import entity.Filtro;
import net.sf.jasperreports.engine.JRException;
import DAO.DaoCita;
import Utils.Conexion;
import Utils.GenerarReporte;
import Utils.Utilidades;;

public class ActionReportes {
	
	public Map listarReportes(HttpServletRequest request,Map parametros,String informe) throws NumberFormatException, ClassNotFoundException, SQLException, ParseException{
		if("CITA".equalsIgnoreCase(informe)) {
			ActionTipoCita oActionTipoCita = new ActionTipoCita();
			List listaTiposCitas = oActionTipoCita.listarTipoCitas();
			parametros.put("listaTiposCitas", listaTiposCitas);
			request.setAttribute("listaTiposCitas", listaTiposCitas);
			parametros.put("jsp", "informe_general_citas.jsp");
		}
		return parametros;
	}
	
	public void exportar(HttpServletRequest request, String sRutaDirectorioJasper, Map parametros, OutputStream out, boolean maps, String formato,String informe) throws NumberFormatException, ClassNotFoundException, SQLException, ParseException{
		ActionCita oActionCita = new ActionCita();
		if("CITAS".equalsIgnoreCase(informe)) {
			oActionCita.exportar(request, sRutaDirectorioJasper, parametros, out, maps, formato);
		}
	}
}
