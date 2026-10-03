package Conexion.Modelos;

import Conexion.Conexion;
import Modelo.PedidosProveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosProveedoresConexion {

	public boolean crearPedido(PedidosProveedores pedido) {
		String sql = "INSERT INTO pedidos_proveedores (libro_id, proveedor_id, cantidad) VALUES (?, ?, ?)";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, pedido.getLibro_id());
			ps.setInt(2, pedido.getProveedor_id());
			ps.setInt(3, pedido.getCantidad());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al crear pedido: " + e.getMessage());
			return false;
		}
	}

	public boolean modificarPedido(PedidosProveedores pedido) {
		String sql = "UPDATE pedidos_proveedores SET libro_id = ?, proveedor_id = ?, cantidad = ?, estado_pedido = ? WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, pedido.getLibro_id());
			ps.setInt(2, pedido.getProveedor_id());
			ps.setInt(3, pedido.getCantidad());
			ps.setString(4, pedido.getEstado_pedido());
			ps.setInt(5, pedido.getIdPedido());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al modificar pedido: " + e.getMessage());
			return false;
		}
	}

	public boolean eliminarPedido(int id) {
		String sql = "DELETE FROM pedidos_proveedores WHERE id = ?";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar pedido: " + e.getMessage());
			return false;
		}
	}

	public List<PedidosProveedores> listarPedidos() {
		List<PedidosProveedores> pedidos = new ArrayList<>();
		String sql = "SELECT id, libro_id, proveedor_id, cantidad, estado_pedido, fecha_pedido FROM pedidos_proveedores ORDER BY id";
		try (Connection con = Conexion.getConexion();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				pedidos.add(new PedidosProveedores(
						rs.getInt("id"),
						rs.getInt("libro_id"),
						rs.getInt("proveedor_id"),
						rs.getInt("cantidad"),
						rs.getString("estado_pedido"),
						rs.getTimestamp("fecha_pedido")
				));
			}
		} catch (SQLException e) {
			System.err.println("Error al listar pedidos: " + e.getMessage());
		}
		return pedidos;
	}
}
