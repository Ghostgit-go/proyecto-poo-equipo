package co.edu.eia.poo.proyecto.laboratorio;

public abstract class Usuario implements Notificable{
	private String nombre;
	private String id;
	private String correo;
	
	public Usuario(String nombre, String id, String correo) {
		
		this.nombre = nombre;
		this.id = id;
		this.correo = correo;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + nombre +" Id unico: " + id+ " Correo: "+ correo; 
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getId() {
		return id;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public abstract String getTipoUsuario();
	
	@Override
	public void notificar(String mensaje) {
		System.out.println(mensaje);
	}
	

	   
}
