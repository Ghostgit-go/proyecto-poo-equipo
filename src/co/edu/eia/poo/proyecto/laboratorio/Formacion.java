package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalDate;

public class Formacion {
	private String fechaEmision;
	private String vigencia;
	private Usuario usuario;
	private Equipos equipos;
	
	public Formacion(String fechaEmision, String vigencia, Usuario usuario, Equipos equipos) {
		this.fechaEmision = fechaEmision;
		this.vigencia = vigencia;
		this.usuario = usuario;
		this.equipos = equipos;
	}
	//APOYO CON IA EN ESTE METODO
	// compara la vigencia en formato yyyy-mm-dd cosntra la fecha actual del sistema, si ya paso retorna false
	 public boolean estaVigente() {
	        LocalDate fechaVigencia = LocalDate.parse(vigencia);
	        LocalDate hoy = LocalDate.now();
	        return !hoy.isAfter(fechaVigencia);
	}
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	
	public String getVigencia() {
		return vigencia;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Equipos getEquipos() {
		return equipos;
	}

}
