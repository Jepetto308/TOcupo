package entity;

public class TipoCita {
	
	private int idTipoCita;
	private String codigoTipoCita;
	private String nombreTipoCita;
	
	public TipoCita() {
		this.idTipoCita = 0;
		this.codigoTipoCita = "";
		this.nombreTipoCita = "";
	}
	
	public TipoCita(int idTipoCita, String codigoTipoCita, String nombreTipoCita) {
		this.idTipoCita = idTipoCita;
		this.codigoTipoCita = codigoTipoCita;
		this.nombreTipoCita = nombreTipoCita;
	}
	
	public int getIdTipoCita() {
		return idTipoCita;
	}
	public void setIdTipoCita(int idTipoCita) {
		this.idTipoCita = idTipoCita;
	}
	public String getCodigoTipoCita() {
		return codigoTipoCita;
	}
	public void setCodigoTipoCita(String codigoTipoCita) {
		this.codigoTipoCita = codigoTipoCita;
	}
	public String getNombreTipoCita() {
		return nombreTipoCita;
	}
	public void setNombreTipoCita(String nombreTipoCita) {
		this.nombreTipoCita = nombreTipoCita;
	}
	
	

}
