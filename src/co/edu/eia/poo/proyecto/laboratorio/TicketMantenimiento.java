package co.edu.eia.poo.proyecto.laboratorio;

public class TicketMantenimiento {
	private int prioridad;
	private String descripcion;
	private Equipos equipos;
	private Tecnico tecnico;
	private String estado;
	private String historialCambios = "";
	
	public TicketMantenimiento(int prioridad, String descripcion, Equipos equipos, Tecnico tecnico, String estado, String historialCambios) {
		this.descripcion = descripcion;
		this.equipos = equipos;
		this.tecnico = tecnico;
		this.prioridad = prioridad;
		this.estado = estado;
		this.historialCambios = historialCambios;
	}
	
	public void actualizarHistorial(String cambio) {
		this.historialCambios += "\n" + cambio;
	}
	
	public int getPrioridad() {
		return prioridad;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Equipos getEquipos() {
		return equipos;
	}
	
	public Tecnico getTecnico() {
		return tecnico;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getHistorialCambios() {
		return historialCambios;
	}

}
