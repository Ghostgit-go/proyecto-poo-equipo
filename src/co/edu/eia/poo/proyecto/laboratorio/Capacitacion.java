package co.edu.eia.poo.proyecto.laboratorio;

public class Capacitacion extends Formacion {
	private String temaCapacitacion;
	private int horasDuracion;
	
	public Capacitacion(String fechaEmision, String vigencia, Usuario usuario, Equipos equipos, String temaCapacitacion, int horasDuracion) {
		super(fechaEmision, vigencia, usuario, equipos);
		this.temaCapacitacion = temaCapacitacion;
		this.horasDuracion = horasDuracion;
	}
	// registra la asistencia del usuario que asistio a la capacitacion
	public void registrarAsistencia(Usuario usuario) {
		System.out.println(usuario.getNombre() + "asistio a "+ temaCapacitacion);
	}
	
	public String getTemaCapacitacion() {
		return temaCapacitacion;
	}
	
	public int getHorasDuracion() {
		return horasDuracion;
	}

}
