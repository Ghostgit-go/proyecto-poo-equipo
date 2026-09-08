package co.edu.eia.poo.proyecto.laboratorio;

public class Reserva {
	private Usuario usuario;
	private Equipos equipos;
	private String fecha;
	private int horaInicio;
	private int horaFinal;
	private String proposito;
	private String estado;
	
	public Reserva(Usuario usuario, Equipos equipos, String fecha, int horaInicio, int horaFinal, String proposito, String estado) {
		this.usuario = usuario;
		this.equipos = equipos;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.proposito = proposito;
		this.estado = estado;
	}
	
	public boolean disponibilidad(Laboratorios laboratorios, String franja) {
		return laboratorios.estaAbierto(horaInicio);
	}
	
	public void bloquearReserva(Equipos equipos) {
		this.estado = "cancelada";
	}
	
	public void consultarReserva(Usuario usuario) {
		System.out.println("reserva de " + usuario.getNombre() + estado);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Equipos getEquipos() {
		return equipos;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public int getHoraInicio() {
		return horaInicio;
	}
	
	public int getHoraFinal() {
		return horaFinal;
	}
	
	public String getProposito() {
		return proposito;
	}
	
	public String getEstado() {
		return estado;
	}
	
}
