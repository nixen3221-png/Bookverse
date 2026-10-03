package Modelo;

public class PedidosProveedores {
	private int id;
	private int libro_id;
	private int proveedor_id;
	private int cantidad;
	private String estado_pedido;
	private java.util.Date fecha_pedido;

	public PedidosProveedores() {}

	public PedidosProveedores(int id, int libro_id, int proveedor_id, int cantidad, String estado_pedido, java.util.Date fecha_pedido) {
		this.id = id;
		this.libro_id = libro_id;
		this.proveedor_id = proveedor_id;
		this.cantidad = cantidad;
		this.estado_pedido = estado_pedido;
		this.fecha_pedido = fecha_pedido;
	}

	public int getIdPedido() { return id; }
	public void setIdPedido(int id) { this.id = id; }

	public int getLibro_id() { return libro_id; }
	public void setLibro_id(int libro_id) { this.libro_id = libro_id; }

	public int getProveedor_id() { return proveedor_id; }
	public void setProveedor_id(int proveedor_id) { this.proveedor_id = proveedor_id; }

	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }

	public String getEstado_pedido() { return estado_pedido; }
	public void setEstado_pedido(String estado_pedido) { this.estado_pedido = estado_pedido; }

	public java.util.Date getFecha_pedido() { return fecha_pedido; }
	public void setFecha_pedido(java.util.Date fecha_pedido) { this.fecha_pedido = fecha_pedido; }
}
