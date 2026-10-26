package co.edu.eia.poo.proyecto.laboratorio;

public class CertificacionVencidaException extends ReservaInvalidaException {
    private static final long serialVersionUID = 1L;


    public CertificacionVencidaException(String mensaje) {
        super(mensaje);
    }
}