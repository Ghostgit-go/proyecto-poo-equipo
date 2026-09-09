package co.edu.eia.poo.proyecto.laboratorio;

public class AuxiliarLaboratorio extends Usuario{
	private String turno;
	
	public AuxiliarLaboratorio(String nombre, String idUnico, String correo, String tipo, String turno) {
		super(nombre, idUnico, correo, tipo);
		this.turno = turno;
	}
	// el auxiliar registro una firmacion por ejemp, el registra la capacitacion de uso de cortadoraalaser, y tambien la certifica
	public void registrarCapacitacionesCertificaciones(Capacitacion capacitacion, Certificacion certificacion) {
		System.out.println("formacion registrada por " + getNombre());
	}
	// el auxiliar registro una reserva, le pone el nombre a la reserva
	public void registrarReservas(Reserva reserva) {
		System.out.println("reserva registrada por " + getNombre());
	}
	//ojo que aqui va la validacion de la reserva antes de confirmarla
	public void validarReserva(Reserva reserva) {
		
	}
	//valida que la cerfificacion est[e vigente
	public void validarCertificado(Certificacion certificacion) {
        certificacion.validarCertificacionVigente();
    }

	public String getTurno() {
		return turno;
	}

}
