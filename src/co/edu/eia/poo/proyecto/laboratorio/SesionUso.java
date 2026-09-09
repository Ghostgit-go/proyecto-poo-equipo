package co.edu.eia.poo.proyecto.laboratorio;

public class SesionUso {
	private Reserva reserva;
	private String consumibles;
	private String observaciones;
	private String incidentes;
	private int horaInicio;
	private int horaFin;
	private boolean cerrada = false;
	
	public SesionUso(Reserva reserva) {
		this.reserva = reserva;
	}
	// se abre una sesion a partir de una reserva y a la hora de inicio
	public void abrirSesion(Reserva reserva, int horaInicio) {
		this.reserva = reserva;
		this.horaInicio = horaInicio;
	}
	// cierra sesion y si ya estaba cerrada, lanza una excepcion de que ya fue cerrada, ya que no se puede cerrar 2 veces
	public void cerrarSesion(int horaFin, String consumibles, String observaciones, String incidentes) {
        if (cerrada) {
            throw new IllegalStateException("La sesión ya fue cerrada.");
        }
        
        this.horaFin = horaFin;
        this.consumibles = consumibles;
        this.observaciones = observaciones;
        this.incidentes = incidentes;
        this.cerrada = true;
	}
	
	public Reserva getReserva() {
		return reserva;
	}
	
	public String getConsumibles() {
		return consumibles;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public String getIncidentes() {
		return incidentes;
	}
	
	public int getHoraIncio() {
		return horaInicio;
	}
	
	public int getHoraFin() {
		return horaFin;
	}

}
