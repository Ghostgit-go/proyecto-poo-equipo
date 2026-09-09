package co.edu.eia.poo.proyecto.laboratorio;

public class Equipos {
	private String codigo;
	private String nombre;
	private int nivelRiesgo;
	private String estado;
	private Laboratorios laboratorio;

	public Equipos(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorios laboratorio ) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nivelRiesgo = nivelRiesgo;
		this.estado = estado;
		this.laboratorio = laboratorio;
	}
	
	public String getCodigo() {
		return codigo;}
	
	public String getNombre() {
		return nombre;}
	
	public int getNivelRiesgo() {
		return nivelRiesgo;}
	
	public String getEstado() {
		return estado;}
	
	public Laboratorios getLaboratorio() {
		return laboratorio;}
	
	// se invoca cuando un equipo este disponible
	public boolean estaDisponible() {
		return estado.equals("disponible");
	}
	// para evitar tantos metodos sobrecargados, aqui le ponemos el estado al quipo
	public void cambiarEstado(String estado) {
		this.estado = estado;
	}
	// este metodo se sobreescrible cuando haya que identificar cada tipo de equipo
	public String tipoEspecifico() {
		return "base"; // aqui se superpondra por cada tipo de equipo
	}
	
	public void mostrarEstado() {
		System.out.println("Codigo: "+ codigo + " Nombre: " + nombre+ " Nivel de riego: "+ nivelRiesgo + " Estado: "+ estado+ " tipo: "+tipoEspecifico());    
	}

}
