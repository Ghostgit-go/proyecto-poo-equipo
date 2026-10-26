package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SesionUso {
    private Reserva reserva;
    private List<String> consumibles;
    private List<String> observaciones;
    private List<String> incidentes;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean abierta = false;
    private boolean cerrada = false;

    public SesionUso(Reserva reserva) {
        this.reserva = reserva;
        this.consumibles = new ArrayList<>();
        this.observaciones = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

    // aqbre la sesión a partir de una reserva y registra la hora real de inicio
    // falla si la sesión ya estaba abierta o si la reserva no está confirmada
    public void abrirSesion(Reserva reserva, LocalTime horaInicio) throws SesionInvalidaException {
        if (abierta) {
            throw new SesionInvalidaException("La sesión ya está abierta");
        }
        if (!reserva.getEstado().equals("confirmada")) {
            throw new SesionInvalidaException("Solo se puede abrir una sesión desde una reserva confirmada");
        }
        this.reserva = reserva;
        this.horaInicio = horaInicio;
        this.abierta = true;
    }

    // cierra la sesión con la hora real, los consumibles usados, las observaciones y los incidentes
    // falla si no se había abierto, si ya se cerró no se puede cerrar dos veces o si la hora
    // de cierre es anterior a la de inicio
    public void cerrarSesion(LocalTime horaFin, List<String> consumibles, List<String> observaciones,
                             List<String> incidentes) throws SesionInvalidaException {
        if (!abierta) {
            throw new SesionInvalidaException("la sesión no se ha abierto");
        }
        if (cerrada) {
            throw new SesionInvalidaException("La sesión ya fue cerrada");
        }
        if (horaFin.isBefore(horaInicio)) {
            throw new SesionInvalidaException("La hora de cierre es anterior a la de inicio");
        }
        this.horaFin = horaFin;
        if (consumibles != null) {
            this.consumibles = consumibles;
        }
        if (observaciones != null) {
            this.observaciones = observaciones;
        }
        if (incidentes != null) {
            this.incidentes = incidentes;
        }
        this.cerrada = true;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public List<String> getConsumibles() {
        return consumibles;
    }

    public List<String> getObservaciones() {
        return observaciones;
    }

    public List<String> getIncidentes() {
        return incidentes;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}