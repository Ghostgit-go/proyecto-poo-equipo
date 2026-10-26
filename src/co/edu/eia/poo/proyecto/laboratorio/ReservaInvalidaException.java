package co.edu.eia.poo.proyecto.laboratorio;

public class ReservaInvalidaException extends MakerLabException {
    private static final long serialVersionUID = 1L;


    public ReservaInvalidaException(String mensaje) {
        super(mensaje);
    }
}