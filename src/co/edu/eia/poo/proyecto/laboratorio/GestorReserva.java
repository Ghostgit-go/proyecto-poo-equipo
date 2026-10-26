package co.edu.eia.poo.proyecto.laboratorio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// crea y cancela reservas, abre y cierra sesiones de uso y administra la lista de espera
public class GestorReserva {
    private Repositorio<Reserva> reservas = new Repositorio<>();
    private List<SesionUso> sesiones = new ArrayList<>();
    private ListaEspera listaEspera = new ListaEspera();
    private EstrategiaPrioridad estrategia;
    private GestorRegistro registro;

    public GestorReserva(GestorRegistro registro, EstrategiaPrioridad estrategia) {
        this.registro = registro;
        this.estrategia = estrategia;
    }

    //intenta crear una reserva CONFIRMADA La rechaza devuelve false y explica por que si
    //la hora final no es posterior a la inicial o queda fuera del horario del laboratorio,
    //el equipo no esta operativo, el usuario no tiene certificacion vigente para ese tipo
    // de equipo, o el horario se cruza con otra reserva confirmada
    //si el unico problema es el cruce de horario, la solicitud pasa a la lista de espera
    public boolean crearReserva(String id, Usuario u, Equipo e, LocalDate f, LocalTime ini,
                                LocalTime fin, String proposito) {
        try {
            if (!fin.isAfter(ini)) {
                throw new HorarioInvalidoException("la hora final debe ser posterior a la inicial");
            }
            if (!e.disponibilidad()) {
                throw new EquipoNoValidoException("El equipo " + e.getId() + " no esta operativo, estado: "
                        + e.getEstado());
            }
            registro.exigirCertificacion(u, e, f);
            Reserva nueva = new Reserva(id, u, e, f, ini, fin, proposito, "confirmada");
            if (!nueva.disponibilidad(e.getLaboratorio())) {
                throw new HorarioInvalidoException("la reserva queda fuera del horario del laboratorio");
            }
            validarCruce(nueva);
            reservas.agregar(nueva);
            System.out.println("reserva " + id + " confirmada para " + u.getNombre() + " sobre " + e.getNombre());
            return true;
        } catch (ReservaCruzadaException ex) {
            System.out.println("reserva rechazada " + ex.getMessage());
            SolicitudEspera s = new SolicitudEspera(u, e, f + " " + ini +  fin);
            if (listaEspera.agregar(s)) {
                System.out.println(u.getNombre() + "pasa a la lista de espera");
            } else {
                System.out.println(u.getNombre() + " ya estaba en la lista de espera para esa franja");
            }
            return false;
        } catch (MakerLabException ex) {
            System.out.println("reserva rechazada " + ex.getMessage());
            return false;
        }
    }

    // revisa que la reserva nueva no se superponga con otra CONFIRMADA del mismo equipo
    // en la misma fecha,si se superpone, lanza ReservaCruzadaException
    private void validarCruce(Reserva nueva) throws ReservaCruzadaException {
        for (Reserva r : reservas.listar()) {
            boolean mismoEquipo = r.getEquipo().getId().equals(nueva.getEquipo().getId());
            boolean confirmada = r.getEstado().equals("confirmada");
            boolean mismaFecha = r.getFecha().equals(nueva.getFecha());
            boolean seSuperpone = nueva.getHoraInicio().isBefore(r.getHoraFinalizacion())
                    && r.getHoraInicio().isBefore(nueva.getHoraFinalizacion());
            if (mismoEquipo && confirmada && mismaFecha && seSuperpone) {
                throw new ReservaCruzadaException("el horario se cruza con la reserva " + r.getId());
            }
        }
    }

    // cancela una reserva por id y le avisa al siguiente de la lista de espera que el hueco se libero,
    // devuelve false si no existe o si no estaba confirmada
    public boolean cancelarReserva(String id) {
        Reserva r = reservas.buscar(id);
        if (r == null || !r.getEstado().equals("confirmada")) {
            return false;
        }
        r.bloquearReserva(r.getEquipo());
        liberarSiguiente(r.getEquipo());
        return true;
    }

    //busca una reserva por id null si no existe Se agrego porque abrirSesion necesita el objeto Reserva
    public Reserva buscarReserva(String id) {
        return reservas.buscar(id);
    }

    //abre una sesion de uso desde una reserva confirmada y deja el equipo en_uso
    //devuelve la sesion, o null si no se pudo abrir (reserva no confirmada, etc
    public SesionUso abrirSesion(Reserva r, LocalTime hora) {
        try {
            SesionUso s = new SesionUso(r);
            s.abrirSesion(r, hora);
            sesiones.add(s);
            r.getEquipo().cambiarEstado("en_uso");
            return s;
        } catch (SesionInvalidaException ex) {
            System.out.println("No se pudo abrir la sesión: " + ex.getMessage());
            return null;
        }
    }

    //cierra la sesion hora real, consumibles, observaciones, incidentes, deja el equipo
    //disponible otra vez y avisa al siguiente de la lista de espera
    //devuelve false si la sesion no se pudo cerrar por ejemplo, ya estaba cerrada
    public boolean cerrarSesion(SesionUso s, LocalTime horaFin, List<String> consumibles,
                                List<String> observaciones, List<String> incidentes) {
        try {
            s.cerrarSesion(horaFin, consumibles, observaciones, incidentes);
            Equipo e = s.getReserva().getEquipo();
            if (e.getEstado().equals("en_uso")) {
                e.cambiarEstado("disponible");
                liberarSiguiente(e);
            }
            return true;
        } catch (SesionInvalidaException ex) {
            System.out.println("No se pudo cerrar la sesión: " + ex.getMessage());
            return false;
        }
    }

    //cancela y notifica todas las reservas confirmadas de un equipo desde hoy en adelante
    //se usa cuando el equipo queda en mantenimiento o fuera de servicio
    public void bloquearReservasDe(Equipo e) {
        for (Reserva r : reservas.listar()) {
            boolean esFutura = !r.getFecha().isBefore(LocalDate.now());
            if (r.getEquipo().getId().equals(e.getId()) && esFutura) {
                r.bloquearReserva(e);
            }
        }
    }

    //le avisa al siguiente de la lista de espera para ese equipo segun la estrategia vigente
    public void liberarSiguiente(Equipo e) {
        listaEspera.avisarSiguiente(estrategia, e);
    }

    //cambia el criterio con el que se elige a quién avisar primero
    public void setEstrategia(EstrategiaPrioridad e) {
        this.estrategia = e;
    }
}