package Controlador;

import Dao.LibrosConexion;
import Modelo.Libros;
import java.math.BigDecimal;
import java.util.List;

public class LibrosControlador {
    private LibrosConexion dao;

    public LibrosControlador() {
        this.dao = new LibrosConexion();
    }

    public boolean registrarLibro(String titulo, String autor, String genero, BigDecimal precio, int stock) {
        Libros libro = new Libros();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setGenero(genero);
        libro.setPrecio(precio);
        libro.setStock_disponible(stock);
        return dao.crearLibro(libro);
    }

    public List<Libros> listarLibros() {
        return dao.listarLibros();
    }

    public boolean actualizarLibro(int id, String titulo, String autor, String genero, BigDecimal precio, int stock) {
        Libros libro = new Libros(id, titulo, autor, genero, precio, stock, null);
        return dao.modificarLibro(libro);
    }

    public boolean eliminarLibro(int id) {
        return dao.eliminarLibro(id);
    }
}