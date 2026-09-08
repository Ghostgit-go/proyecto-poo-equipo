package co.edu.eia.poo.proyecto.laboratorio;

public class Estudiante extends Usuario{
	private String carrera;
	private String semestre;
	
	public Estudiante(String nombre, String idUnico, String correo, String tipo, String carrera, String semestre) {
		super(nombre, idUnico, correo, tipo);
		this.carrera = carrera;
		this.semestre = semestre;
	}
	
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
