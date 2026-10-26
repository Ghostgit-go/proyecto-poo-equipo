package co.edu.eia.poo.proyecto.laboratorio;

import java.util.ArrayList;
import java.util.List;

public class ListaEspera {
    private List<SolicitudEspera> solicitudes;

    public ListaEspera() {
        this.solicitudes = new ArrayList<>();
    }

    //agrega una solicitud devuelve false y no la agrega, si ya existe otra con la misma
    // persona, equipo y franja
    public boolean agregar(SolicitudEspera s) {
        for (SolicitudEspera existente : solicitudes) {
            if (existente.getClave().equals(s.getClave())) {
                return false;
            }
        }
        solicitudes.add(s);
        return true;
    }

    //elige, entre todas las solicitudes, la siguiente según la estrategia recibida
    //por llegada, por tipo de usuario, etc devuelve null si no hay ninguna
    public SolicitudEspera siguienteCandidato(EstrategiaPrioridad e) {
        return e.seleccionar(solicitudes);
    }

    //busca, entre las solicitudes de ese equipo, la siguiente segun la estrategia,
    // le avisa al usuario que el equipo ya esta libre y la saca de la lista
    public void avisarSiguiente(EstrategiaPrioridad estrategia, Equipo equipo) {
        List<SolicitudEspera> delEquipo = new ArrayList<>();
        for (SolicitudEspera s : solicitudes) {
            if (s.getEquipo().getId().equals(equipo.getId())) {
                delEquipo.add(s);
            }
        }
        SolicitudEspera elegida = estrategia.seleccionar(delEquipo);
        if (elegida != null) {
            elegida.getUsuario().notificar("El equipo " + equipo.getNombre()
                    + " ya esta disponible puedes intentar tu reserva para la franja " + elegida.getFranja());
            remover(elegida);
        }
    }

    //asaca una solicitud de la lista
    public void remover(SolicitudEspera s) {
        solicitudes.remove(s);
    }

    //cantidad de solicitudes en espera
    public int tamano() {
        return solicitudes.size();
    }
}