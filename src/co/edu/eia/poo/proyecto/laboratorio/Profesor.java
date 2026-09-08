package co.edu.eia.poo.proyecto.laboratorio;

public class Profesor extends Usuario{
	private String materia;
	
	public Profesor (String nombre, String idUnico, String correo, String tipo, String materia) {
		super(nombre, idUnico, correo, tipo);
		this.materia = materia;
	}
	
	public void autorizarUsoEquipo(Equipos equipos, Estudiante estudiante) {
		System.out.println(getNombre()+ "aurotiza a "+ estudiante.getNombre() + " a usar el/la <" + equipos.getNombre());
	}
	
	public String getMateria() {
		return materia;
	}

}
