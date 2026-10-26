package co.edu.eia.poo.proyecto.laboratorio;

import java.util.List;
import java.util.Map;

//estrategia por tipo de usuario cada tipo por ejemplo Profesor tiene un peso
// gana el de mayor peso y, si empatan, el que llego primero
public class PrioridadPorUsuario implements EstrategiaPrioridad {
    private Map<String, Integer> pesoUsuario;

    public PrioridadPorUsuario(Map<String, Integer> pesoUsuario) {
        this.pesoUsuario = pesoUsuario;
    }

    //devuelve la solicitud cuyo tipo de usuario tenga mayor peso los tipos sin peso valen 0
    @Override
    public SolicitudEspera seleccionar(List<SolicitudEspera> c) {
        if (c == null || c.isEmpty()) {
            return null;
        }
        SolicitudEspera elegida = c.get(0);
        for (SolicitudEspera s : c) {
            int pesoS = peso(s);
            int pesoElegida = peso(elegida);
            if (pesoS > pesoElegida
                    || (pesoS == pesoElegida && s.getFechaSolicitud().isBefore(elegida.getFechaSolicitud()))) {
                elegida = s;
            }
        }
        return elegida;
    }

    // peso del tipo de usuario de una solicitud 0 si no está en el mapa
    private int peso(SolicitudEspera s) {
        Integer p = pesoUsuario.get(s.getUsuario().getTipoUsuario());
        if (p == null) {
            return 0;
        }
        return p;
    }
}