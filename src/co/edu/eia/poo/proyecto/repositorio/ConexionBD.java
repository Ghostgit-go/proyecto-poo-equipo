package co.edu.eia.poo.proyecto.repositorio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

	private static final String ARCHIVO = "config.properties";

	public static Connection obtener() throws SQLException {
		Properties config = new Properties();
		try (FileInputStream entrada = new FileInputStream(ARCHIVO)) {
			config.load(entrada);
		} catch (IOException e) {
			throw new SQLException("No se pudo leer " + ARCHIVO, e);
		}
		return DriverManager.getConnection(
				config.getProperty("db.url"),
				config.getProperty("db.usuario"),
				config.getProperty("db.clave"));
	}
}