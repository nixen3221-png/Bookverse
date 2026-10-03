package Conexion.Modelos;

import Conexion.Conexion;
import Modelo.Libros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrosConexion {

    public boolean crearLibro(Libros libro) {
        String sql = "INSERT INTO libros (titulo, autor, genero, precio, stock_disponible) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getGenero());
            ps.setBigDecimal(4, libro.getPrecio());
            ps.setInt(5, libro.getStock_disponible());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear libro: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarLibro(Libros libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, genero = ?, precio = ?, stock_disponible = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getGenero());
            ps.setBigDecimal(4, libro.getPrecio());
            ps.setInt(5, libro.getStock_disponible());
            ps.setInt(6, libro.getIdLibro());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar libro: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }

    public List<Libros> listarLibros() {
        List<Libros> libros = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, genero, precio, stock_disponible, fecha_registro FROM libros ORDER BY id";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                libros.add(new Libros(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getBigDecimal("precio"),
                        rs.getInt("stock_disponible"),
                        rs.getTimestamp("fecha_registro")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
        return libros;
    }
}