package co.edu.eia.poo.proyecto.laboratorio;

public class EquipoNoValidoException extends ReservaInvalidaException {
    private static final long serialVersionUID = 1L;


    public EquipoNoValidoException(String mensaje) {
        super(mensaje);
    }
}