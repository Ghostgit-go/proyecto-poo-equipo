package co.edu.eia.poo.proyecto.laboratorio;

public class Impresora3D extends Equipos{
	private String materialCompatible;
	
	public Impresora3D(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorios laboratorios, String materialCompatible) {
		super(codigo, nombre, nivelRiesgo, estado, laboratorios);
		this.materialCompatible = materialCompatible;
	}
	
	@Override
	public String getTipoEquipo(){
		return "Impresora 3D - Transforma archivos digitales en objetos físicos tridimensionales";
	}
	
	public String getMaterialCompatible() {
		return materialCompatible;
	}
	
}
