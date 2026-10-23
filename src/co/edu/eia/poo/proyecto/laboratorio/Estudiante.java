package co.edu.eia.poo.proyecto.laboratorio;

public class Estudiante extends Usuario{
	private String carrera;
	private String semestre;
	
	public Estudiante(String nombre, String id, String correo, String carrera, String semestre) {
		super(nombre, id, correo);
		this.carrera = carrera;
		this.semestre = semestre;
	}
	
	@Override
	public String getTipoUsuario() {
		return "Estudiante - Reserva laboratorios";	}
	
	// se inscribe ne una capacitacion
	public void inscribirCapacitacion(Capacitacion capacitacion) {
		System.out.println(getNombre()+ "Inscrito en "+ capacitacion.getTemaCapacitacion());
	}
	
	public String getCarrera() {
		return carrera;
	}
	
	public String getSemestre() {
		return semestre;
	}
}
