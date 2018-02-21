/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Image;

/**
 *
 * @author Jefferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class Usuario {

    private String numeroIdentificacion;
    private String nombre;
    private String apellidos;
    private String username;
    private String password;
    private String sexo;
    private String codigoRol;
    private String telefono;
    private Image fotoPerfil;  
    private String rutaImg;
    private String nombreRol;
    private String estado;
    private String idUsuario;
    private String nombreCompleto;
    
    public Usuario(String numeroIdentificacion,String nombre,String apellidos,String username,String sexo,String codigoRol,String telefono,Image fotoPerfil,String rutaImg,String password,String estado, String idUsuario){
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.sexo = sexo;
        this.codigoRol = codigoRol;
        this.telefono = telefono;
        this.fotoPerfil = fotoPerfil;
        this.rutaImg = rutaImg;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }
    
    public Usuario(){
        this.numeroIdentificacion = "";
        this.nombre = "";
        this.apellidos = "";
        this.username = "";
        this.password = "";
        this.sexo = "";
        this.codigoRol = "";
        this.telefono = "";
        this.fotoPerfil = null;
        this.rutaImg = "";
        this.estado = "";
        this.idUsuario = "";
    }
    
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Image getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Image fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    
    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}
