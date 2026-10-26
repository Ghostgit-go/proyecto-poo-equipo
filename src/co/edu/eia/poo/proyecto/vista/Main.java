package co.edu.eia.poo.proyecto.vista;

import co.edu.eia.poo.proyecto.repositorio.EquipoRepositorio;
import co.edu.eia.poo.proyecto.repositorio.RepositorioEquiposPostgres;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		EquipoRepositorio repositorio = new RepositorioEquiposPostgres();
		Scene escena = new Scene(new VistaEquipos(repositorio), 1000, 600);

		var css = Main.class.getResource("estilos.css");
		if (css != null) {
			escena.getStylesheets().add(css.toExternalForm());
		}

		stage.setScene(escena);
		stage.setTitle("Gestión de Laboratorios");
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}