package Dao;

import Conexion.Conexion;
import Modelo.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresConexion {

	public boolean crearProveedor(Proveedores proveedor) {
		String sql = "INSERT INTO proveedores (nombre_empresa, contacto, telefono) VALUES (?, ?, ?)";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, proveedor.getNombre_empresa());
			ps.setString(2, proveedor.getContacto());
			ps.setString(3, proveedor.getTelefono());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al crear proveedor: " + e.getMessage());
			return false;
		}
	}

	public boolean modificarProveedor(Proveedores proveedor) {
		String sql = "UPDATE proveedores SET nombre_empresa = ?, contacto = ?, telefono = ? WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, proveedor.getNombre_empresa());
			ps.setString(2, proveedor.getContacto());
			ps.setString(3, proveedor.getTelefono());
			ps.setInt(4, proveedor.getIdProveedor());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al modificar proveedor: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminarProveedor(int id) {
		String sql = "DELETE FROM proveedores WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar proveedor: " + e.getMessage());
			return false;
		}
	}

	public List<Proveedores> listarProveedores() {
		List<Proveedores> proveedores = new ArrayList<>();
		String sql = "SELECT id, nombre_empresa, contacto, telefono FROM proveedores ORDER BY id";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				proveedores.add(new Proveedores(
						rs.getInt("id"),
						rs.getString("nombre_empresa"),
						rs.getString("contacto"),
						rs.getString("telefono")
				));
			}
		} catch (SQLException e) {
			System.err.println("Error al listar proveedores: " + e.getMessage());
		}
		return proveedores;
	}
}