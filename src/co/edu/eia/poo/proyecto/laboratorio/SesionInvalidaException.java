package co.edu.eia.poo.proyecto.laboratorio;

public class SesionInvalidaException extends MakerLabException {
    private static final long serialVersionUID = 1L;


    public SesionInvalidaException(String mensaje) {
        super(mensaje);
    }
}