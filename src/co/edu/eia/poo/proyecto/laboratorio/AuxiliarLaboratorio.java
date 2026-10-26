package co.edu.eia.poo.proyecto.laboratorio;

import java.time.LocalTime;

public class AuxiliarLaboratorio extends Usuario {
    private String turno;

    public AuxiliarLaboratorio(String idUnico, String nombre, String correo, String turno) {
        super(idUnico, nombre, correo);
        this.turno = turno;
    }

    // registra un usuario a traves del gestor y avisa si se pudo o no
    // el gestor rechaza id repetidos
    public void registrarUsuario(Usuario u, GestorRegistro registro) {
        if (registro.registrarUsuario(u)) {
            System.out.println(getNombre() + "registro al usuario " + u.getNombre());
        } else {
            System.out.println(getNombre() + "no pudo registrar al usuario " + u.getNombre());
        }
    }

    // registra una certificacion a traves del gestor y avisa el resultado
    public void registrarCertificacion(Certificacion c, GestorRegistro registro) {
        if (registro.registrarCertificacion(c)) {
            System.out.println(getNombre() + "registro la certificacion " + c.getId());
        } else {
            System.out.println(getNombre() + "no pudo registrar la certificacion " + c.getId());
        }
    }

    // anota la asistencia de un usuario a una capacitaci[on
    public void registrarAsistencia(Capacitacion cap, Usuario u) {
        cap.registrarAsistencia(u);
    }

    // abre una sesion de uso a partir de una reserva, usando la hora actual como hora de inicio
    // devuelve la sesion, o null si el gestor la rechazo
    public SesionUso abrirSesion(Reserva r, GestorReserva gestor) {
        return gestor.abrirSesion(r, LocalTime.now());
    }

    public String getTurno() {
        return turno;
    }

    @Override
    public String getTipoUsuario() {
        return "Auxiliar de laboratorio";
    }
}