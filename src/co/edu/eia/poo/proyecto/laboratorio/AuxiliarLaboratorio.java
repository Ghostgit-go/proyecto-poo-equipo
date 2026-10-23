package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalDate;

public class AuxiliarLaboratorio extends Usuario{
	private String turno;
	
	public AuxiliarLaboratorio(String nombre, String id, String correo, String turno) {
		super(nombre, id, correo);
		this.turno = turno;
	}
	
	@Override
	public String getTipoUsuario() {
		return "Auxiliar Laboratorio - Se encarga de registrar capacitaciones y certificaciones al sistema, y abre sesiones para prestar laboratorios";
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
	public boolean validarCertificado(Certificacion certificacion, LocalDate fecha) {
	    return certificacion.validarCertificacionVigente(fecha);
	}

	public String getTurno() {
		return turno;
	}

}
