package co.edu.eia.poo.proyecto.laboratorio;

public class Estudiante extends Usuario{
	private String carrera;
	private int semestre;
	
	public Estudiante(String idUnico, String nombre, String correo, String carrera, int semestre) {
		super(idUnico, nombre, correo);
		this.carrera = carrera;
		this.semestre = semestre;
	}
	
	@Override
	public String getTipoUsuario() {
		return "Estudiante";	}
	
	// se inscribe ne una capacitacion
	public void inscribirCapacitacion(Capacitacion capacitacion) {
		System.out.println(getNombre()+ "Inscrito en "+ capacitacion.getTema());
	}
	
	public String getCarrera() {
		return carrera;
	}
	
	public int getSemestre() {
		return semestre;
	}
}
