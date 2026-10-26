package co.edu.eia.poo.proyecto.laboratorio;

// Excepción base del sistema todas las excepciones propias del dominio heredan de ella.
public class MakerLabException extends Exception {
    private static final long serialVersionUID = 1L;


    public MakerLabException(String mensaje) {
        super(mensaje);
    }
}