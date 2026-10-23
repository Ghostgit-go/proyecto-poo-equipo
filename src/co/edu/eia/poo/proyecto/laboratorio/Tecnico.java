package co.edu.eia.poo.proyecto.laboratorio;
import java.util.List;

public class Tecnico extends Usuario {
    private String especialidad;
    private List<TicketMantenimiento> tickets;

    public Tecnico(String nombre, String id, String correo, String especialidad) {
        super(id, nombre, correo);
        this.especialidad = especialidad;
    }
    
    @Override
    public String getTipoUsuario() {
    	return "Técnico - Encargado de arreglar los equipos";
    }
    
    //verifica que el equipo este disponible
    public void validarDisponibilidadEquipo(Equipos equipos) {
        if (!equipos.disponibilidad()) {
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