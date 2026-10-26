package co.edu.eia.poo.proyecto.repositorio;

import java.sql.Connection;

public class PruebaConexion {

	public static void main(String[] args) {
		try (Connection c = ConexionBD.obtener()) {
			System.out.println("Conexión exitosa a: " + c.getCatalog());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}