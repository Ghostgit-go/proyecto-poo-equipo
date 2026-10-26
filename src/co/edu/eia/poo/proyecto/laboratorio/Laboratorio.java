package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalTime;

public class Laboratorio implements Identificable{
	private String codigo;
	private String nombre;
	private String ubicacion;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private boolean estado;
	
	public Laboratorio(String codigo, String nombre, String ubicacion, LocalTime horaApertura, LocalTime horaCierre, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
		this.ubicacion = ubicacion;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		
	}
	 //indica si el laboratorio esta abierto a esa hora debe estar activo estado true
    // y la hora debe caer desde la apertura incluida hasta el cierre excluido
	public boolean estaAbierto(LocalTime hora) {
		return estado && !hora.isBefore(horaApertura) && hora.isBefore(horaCierre);
	}
	
	@Override
	public String getId() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public LocalTime getHoraApertura() {
		return horaApertura;
	}
	
	public LocalTime getHoraCierre() {
		return horaCierre;
	}
	
	public boolean getEstado() {
		return estado;
	}
}
