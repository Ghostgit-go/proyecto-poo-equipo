package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalDate;

public class Certificacion{
	private String tipoEquipoAutorizado;
	private String id;
	private LocalDate fechaEmision;
	private int vigenciaDias;
	private Usuario usuario;
	
	public Certificacion(String id, LocalDate fechaEmision, int vigenciaDias, Usuario usuario, String tipoEquipoAutorizado) {
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.vigenciaDias = vigenciaDias;
		this.usuario = usuario;
		this.tipoEquipoAutorizado = tipoEquipoAutorizado;
	}
	
	//toma la fecha en que se emitió y súmale los días de vigencia
	public LocalDate getFechaVencimiento() {
	    return fechaEmision.plusDays(vigenciaDias);
	}
	
	// verifica que la certificacion siga vigente, sino, lanza la excepcion
	public boolean validarCertificacionVigente(LocalDate fecha) {
		    return !fecha.isBefore(fechaEmision) && !fecha.isAfter(getFechaVencimiento());
	}
	
	public String getTipoEquipoAutorizado() {
		return tipoEquipoAutorizado;
	}
	
	public String getId() {
		return id;
	}
}
