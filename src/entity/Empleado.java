/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author WILMAR
 */
public class Empleado {
   private int idEmpleado;
   private String tipoDocumentoEmpleado;
   private String numeroDocumentoEmpleado;
   private String primerNombreEmpleado;
   private String otrosNombresEmpleado;
   private String primerApellidoEmpleado;
   private String segundoApellidoEmpleado;
   
   private String direccionEmpleado;
   private String telefonoEmpleado;
   private String celularEmpleado;
   private String faxEmpleado;
   private String correoEmpleado;
   private String codigoPais;
   private String codigoMunicipio;
   private String usuarioCreacion;
   private java.sql.Timestamp fechaCreacion;
   private String nombrePais;
   private String nombreMunicipio;
   
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getTipoDocumentoEmpleado() {
        return tipoDocumentoEmpleado;
    }

    public void setTipoDocumentoEmpleado(String tipoDocumentoEmpleado) {
        this.tipoDocumentoEmpleado = tipoDocumentoEmpleado;
    }

    public String getNumeroDocumentoEmpleado() {
        return numeroDocumentoEmpleado;
    }

    public void setNumeroDocumentoEmpleado(String numeroDocumentoEmpleado) {
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
    }

    public String getPrimerNombreEmpleado() {
        return primerNombreEmpleado;
    }

    public void setPrimerNombreEmpleado(String primerNombreEmpleado) {
        this.primerNombreEmpleado = primerNombreEmpleado;
    }

    public String getOtrosNombresEmpleado() {
        return otrosNombresEmpleado;
    }

    public void setOtrosNombresEmpleado(String otrosNombresEmpleado) {
        this.otrosNombresEmpleado = otrosNombresEmpleado;
    }

    public String getPrimerApellidoEmpleado() {
        return primerApellidoEmpleado;
    }

    public void setPrimerApellidoEmpleado(String primerApellidoEmpleado) {
        this.primerApellidoEmpleado = primerApellidoEmpleado;
    }

    public String getSegundoApellidoEmpleado() {
        return segundoApellidoEmpleado;
    }

    public void setSegundoApellidoEmpleado(String segundoApellidoEmpleado) {
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getCelularEmpleado() {
        return celularEmpleado;
    }

    public void setCelularEmpleado(String celularEmpleado) {
        this.celularEmpleado = celularEmpleado;
    }

    public String getFaxEmpleado() {
        return faxEmpleado;
    }

    public void setFaxEmpleado(String faxEmpleado) {
        this.faxEmpleado = faxEmpleado;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public java.sql.Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

   

}