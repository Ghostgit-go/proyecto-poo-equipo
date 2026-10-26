package co.edu.eia.poo.proyecto.laboratorio;

import java.util.ArrayList;

//crea, asigna y cierra tickets de mantenimiento, y cambia el estado del equipo en cada paso
public class GestorMantenimiento {
    private Repositorio<TicketMantenimiento> tickets = new Repositorio<>();
    private GestorReserva gestorReserva;

    public GestorMantenimiento(GestorReserva gestorReserva) {
        this.gestorReserva = gestorReserva;
    }

    // crea un ticket abierto para el equipo, lo deja en mantenimiento y cancela/notifica
    // sus reservas futuras y devuelve false si el id del ticket ya existe
    public boolean crearTicket(String id, int prioridad, String descripcion, Equipo e) {
        try {
            TicketMantenimiento t = new TicketMantenimiento(id, prioridad, descripcion, e, null,
                    "abierto", new ArrayList<String>());
            t.actualizarHistorial("Ticket creado: " + descripcion);
            tickets.agregar(t);
            e.cambiarEstado("mantenimiento");
            gestorReserva.bloquearReservasDe(e);
            return true;
        } catch (IdDuplicadoException ex) {
            System.out.println("Ticket rechazado: " + ex.getMessage());
            return false;
        }
    }

    //asigna el ticket a un tecnico y devuelve false si el tecnico esta ocupado
    public boolean asignarTecnico(TicketMantenimiento t, Tecnico tec) {
        try {
            tec.asignarTicket(t);
            t.asignarTecnico(tec);
            return true;
        } catch (TecnicoOcupadoException ex) {
            System.out.println("No se pudo asignar: " + ex.getMessage());
            return false;
        }
    }

    //cierra el ticket con la accion realizada y el estado final del equipo disponible o
    // fuera_de_servicio, devuelve false si el ticket no se puede cerrar
    public boolean cerrarTicket(TicketMantenimiento t, String accion, String estadoFinal) {
        try {
            t.cerrar(accion, estadoFinal);
            if (estadoFinal.equals("disponible")) {
                ponerOperativo(t.getEquipo());
            } else {
                ponerFueraDeServicio(t.getEquipo());
            }
            return true;
        } catch (TicketInvalidoException ex) {
            System.out.println("no se pudo cerrar el ticket " + ex.getMessage());
            return false;
        }
    }

    //deja el equipo fuera_de_servicio y cancela/notifica sus reservas futuras
    public void ponerFueraDeServicio(Equipo e) {
        e.cambiarEstado("fuera_de_servicio");
        gestorReserva.bloquearReservasDe(e);
    }

    //deja el equipo disponible y le avisa al siguiente de la lista de espera
    public void ponerOperativo(Equipo e) {
        e.cambiarEstado("disponible");
        gestorReserva.liberarSiguiente(e);
    }

    //busca un ticket por id null si no existe, se agreg[o para poder recuperar el ticket creado
    public TicketMantenimiento buscarTicket(String id) {
        return tickets.buscar(id);
    }
}