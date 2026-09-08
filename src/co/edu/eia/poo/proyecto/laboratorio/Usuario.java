package co.edu.eia.poo.proyecto.laboratorio;

public class Usuario {
	private String nombre;
	private String idUnico;
	private String correo;
	private String tipo;
	
	public Usuario(String nombre, String idUnico, String correo, String tipo) {
		
		this.nombre = nombre;
		this.idUnico = idUnico;
		this.correo = correo;
		this.tipo = tipo;
	}
	
	public void mostrarUsuario() {
		System.out.println("Nombre: " + nombre +" Id unico: " + idUnico+ " Correo: "+ correo + "tipo: "+ tipo); 
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getIdUnico() {
		return idUnico;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getTipo() {
		return tipo;
	}
	   
}
