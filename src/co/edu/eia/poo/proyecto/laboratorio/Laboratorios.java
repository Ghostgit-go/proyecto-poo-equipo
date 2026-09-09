package co.edu.eia.poo.proyecto.laboratorio;

public class Laboratorios {
	private String codigo;
	private String nombre;
	private String ubicacion;
	private String horario;
	private boolean estado;
	
	public Laboratorios(String codigo, String nombre, String ubicacion, String horario, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
		this.ubicacion = ubicacion;
		this.horario = horario;
	}
	// indica si el laboratorio est[a abierto, la hora aun no la usamos pero es para cuando haya que cruzar horarios
	public boolean estaAbierto(int hora) {
		return estado;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public boolean getEstado() {
		return estado;
	}
}
