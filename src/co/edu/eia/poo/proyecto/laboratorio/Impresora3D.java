package co.edu.eia.poo.proyecto.laboratorio;

public class Impresora3D extends Equipo{
	private String materialCompatible;
	
	public Impresora3D(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorio laboratorio, String materialCompatible) {
		super(codigo, nombre, nivelRiesgo, estado, laboratorio);
		this.materialCompatible = materialCompatible;
	}
	
	@Override
	public String getTipoEquipo(){
		return "Impresora 3D";
	}
	
	public String getMaterialCompatible() {
		return materialCompatible;
	}
	
}
