package co.edu.eia.poo.proyecto.laboratorio;

import java.util.List;

//contrato para decidir quién pasa primero en la lista de espera
//cada implementación aplica un criterio distinto
public interface EstrategiaPrioridad {

    //recibe las solicitudes candidatas y retorna la elegida o null si la lista esta vacia
    SolicitudEspera seleccionar(List<SolicitudEspera> c);
}