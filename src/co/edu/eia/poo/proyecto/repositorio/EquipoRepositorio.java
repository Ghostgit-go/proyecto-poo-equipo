package co.edu.eia.poo.proyecto.repositorio;

import java.util.List;
import java.util.Optional;

import co.edu.eia.poo.proyecto.laboratorio.Equipos;

public interface EquipoRepositorio {
	List<Equipos> listar();
	Optional<Equipos> buscarPorCodigo(String codigo);
	void guardar(Equipos equipo);
}