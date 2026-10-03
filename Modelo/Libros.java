package Modelo;

public class Libros {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int precio;
    private String stock_disponible;
    private java.util.Date fecha_registro; // 1: ADMIN, 2: EMPLEADO
    private boolean activo;

    public Libros() {}

    public Libros(int id, String titulo, String autor, String genero, int precio, String stock_disponible, java.util.Date fecha_registro, boolean activo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.stock_disponible = stock_disponible;
        this.fecha_registro = fecha_registro;
        this.activo = activo;
    }

    public int getIdLibro() { return id; }
    public void setIdLibro(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }

    public String getStock_disponible() { return stock_disponible; }
    public void setStock_disponible(String stock_disponible) { this.stock_disponible = stock_disponible; }

    public java.util.Date getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(java.util.Date fecha_registro) { this.fecha_registro = fecha_registro; }
}