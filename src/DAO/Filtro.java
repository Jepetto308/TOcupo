package DAO;

public class Filtro {
	private String campo;
	private String operador;
	private Object valor;
	
	public void Filtro(String campo, String operador, Object valor){
		this.campo = campo;
		this.operador = operador;
		this.valor = valor;
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
	
	
}
