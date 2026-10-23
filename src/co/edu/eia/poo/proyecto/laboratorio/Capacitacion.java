package co.edu.eia.poo.proyecto.laboratorio;
import java.util.List;

public class Capacitacion  {
	private String temaCapacitacion;
	private int horasDuracion;
	private String id;
	private String tipoEquipos;
	private List<Usuario> asistentes;
	
	
	public Capacitacion(String temaCapacitacion, int horasDuracion, String id, String tipoEquipos) {
		this.horasDuracion=horasDuracion;
		this.id=id;
		this.temaCapacitacion = temaCapacitacion;
		this.tipoEquipos=tipoEquipos;
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
