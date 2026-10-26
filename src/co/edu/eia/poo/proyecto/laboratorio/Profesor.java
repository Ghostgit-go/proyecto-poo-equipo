package co.edu.eia.poo.proyecto.laboratorio;

public class Profesor extends Usuario{
	private String materia;
	
	public Profesor (String idUnico, String nombre, String correo, String materia) {
		super(idUnico, nombre, correo);
		this.materia = materia;
	}
	// autoriza al estudiante a usar el equipo especifico
	public void autorizarUsoEquipo(Equipo equipos, Estudiante estudiante) {
		System.out.println(getNombre()+ "aurotiza a "+ estudiante.getNombre() + " a usar el/la <" + equipos.getNombre());
	}
	
	@Override
	public String getTipoUsuario() {
		return "Profesor";
		}
	
	public String getMateria() {
		return materia;
	}

}
