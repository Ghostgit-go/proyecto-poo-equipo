package co.edu.eia.poo.proyecto.laboratorio;

import java.time.LocalDateTime;

public class SolicitudEspera {
    private Usuario usuario;
    private Equipo equipo;
    private String franja;
    private LocalDateTime fechaSolicitud;

    public SolicitudEspera(Usuario usuario, Equipo equipo, String franja) {
        this.usuario = usuario;
        this.equipo = equipo;
        this.franja = franja;
        this.fechaSolicitud = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getFranja() {
        return franja;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    //clave que identifica la solicitud: persona + equipo + franja
    //dos solicitudes con la misma clave son duplicadas regla de la lista de espera
    public String getClave() {
        return usuario.getId() + " " + equipo.getId() + " " + franja;
    }
}