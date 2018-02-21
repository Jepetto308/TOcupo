package entity;

import Utils.Utilidades;
import javassist.bytecode.analysis.Util;

public class Filtro {
	private String campo;
	private String operador;
	private String evaluador = "AND";
	private Object valor;
	private Object valor2;
	
	public void Filtro(String campo, String operador,String evaluador, Object valor, Object valor2){
		this.campo = campo;
		this.operador = operador;
		if(!new Utilidades().esVacio(evaluador)) {
			this.evaluador = evaluador;
		}else {
			this.evaluador = "AND";
		}
		this.valor = valor;
		this.valor2 = valor2;
	}
	
	public void Filtro(){
		this.campo = "";
		this.operador = "";
		this.evaluador = "AND";
		this.valor = "";
		this.valor2 = "";
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	public String getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(String evaluador) {
		this.evaluador = evaluador;
	}

	public Object getValor2() {
		return valor2;
	}

	public void setValor2(Object valor2) {
		this.valor2 = valor2;
	}
	
	
}
