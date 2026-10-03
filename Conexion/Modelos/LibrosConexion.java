package Conexion.Modelos;

import Modelo.Libros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrosConexion {

    // Validación
    public Libros login(String correo, String contrasena) {
        String sql = "SELECT * FROM libros WHERE correo = ? AND contrasena = ? AND activo = TRUE";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Libros(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getBigDecimal("precio"),
                    rs.getInt("stock_disponible"),
                    rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return null;
    }

    // Crear Usuario
    public boolean crearUsuario(Libros l) {
        String sql = "INSERT INTO libros (titulo, autor, genero, precio, stock_disponible, fecha_registro, activo) VALUES (?, ?, ?, ?, ?, ?, TRUE)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setString(3, l.getGenero());
            ps.setDecimal(4, l.getPrecio());
            ps.setInt(5, l.getStockDisponible());
            ps.setDate(6, l.getFechaRegistro());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear libro: " + e.getMessage());
            return false;
        }
    }

    // Modificar Usuario
    public boolean modificarUsuario(Libros l) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, genero = ?, precio = ?, stock_disponible = ?, fecha_registro = ? WHERE id_libro = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setString(3, l.getGenero());
            ps.setDecimal(4, l.getPrecio());
            ps.setInt(5, l.getStockDisponible());
            ps.setDate(6, l.getFechaRegistro());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar libro: " + e.getMessage());
            return false;
        }
    }

    // Eliminar Usuario 
    public boolean eliminarUsuario(int id) {
        String sql = "UPDATE usuarios SET activo = FALSE WHERE id_usuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los usuarios activos
    public List<Libros> listarUsuarios() {
        List<Libros> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE activo = TRUE";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new Libros(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getBigDecimal("precio"),
                    rs.getInt("stock_disponible"),
                    rs.getDate("fecha_registro")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libro: " + e.getMessage());
        }
        return lista;
    }
}
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getBigDecimal("precio"),
                    rs.getInt("stock_disponible"),
                    rs.getDate("fecha_registro")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
        return lista;
    }
}