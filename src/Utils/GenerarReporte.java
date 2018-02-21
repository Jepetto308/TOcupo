package Utils;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Jefferson Palacios Torres
 */
public class GenerarReporte {

    public static final String FORMATO_PDF = "pdf";
    public static final String FORMATO_XLS = "xls";
    public static final String FORMATO_RTF = "rtf";
    public static final String FORMATO_TXT = "txt";
    public static final String FORMATO_TXT2 = "txt2";
    public static final String FORMATO_XLSX = "xlsx";
    public static final String FORMATO_CSV = "csv";

    public static void exportarDesdeFormatos(Map configuracion, String nombreJasper, List listaBeans, Map parametros, OutputStream out, String formato) throws JRException {
        String rutaPlantillas = (String) configuracion.get("sia.rutaplantillas") + "/formatos/" + nombreJasper;
//        rutaPlantillas = "C:\\Users\\Usuario\\Desktop\\formatos\\copias\\schwyn\\formatos/" + nombreJasper;
        File archivo = new File(rutaPlantillas);
        if (!archivo.exists()) {
            rutaPlantillas = (String) configuracion.get("rutaReportes") + "/formatos/" + nombreJasper;
        }
            exportar(rutaPlantillas, listaBeans, parametros, out, true, formato);
    }

    public static void exportar(String sRutaDirectorioJasper, List listaBeans, Map parametros, OutputStream out, boolean maps, String formato) throws JRException {

//        Conexion oConexion = new Conexion();
//        conn = oConexion.getConexion();
//        sRutaDirectorioJasper = "C:\\Users\\ASUS\\Desktop\\Reportes\\reporteProductos.jasper";
//        parametros = new HashMap();
//        maps = false;
//        listaBeans = new ArrayList();
//        formato = "pdf";
//        OutputStream out = null;
        /*
* Para que esto funcione debe agregar al buidpath Jaxen.jar
* http://stackoverflow.com/questions/15875199/jasperreports-fillreport-too-slow-and-resource-consuming
         */
        DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
        JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");
        /*
* http://community.jaspersoft.com/questions/529773/export-huge-pdf
         */
        JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, System.getProperty("java.io.tmpdir"));
        parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
//crear JRDataSource
        JRDataSource jds = null;
        if (maps) {
            jds = new JRMapCollectionDataSource(listaBeans);
        } else {
            jds = new JRBeanCollectionDataSource(listaBeans);
        }
// llenar reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(sRutaDirectorioJasper, parametros, jds);

        JRAbstractExporter reporte = null;
        if (FORMATO_XLS.equalsIgnoreCase(formato)) {
            reporte = new JRXlsExporter();
            reporte.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, new Boolean(false));
            reporte.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, new Boolean(true));
            reporte.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, new Boolean(true));
        } else if (FORMATO_RTF.equalsIgnoreCase(formato)) {
            reporte = new JRRtfExporter();
        } else if (FORMATO_TXT.equalsIgnoreCase(formato)) {
            reporte = new JRTextExporter();
            reporte.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Float("130"));
            reporte.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float("59"));
            reporte.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float("10"));
        } else if (FORMATO_XLSX.equalsIgnoreCase(formato)) {
            reporte = new JRXlsxExporter();
            reporte.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, new Boolean(false));
            reporte.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, new Boolean(true));
            reporte.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, new Boolean(true));
        } else if (FORMATO_CSV.equalsIgnoreCase(formato)) {
//  Exportar documentos en CSV : SimpleCsvReportConfiguration
            reporte = new JRCsvExporter();
            reporte.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ",");
            reporte.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");
            reporte.setParameter(JRCsvExporterParameter.CHARACTER_ENCODING, "latin1");
        } // Requerimiento 3401 Julio 2017: es necesario añadir la exportacion de formatos en .Docx para los formatos de poderes
        else if ("DOCX".equalsIgnoreCase(formato)) {
//  Exportar documentos en DOCX : SimpleDocxReportConfiguration
            reporte = new JRDocxExporter();
            reporte.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING, "utf-8");
            reporte.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, new Boolean(true));
            reporte.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES, new Boolean(true));
        } else {
            reporte = new JRPdfExporter();
        }
        reporte.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        reporte.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        reporte.exportReport();
    }

//    public static void main(String[] args) {
//        try {
//            GenerarReporte.exportar(FORMATO_PDF, null, null, FORMATO_PDF, true,"");
//        } catch (JRException ex) {
//            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
