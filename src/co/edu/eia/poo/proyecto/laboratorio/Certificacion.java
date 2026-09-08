package co.edu.eia.poo.proyecto.laboratorio;

public class Certificacion extends Formacion{
	private String tipoEquipoAutorizado;
	
	public Certificacion(String fechaEmision, String vigencia, Usuario usuario, Equipos equipos, String tipoEquipoAutorizado) {
		super(fechaEmision, vigencia, usuario, equipos);
		this.tipoEquipoAutorizado = tipoEquipoAutorizado;
	}
	
	public void validarCertificacionVigente() {
		 if (!estaVigente()) {
	            throw new IllegalStateException("La certificación está vencida.");
	        }
	}
	
	public String getTipoEquipoAutorizado() {
		return tipoEquipoAutorizado;
	}
}
