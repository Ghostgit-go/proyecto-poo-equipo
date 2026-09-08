package co.edu.eia.poo.proyecto.laboratorio;

public class Multimetro extends Equipos {
	private String rangoMedicion;
	private double precision;
	
	public Multimetro(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorios laboratorios, String rangoMedicion, double precision) {
		super(codigo, nombre, nivelRiesgo, estado, laboratorios);
		this.rangoMedicion = rangoMedicion;
		this.precision = precision;
	}
	
	public String getRangoMedicion() {
		return rangoMedicion;
	}
	
	public double getPrecision() {
		return precision;
	}
	
	@Override
	public String tipoEspecifico() {
		return "Multimetro";
	}

}