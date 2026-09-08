package co.edu.eia.poo.proyecto.laboratorio;

public class Formacion {
	private String fechaEmision;
	private String vigencia;
	private Usuario usuario;
	private Equipos equipos;
	
	public Formacion(String fechaEmision, String vigencia, Usuario usuario, Equipos equipos) {
		this.fechaEmision = fechaEmision;
		this.vigencia = vigencia;
		this.usuario = usuario;
		this.equipos = equipos;
	}
	
	public boolean estaVigente() {
		return true;
	}
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	
	public String getVigencia() {
		return vigencia;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Equipos getEquipos() {
		return equipos;
	}

}
