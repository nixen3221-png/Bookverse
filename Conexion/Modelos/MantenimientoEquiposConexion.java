package Conexion.Modelos;

import Conexion.Conexion;
import Modelo.MantenimientoEquipos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoEquiposConexion {

	public boolean crearMantenimiento(MantenimientoEquipos mantenimiento) {
		String sql = "INSERT INTO mantenimiento_equipos (equipo_id, tipo_mantenimiento, descripcion, tecnico, estado_software) VALUES (?, ?, ?, ?, ?)";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, mantenimiento.getEquipo_id());
			ps.setString(2, mantenimiento.getTipo_mantenimiento());
			ps.setString(3, mantenimiento.getDescripcion());
			ps.setString(4, mantenimiento.getTecnico());
			ps.setString(5, mantenimiento.getEstado_software());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al crear mantenimiento: " + e.getMessage());
			return false;
		}
	}

	public boolean modificarMantenimiento(MantenimientoEquipos mantenimiento) {
		String sql = "UPDATE mantenimiento_equipos SET equipo_id = ?, tipo_mantenimiento = ?, descripcion = ?, tecnico = ?, estado_software = ? WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, mantenimiento.getEquipo_id());
			ps.setString(2, mantenimiento.getTipo_mantenimiento());
			ps.setString(3, mantenimiento.getDescripcion());
			ps.setString(4, mantenimiento.getTecnico());
			ps.setString(5, mantenimiento.getEstado_software());
			ps.setInt(6, mantenimiento.getIdMantenimiento());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al modificar mantenimiento: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminarMantenimiento(int id) {
		String sql = "DELETE FROM mantenimiento_equipos WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar mantenimiento: " + e.getMessage());
			return false;
		}
	}

	public List<MantenimientoEquipos> listarMantenimientos() {
		List<MantenimientoEquipos> mantenimientos = new ArrayList<>();
		String sql = "SELECT id, equipo_id, tipo_mantenimiento, descripcion, tecnico, estado_software, fecha_mantenimiento FROM mantenimiento_equipos ORDER BY id";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				mantenimientos.add(new MantenimientoEquipos(
						rs.getInt("id"),
						rs.getInt("equipo_id"),
						rs.getString("tipo_mantenimiento"),
						rs.getString("descripcion"),
						rs.getString("tecnico"),
						rs.getString("estado_software"),
						rs.getTimestamp("fecha_mantenimiento")
				));
			}
		} catch (SQLException e) {
			System.err.println("Error al listar mantenimientos: " + e.getMessage());
		}
		return mantenimientos;
	}
}
