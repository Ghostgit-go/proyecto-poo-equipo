package co.edu.eia.poo.proyecto.laboratorio;

public class TicketInvalidoException extends MakerLabException {
    private static final long serialVersionUID = 1L;


    public TicketInvalidoException(String mensaje) {
        super(mensaje);
    }
}