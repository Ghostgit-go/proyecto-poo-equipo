package co.edu.eia.poo.proyecto.laboratorio;

public class Notificacion {
	private String mensaje;
	private Usuario destinatario;
	
	public Notificacion(String mensaje, Usuario destinatario) {
		this.mensaje = mensaje;
		this.destinatario = destinatario;
	}
	
	public void enviarNotificacion() {
		System.out.println("Para " + destinatario.getNombre() + " " + mensaje);
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public Usuario getDestinatario() {
		return destinatario;
	}

}
