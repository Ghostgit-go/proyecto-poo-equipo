package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalDate;

public class Certificacion implements Identificable{
	private String id;
	private LocalDate fechaEmision;
	private int vigenciaDias;
	private Usuario usuario;
	private String tipoEquipoAutorizado;
	
	public Certificacion(String id, LocalDate fechaEmision, int vigenciaDias, Usuario usuario, String tipoEquipoAutorizado) {
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.vigenciaDias = vigenciaDias;
		this.usuario = usuario;
		this.tipoEquipoAutorizado = tipoEquipoAutorizado;
	}
	//si la certificacion esta vigente en la fecha dada debe haberse emitido ya
    //y no haber pasado fechaEmision + vigenciaDias
	public boolean validarCertificacionVigente(LocalDate fecha) {
	        LocalDate vence = fechaEmision.plusDays(vigenciaDias);
	        return !fecha.isBefore(fechaEmision) && !fecha.isAfter(vence);
	    }
	// si la certificacion sigue vigente pero vence dentro de los proximos dias
    // sirve para el reporte de certificaciones priximas a vencer
    public boolean proximaVencer(LocalDate fecha, int dias) {
        LocalDate vence = fechaEmision.plusDays(vigenciaDias);
        return validarCertificacionVigente(fecha) && !vence.isAfter(fecha.plusDays(dias));
    }
    public boolean autorizaPara(Equipo e, LocalDate f) {
        return e.getTipoEquipo().equals(tipoEquipoAutorizado) && validarCertificacionVigente(f);
    }
	
	public String getTipoEquipoAutorizado() {
		return tipoEquipoAutorizado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	@Override
	public String getId() {
		return id;
	}
}
