package co.edu.eia.poo.proyecto.laboratorio;

public class CortadoraLaser extends Equipos {

		private double potenciaLaser;
		
		public CortadoraLaser(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorios laboratorios, double potenciaLaser) {
			super(codigo, nombre, nivelRiesgo, estado, laboratorios);
			this.potenciaLaser = potenciaLaser;
		}
		
		public double getpotenciaLaser() {
			return potenciaLaser;
		}
		
		@Override
		public String tipoEspecifico() {
			return "Cortadora laser";
		}
	}

