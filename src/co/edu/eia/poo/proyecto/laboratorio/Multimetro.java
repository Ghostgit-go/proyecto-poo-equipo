package co.edu.eia.poo.proyecto.laboratorio;

public class Multimetro extends Equipo {
	private String rangoMedicion;
	private double precision;
	
	public Multimetro(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorio laboratorio, String rangoMedicion, double precision) {
		super(codigo, nombre, nivelRiesgo, estado, laboratorio);
		this.rangoMedicion = rangoMedicion;
		this.precision = precision;
	}
	
	@Override
	public String getTipoEquipo() {
		return "Multimetro";
	}
	
	public String getRangoMedicion() {
		return rangoMedicion;
	}
	
	public double getPrecision() {
		return precision;
	}
	

}