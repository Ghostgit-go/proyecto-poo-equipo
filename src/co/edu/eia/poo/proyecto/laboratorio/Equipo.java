package co.edu.eia.poo.proyecto.laboratorio;

public abstract class Equipo implements Identificable{
	private String codigo;
	private String nombre;
	private int nivelRiesgo;
	private String estado;
	private Laboratorio laboratorio;

	public Equipo(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorio laboratorio ) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nivelRiesgo = nivelRiesgo;
		this.estado = estado;
		this.laboratorio = laboratorio;
	}
	
	public abstract String getTipoEquipo();
	
	@Override
	public String getId() {
		return codigo;}
	
	public String getNombre() {
		return nombre;}
	
	public int getNivelRiesgo() {
		return nivelRiesgo;}
	
	public String getEstado() {
		return estado;}
	
	public Laboratorio getLaboratorio() {
		return laboratorio;}
	
	// se invoca cuando un equipo este disponible
	public boolean disponibilidad() {
		return estado.equals("disponible");
	}
	// para evitar tantos metodos sobrecargados, aqui le ponemos el estado al quipo
	public void cambiarEstado(String estado) {
		this.estado = estado;
	}

}
