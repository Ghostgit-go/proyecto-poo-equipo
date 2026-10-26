package co.edu.eia.poo.proyecto.laboratorio;

import java.util.ArrayList;
import java.util.List;

public class TicketMantenimiento implements Identificable {
    private String id;
    private int prioridad;
    private String descripcion;
    private Equipo equipo;
    private Tecnico tecnico;
    private String estado; // abierto asignado cerrado
    private List<String> historialCambios;

    public TicketMantenimiento(String id, int prioridad, String descripcion, Equipo equipo,
                               Tecnico tecnico, String estado, List<String> historialCambios) {
        this.id = id;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.equipo = equipo;
        this.tecnico = tecnico;
        this.estado = estado;
        if (historialCambios == null) {
            this.historialCambios = new ArrayList<>();
        } else {
            this.historialCambios = historialCambios;
        }
    }

    // Agrega una línea al historial de cambios (solo se acumula, nunca se borra
    public void actualizarHistorial(String cambio) {
        historialCambios.add(cambio);
    }

    // Deja asignado el técnico responsable y pasa el ticket a "asignado"
    //metodo agregad el UML no tenía forma de asignar el técnico después de crear el ticket
    public void asignarTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
        this.estado = "asignado";
        actualizarHistorial("Asignado al técnico " + tecnico.getNombre());
    }

    // Cierra el ticket. Reglas: no puede estar ya cerrado, debe indicarse la acción realizada,
    // debe tener técnico responsable y el estado final del equipo debe ser valido
    // "disponible" o "fuera_de_servicio"). Si algo falla se lanza TicketInvalidoException
    public void cerrar(String accion, String estadoFinal) throws TicketInvalidoException {
        if (estado.equals("cerrado")) {
            throw new TicketInvalidoException("El ticket " + id + " ya está cerrado.");
        }
        if (accion == null || accion.trim().isEmpty()) {
            throw new TicketInvalidoException("No se puede cerrar sin registrar la acción realizada.");
        }
        if (tecnico == null) {
            throw new TicketInvalidoException("No se puede cerrar sin un técnico responsable.");
        }
        if (!estadoFinal.equals("disponible") && !estadoFinal.equals("fuera_de_servicio")) {
            throw new TicketInvalidoException("Estado final inválido: " + estadoFinal);
        }
        actualizarHistorial("Acción: " + accion + " | Técnico: " + tecnico.getNombre()
                + " | Estado final del equipo: " + estadoFinal);
        estado = "cerrado";
    }

    @Override
    public String getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public String getEstado() {
        return estado;
    }

    public List<String> getHistorialCambios() {
        return historialCambios;
    }
}