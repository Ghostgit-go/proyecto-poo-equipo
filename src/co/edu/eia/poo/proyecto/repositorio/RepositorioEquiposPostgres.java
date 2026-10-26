package co.edu.eia.poo.proyecto.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.eia.poo.proyecto.laboratorio.Equipos;
import co.edu.eia.poo.proyecto.laboratorio.Impresora3D;
import co.edu.eia.poo.proyecto.laboratorio.Laboratorios;

public class RepositorioEquiposPostgres implements EquipoRepositorio {

	private static final String CONSULTA = "SELECT e.codigo, e.nombre, e.tipo_equipo, e.nivel_riesgo, e.estado, "
			+ "l.codigo AS lab_codigo, l.nombre AS lab_nombre, l.ubicaci\u00f3n AS lab_ubicacion, "
			+ "l.horario_apertura, l.horario_cierre, l.estado AS lab_estado, i.material_compatible "
			+ "FROM equipo e "
			+ "JOIN laboratorio l ON e.laboratorio_codigo = l.codigo "
			+ "LEFT JOIN impresora_3d i ON i.codigo_equipo = e.codigo ";

	@Override
	public List<Equipos> listar() {
		List<Equipos> lista = new ArrayList<>();
		try (Connection c = ConexionBD.obtener();
				PreparedStatement ps = c.prepareStatement(CONSULTA + "ORDER BY e.codigo");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(crearEquipo(rs));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("No se pudieron leer los equipos", e);
		}
		return lista;
	}

	@Override
	public Optional<Equipos> buscarPorCodigo(String codigo) {
		try (Connection c = ConexionBD.obtener();
				PreparedStatement ps = c.prepareStatement(CONSULTA + "WHERE e.codigo = ?")) {
			ps.setString(1, codigo);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? Optional.of(crearEquipo(rs)) : Optional.empty();
			}
		} catch (SQLException e) {
			throw new IllegalStateException("No se pudo buscar el equipo " + codigo, e);
		}
	}

	@Override
	public void guardar(Equipos equipo) {
		throw new UnsupportedOperationException("Guardar se implementa con el formulario");
	}

	private Equipos crearEquipo(ResultSet rs) throws SQLException {
		String horario = rs.getTime("horario_apertura").toLocalTime() + "-"
				+ rs.getTime("horario_cierre").toLocalTime();
		Laboratorios lab = new Laboratorios(rs.getString("lab_codigo"), rs.getString("lab_nombre"),
				rs.getString("lab_ubicacion"), horario, rs.getBoolean("lab_estado"));

		String codigo = rs.getString("codigo");
		String nombre = rs.getString("nombre");
		int riesgo = rs.getInt("nivel_riesgo");
		String estado = rs.getString("estado");

		if ("Impresora3D".equals(rs.getString("tipo_equipo"))) {
			return new Impresora3D(codigo, nombre, riesgo, estado, lab, rs.getString("material_compatible"));
		}
		return new Equipos(codigo, nombre, riesgo, estado, lab);
	}
}