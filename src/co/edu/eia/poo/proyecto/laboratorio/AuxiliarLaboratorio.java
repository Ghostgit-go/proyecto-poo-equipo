package co.edu.eia.poo.proyecto.laboratorio;

public class AuxiliarLaboratorio extends Usuario{
	private String turno;
	
	public AuxiliarLaboratorio(String nombre, String idUnico, String correo, String tipo, String turno) {
		super(nombre, idUnico, correo, tipo);
		this.turno = turno;
	}
	
	public void registrarCapacitacionesCertificaciones(Capacitacion capacitacion, Certificacion certificacion) {
		System.out.println("formacion registrada por " + getNombre());
	}
	
	public void registrarReservas(Reserva reserva) {
		System.out.println("reserva registrada por " + getNombre());
	}
	
	public void validarReserva(Reserva reserva) {
		
	}
	
	public void validarCertificado(Certificacion certificacion) {
        certificacion.validarCertificacionVigente();
    }

	public String getTurno() {
		return turno;
	}

}
