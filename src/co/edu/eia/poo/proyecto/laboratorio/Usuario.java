package co.edu.eia.poo.proyecto.laboratorio;

public abstract class Usuario implements Identificable, Notificable{
	private String idUnico;
	private String nombre;
	private String correo;
	
	public Usuario(String idUnico, String nombre, String correo) {
		this.idUnico = idUnico;
		this.nombre = nombre;
		this.correo = correo;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + nombre +" Id unico: " + idUnico+ " Correo: "+ correo + "tipo de usuario:"+ getTipoUsuario(); 
	}
	
	public String getNombre() {
		return nombre;
	}
	@Override
	public String getId() {
		return idUnico;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public abstract String getTipoUsuario();
	
	@Override
	public void notificar(String mensaje) {
		System.out.println("para " + nombre + " " + mensaje);
	}
	

	   
}
