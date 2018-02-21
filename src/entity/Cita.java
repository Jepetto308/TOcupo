/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author USER
 */
public class Cita {
     private int idCita;
    private String numeroCita;
    private java.sql.Timestamp fechaCita;
    private int idServicioCita;
    private String estadoCita;
    private int idCliente;
    private String usuarioCreacion;
    private java.sql.Timestamp fechaCreacion;
    private String nombreCompletoCliente;
    private int numeroDocumentoCliente;
    private String nombreTipoCita;

	public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getNumeroCita() {
        return numeroCita;
    }

    public void setNumeroCita(String numeroCita) {
        this.numeroCita = numeroCita;
    }

    public java.sql.Timestamp getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(java.sql.Timestamp fechaCita) {
        this.fechaCita = fechaCita;
    }

    public int getIdServicioCita() {
        return idServicioCita;
    }

    public void setIdServicioCita(int idServicioCita) {
        this.idServicioCita = idServicioCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}

	public int getNumeroDocumentoCliente() {
		return numeroDocumentoCliente;
	}

	public void setNumeroDocumentoCliente(int numeroDocumentoCliente) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}

	public String getNombreTipoCita() {
		return nombreTipoCita;
	}

	public void setNombreTipoCita(String nombreTipoCita) {
		this.nombreTipoCita = nombreTipoCita;
	}

}
