package co.edu.eia.poo.proyecto.laboratorio;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Usuario {
    private String especialidad;
    private List<TicketMantenimiento> tickets;

    public Tecnico(String idUnico, String nombre, String correo, String especialidad,
                   List<TicketMantenimiento> tickets) {
        super(idUnico, nombre, correo);
        this.especialidad = especialidad;
        if (tickets == null) {
            this.tickets = new ArrayList<>();
        } else {
            this.tickets = tickets;
        }
    }

    // Asigna un ticket al técnic Si ya tiene un ticket sin cerrar, esta ocupado y
    // se lanza TecnicoOcupadoException regla un técnico no puede tener dos
    // mantenimientos a la vez
    public void asignarTicket(TicketMantenimiento t) throws TecnicoOcupadoException {
        for (TicketMantenimiento existente : tickets) {
            if (!existente.getEstado().equals("cerrado")) {
                throw new TecnicoOcupadoException("El técnico " + getNombre()
                        + " ya tiene en curso el ticket " + existente.getId());
            }
        }
        tickets.add(t);
    }

    // Verifica que el equipo esté disponible; si no lo está, lanza una excepción
    // (se usa antes de intervenir o de dejar usar un equipo
    public void validarDisponibilidadEquipo(Equipo equipo) {
        if (!equipo.disponibilidad()) {
            throw new IllegalStateException("El equipo " + equipo.getId() + " no está disponible");
        }
    }

    // Cierra un ticket registra la acción realizada y deja el equipo en estado "disponible"
    // Si el ticket no se puede cerrar ya cerrado, estado final inválido, avisa por consola
    public void cerrarTicket(TicketMantenimiento ticket, Equipo equipo) {
        try {
            ticket.cerrar("Mantenimiento realizado por " + getNombre(), "disponible");
            equipo.cambiarEstado("disponible");
        } catch (TicketInvalidoException e) {
            System.out.println("No se pudo cerrar el ticket: " + e.getMessage());
        }
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String getTipoUsuario() {
        return "Técnico";
    }
}