package co.edu.eia.poo.proyecto.laboratorio;

import java.util.List;

//el primero que llega, el primero que sale
public class PrioridadPorLlegada implements EstrategiaPrioridad {

    //devuelve la solicitud con la fecha de solicitud mas antigua null si la lista esta vacia
    @Override
    public SolicitudEspera seleccionar(List<SolicitudEspera> c) {
        if (c == null || c.isEmpty()) {
            return null;
        }
        SolicitudEspera elegida = c.get(0);
        for (SolicitudEspera s : c) {
            if (s.getFechaSolicitud().isBefore(elegida.getFechaSolicitud())) {
                elegida = s;
            }
        }
        return elegida;
    }
}