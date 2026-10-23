package co.edu.eia.poo.proyecto.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.eia.poo.proyecto.laboratorio.Equipos;
import co.edu.eia.poo.proyecto.laboratorio.Impresora3D;
import co.edu.eia.poo.proyecto.laboratorio.Laboratorios;

public class RepositorioEquiposMemoria implements EquipoRepositorio {

	private final List<Equipos> equipos = new ArrayList<>();

	public RepositorioEquiposMemoria() {
		Laboratorios electronica = new Laboratorios("LAB-01", "Electrónica", "Bloque 3", "7:00-17:00", true);
		Laboratorios quimica = new Laboratorios("LAB-02", "Química", "Bloque 5", "8:00-16:00", true);

		equipos.add(new Equipos("EQ-001", "Osciloscopio", 2, "disponible", electronica));
		equipos.add(new Equipos("EQ-002", "Multímetro", 1, "prestado", electronica));
		equipos.add(new Impresora3D("EQ-003", "Impresora 3D", 3, "mantenimiento", electronica, "PLA"));
		equipos.add(new Equipos("EQ-004", "Centrífuga", 4, "disponible", quimica));
		equipos.add(new Equipos("EQ-005", "Cortadora láser", 5, "prestado", quimica));
	}

	@Override
	public List<Equipos> listar() {
		return new ArrayList<>(equipos);
	}

	@Override
	public Optional<Equipos> buscarPorCodigo(String codigo) {
		return equipos.stream().filter(e -> e.getCodigo().equals(codigo)).findFirst();
	}

	@Override
	public void guardar(Equipos equipo) {
		buscarPorCodigo(equipo.getCodigo()).ifPresent(equipos::remove);
		equipos.add(equipo);
	}
}