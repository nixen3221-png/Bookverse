package Dao;

import Conexion.Conexion;
import Modelo.EquiposOficina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquiposOficinaConexion {

	public boolean crearEquipo(EquiposOficina equipo) {
		String sql = "INSERT INTO equipos_oficina (codigo_equipo, tipo_equipo, ubicacion) VALUES (?, ?, ?)";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, equipo.getCodigo_equipo());
			ps.setString(2, equipo.getTipo_equipo());
			ps.setString(3, equipo.getUbicacion());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al crear equipo: " + e.getMessage());
			return false;
		}
	}

	public boolean modificarEquipo(EquiposOficina equipo) {
		String sql = "UPDATE equipos_oficina SET codigo_equipo = ?, tipo_equipo = ?, ubicacion = ?, estado_operativo = ? WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, equipo.getCodigo_equipo());
			ps.setString(2, equipo.getTipo_equipo());
			ps.setString(3, equipo.getUbicacion());
			ps.setString(4, equipo.getEstado_operativo());
			ps.setInt(5, equipo.getIdEquipo());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al modificar equipo: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminarEquipo(int id) {
		String sql = "DELETE FROM equipos_oficina WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar equipo: " + e.getMessage());
			return false;
		}
	}

	public List<EquiposOficina> listarEquipos() {
		List<EquiposOficina> equipos = new ArrayList<>();
		String sql = "SELECT id, codigo_equipo, tipo_equipo, ubicacion, estado_operativo FROM equipos_oficina ORDER BY id";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				equipos.add(new EquiposOficina(
						rs.getInt("id"),
						rs.getString("codigo_equipo"),
						rs.getString("tipo_equipo"),
						rs.getString("ubicacion"),
						rs.getString("estado_operativo")
				));
			}
		} catch (SQLException e) {
			System.err.println("Error al listar equipos: " + e.getMessage());
		}
		return equipos;
	}
}