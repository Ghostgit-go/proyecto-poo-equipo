package co.edu.eia.poo.proyecto.laboratorio;

public class Tecnico extends Usuario {
    private String especialidad;

    public Tecnico(String idUnico, String nombre, String correo, String tipo, String especialidad) {
        super(idUnico, nombre, correo, tipo);
        this.especialidad = especialidad;
    }

    public void validarDisponibilidadEquipo(Equipos equipos) {
        if (!equipos.estaDisponible()) {
            throw new IllegalStateException("El equipo " + equipos.getCodigo() + " no está disponible");
        }
    }

    public void cerrarTicket(TicketMantenimiento ticket, Equipos equipos) {
        // solo se puede cerrar si ya se registró una acción y el equipo quedó en estado válido
        ticket.actualizarHistorial("Ticket cerrado por " + getNombre());
    }

    public String getEspecialidad() { 
    	return especialidad; }
}