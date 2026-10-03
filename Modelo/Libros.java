package Modelo;

public class Libros {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private java.math.BigDecimal precio;
    private int stock_disponible;
    private java.util.Date fecha_registro;

    public Libros() {}

    public Libros(int id, String titulo, String autor, String genero, java.math.BigDecimal precio, int stock_disponible, java.util.Date fecha_registro) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.stock_disponible = stock_disponible;
        this.fecha_registro = fecha_registro;
    }

    public int getIdLibro() { return id; }
    public void setIdLibro(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public java.math.BigDecimal getPrecio() { return precio; }
    public void setPrecio(java.math.BigDecimal precio) { this.precio = precio; }

    public int getStock_disponible() { return stock_disponible; }
    public void setStock_disponible(int stock_disponible) { this.stock_disponible = stock_disponible; }

    public java.util.Date getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(java.util.Date fecha_registro) { this.fecha_registro = fecha_registro; }
}