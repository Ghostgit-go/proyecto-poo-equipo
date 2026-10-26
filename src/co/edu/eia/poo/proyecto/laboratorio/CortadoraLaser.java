package co.edu.eia.poo.proyecto.laboratorio;

public class CortadoraLaser extends Equipo {

		private double potenciaLaser;
		
		public CortadoraLaser(String codigo, String nombre, int nivelRiesgo, String estado, Laboratorio laboratorio, double potenciaLaser) {
			super(codigo, nombre, nivelRiesgo, estado, laboratorio);
			this.potenciaLaser = potenciaLaser;
		}
		
		@Override
		public String getTipoEquipo() {
			return "Cortadora Laser";
		}
		
		public double getpotenciaLaser() {
			return potenciaLaser;
		}
		
	}

