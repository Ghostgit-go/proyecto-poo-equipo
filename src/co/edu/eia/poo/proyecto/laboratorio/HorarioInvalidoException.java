package co.edu.eia.poo.proyecto.laboratorio;

public class HorarioInvalidoException extends ReservaInvalidaException {
    private static final long serialVersionUID = 1L;


    public HorarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}