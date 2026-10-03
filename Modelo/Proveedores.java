package Modelo;

public class Proveedores {
	private int id;
	private String nombre_empresa;
	private String contacto;
	private String telefono;

	public Proveedores() {}

	public Proveedores(int id, String nombre_empresa, String contacto, String telefono) {
		this.id = id;
		this.nombre_empresa = nombre_empresa;
		this.contacto = contacto;
		this.telefono = telefono;
	}

	public int getIdProveedor() { return id; }
	public void setIdProveedor(int id) { this.id = id; }

	public String getNombre_empresa() { return nombre_empresa; }
	public void setNombre_empresa(String nombre_empresa) { this.nombre_empresa = nombre_empresa; }

	public String getContacto() { return contacto; }
	public void setContacto(String contacto) { this.contacto = contacto; }

	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
}
