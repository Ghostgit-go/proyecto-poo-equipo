package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva implements Identificable, Notificable {
    private String id;
    private Usuario usuario;
    private Equipo equipo;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private String proposito;
    private String estado;

    public Reserva(String id, Usuario usuario, Equipo equipo, LocalDate fecha, LocalTime horaInicio,
                   LocalTime horaFinal, String proposito, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.equipo = equipo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.proposito = proposito;
        this.estado = estado;
    }

    // dice si la franja de la reserva horaInicio horaFinal cabe dentro del horario del
    // laboratorio el laboratorio debe estar abierto al inicio y cerrar después del final
    // La "franja" ya está representada por horaInicio y horaFinal, por eso no se recibe aparte
    public boolean disponibilidad(Laboratorio laboratorio) {
        return laboratorio.estaAbierto(horaInicio) && !horaFinal.isAfter(laboratorio.getHoraCierre());
    }

    // cancela la reserva si esta confirmada y es del equipo indicado, y avisa al usuario
    // se usa, por ejemplo cuando el equipo queda fuera de servicio
    public void bloquearReserva(Equipo equipo) {
        if (this.equipo.getId().equals(equipo.getId()) && estado.equals("confirmada")) {
            estado = "cancelada";
            notificar("Tu reserva " + id + " del equipo " + equipo.getNombre()
                    + " fue cancelada porque el equipo no está operativo.");
        }
    }

    //imprime los datos de la reserva si pertenece al usuario indicado
    public void consultarReserva(Usuario usuario) {
        if (this.usuario.getId().equals(usuario.getId())) {
            System.out.println("Reserva " + id + " de " + usuario.getNombre() + ": " + equipo.getNombre()
                    + " el " + fecha + " de " + horaInicio + " a " + horaFinal + " (" + estado + ")");
        } else {
            System.out.println("La reserva " + id + " no pertenece a " + usuario.getNombre());
        }
    }

    // reenvia el aviso al usuario dueño de la reserva
    @Override
    public void notificar(String mensaje) {
        usuario.notificar(mensaje);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFinalizacion() {
        return horaFinal;
    }

    public String getProposito() {
        return proposito;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String getId() {
        return id;
    }
}