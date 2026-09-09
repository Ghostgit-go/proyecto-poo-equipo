package co.edu.eia.poo.proyecto.laboratorio;

public class Impresora3D extends Equipos{
	private String materialCompatible;
	
	public Impresora3D(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorios laboratorios, String materialCompatible) {
		super(codigo, nombre, nivelRiesgo, estado, laboratorios);
		this.materialCompatible = materialCompatible;
	}
	
	public String getMaterialCompatible() {
		return materialCompatible;
	}
	// aqui identifica el metodo de tipoespecifico y lo superpone por impresora 3d
	@Override
	public String tipoEspecifico() {
		return "Impresora 3D";
	}

}
