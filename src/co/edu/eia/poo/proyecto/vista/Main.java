package co.edu.eia.poo.proyecto.vista;

import co.edu.eia.poo.proyecto.repositorio.EquipoRepositorio;
import co.edu.eia.poo.proyecto.repositorio.RepositorioEquiposMemoria;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		EquipoRepositorio repositorio = new RepositorioEquiposMemoria();
		stage.setScene(new Scene(new VistaEquipos(repositorio), 900, 500));
		stage.setTitle("Gestión de Laboratorios");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}