package co.edu.eia.poo.proyecto.vista;

import java.util.ArrayList;
import java.util.List;

import co.edu.eia.poo.proyecto.laboratorio.Equipos;
import co.edu.eia.poo.proyecto.repositorio.EquipoRepositorio;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaEquipos extends BorderPane {

	private final EquipoRepositorio repositorio;
	private final ObservableList<Equipos> datos = FXCollections.observableArrayList();
	private final FilteredList<Equipos> filtrados = new FilteredList<>(datos, e -> true);
	private final TextField campoBusqueda = new TextField();
	private final ComboBox<String> filtroEstado = new ComboBox<>();

	private final Label lblTotal = new Label("0");
	private final Label lblDisponibles = new Label("0");
	private final Label lblMantenimiento = new Label("0");
	private final Label lblOcupados = new Label("0");

	public VistaEquipos(EquipoRepositorio repositorio) {
		this.repositorio = repositorio;
		setPadding(new Insets(16));
		setTop(crearEncabezado());
		setCenter(crearTabla());
		recargar();
	}

	private VBox crearEncabezado() {
		Label titulo = new Label("Gestión de Laboratorios");
		titulo.getStyleClass().add("titulo");
		Label subtitulo = new Label("Inventario de equipos y su estado actual");
		subtitulo.getStyleClass().add("subtitulo");

		HBox tarjetas = new HBox(12,
				crearTarjeta("Total de equipos", lblTotal),
				crearTarjeta("Disponibles", lblDisponibles),
				crearTarjeta("En mantenimiento", lblMantenimiento),
				crearTarjeta("Ocupados", lblOcupados));

		VBox encabezado = new VBox(10, titulo, subtitulo, tarjetas, crearBarra());
		encabezado.setPadding(new Insets(0, 0, 12, 0));
		return encabezado;
	}

	private VBox crearTarjeta(String texto, Label numero) {
		numero.getStyleClass().add("tarjeta-numero");
		Label etiqueta = new Label(texto);
		etiqueta.getStyleClass().add("tarjeta-texto");
		VBox tarjeta = new VBox(2, numero, etiqueta);
		tarjeta.getStyleClass().add("tarjeta");
		tarjeta.setMinWidth(150);
		return tarjeta;
	}

	private HBox crearBarra() {
		campoBusqueda.setPromptText("Código o nombre");
		campoBusqueda.textProperty().addListener((obs, antes, ahora) -> aplicarFiltro());
		filtroEstado.valueProperty().addListener((obs, antes, ahora) -> aplicarFiltro());

		HBox barra = new HBox(10, new Label("Buscar:"), campoBusqueda, new Label("Estado:"), filtroEstado);
		barra.setAlignment(Pos.CENTER_LEFT);
		return barra;
	}

	@SuppressWarnings("unchecked")
	private TableView<Equipos> crearTabla() {
		TableView<Equipos> tabla = new TableView<>(filtrados);

		TableColumn<Equipos, String> colCodigo = new TableColumn<>("Código");
		colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));

		TableColumn<Equipos, String> colNombre = new TableColumn<>("Nombre");
		colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));

		TableColumn<Equipos, String> colTipo = new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().tipoEspecifico()));

		TableColumn<Equipos, Number> colRiesgo = new TableColumn<>("Nivel de riesgo");
		colRiesgo.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getNivelRiesgo()));

		TableColumn<Equipos, String> colEstado = new TableColumn<>("Estado");
		colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
		colEstado.setCellFactory(col -> new TableCell<Equipos, String>() {
			private final Label etiqueta = new Label();

			@Override
			protected void updateItem(String estado, boolean vacio) {
				super.updateItem(estado, vacio);
				if (vacio || estado == null) {
					setGraphic(null);
					setText(null);
					return;
				}
				etiqueta.setText(estado);
				etiqueta.getStyleClass().removeAll("badge", "badge-verde", "badge-amarillo", "badge-rojo", "badge-gris");
				etiqueta.getStyleClass().addAll("badge", claseEstado(estado));
				setText(null);
				setGraphic(etiqueta);
			}
		});

		TableColumn<Equipos, String> colLab = new TableColumn<>("Laboratorio");
		colLab.setCellValueFactory(c -> new SimpleStringProperty(
				c.getValue().getLaboratorio() == null ? "-" : c.getValue().getLaboratorio().getNombre()));

		tabla.getColumns().addAll(colCodigo, colNombre, colTipo, colRiesgo, colEstado, colLab);
		tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		tabla.setPlaceholder(new Label("No hay equipos para mostrar"));
		return tabla;
	}

	private String claseEstado(String estado) {
		String e = estado.toLowerCase();
		if (e.equals("disponible")) {
			return "badge-verde";
		}
		if (e.contains("manten")) {
			return "badge-amarillo";
		}
		if (e.contains("fuera")) {
			return "badge-rojo";
		}
		return "badge-gris";
	}

	private void aplicarFiltro() {
		String texto = campoBusqueda.getText() == null ? "" : campoBusqueda.getText().trim().toLowerCase();
		String estado = filtroEstado.getValue();

		filtrados.setPredicate(e -> {
			boolean coincideTexto = texto.isEmpty()
					|| e.getCodigo().toLowerCase().contains(texto)
					|| e.getNombre().toLowerCase().contains(texto);
			boolean coincideEstado = estado == null || estado.equals("Todos") || estado.equals(e.getEstado());
			return coincideTexto && coincideEstado;
		});
	}

	private void actualizarContadores() {
		lblTotal.setText(String.valueOf(datos.size()));
		lblDisponibles.setText(String.valueOf(
				datos.stream().filter(e -> e.getEstado().equalsIgnoreCase("disponible")).count()));
		lblMantenimiento.setText(String.valueOf(
				datos.stream().filter(e -> e.getEstado().toLowerCase().contains("manten")).count()));
		lblOcupados.setText(String.valueOf(
				datos.stream().filter(e -> e.getEstado().equalsIgnoreCase("ocupado")
						|| e.getEstado().equalsIgnoreCase("prestado")).count()));
	}

	public void recargar() {
		datos.setAll(repositorio.listar());
		actualizarContadores();

		String seleccionado = filtroEstado.getValue();
		List<String> estados = new ArrayList<>();
		estados.add("Todos");
		datos.stream().map(Equipos::getEstado).distinct().sorted().forEach(estados::add);

		filtroEstado.setItems(FXCollections.observableArrayList(estados));
		filtroEstado.setValue(estados.contains(seleccionado) ? seleccionado : "Todos");
	}
}