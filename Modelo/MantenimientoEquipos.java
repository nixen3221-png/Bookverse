package Modelo;

public class MantenimientoEquipos {
	private int id;
	private int equipo_id;
	private String tipo_mantenimiento;
	private String descripcion;
	private String tecnico;
	private String estado_software;
	private java.util.Date fecha_mantenimiento;

	public MantenimientoEquipos() {}

	public MantenimientoEquipos(int id, int equipo_id, String tipo_mantenimiento, String descripcion, String tecnico, String estado_software, java.util.Date fecha_mantenimiento) {
		this.id = id;
		this.equipo_id = equipo_id;
		this.tipo_mantenimiento = tipo_mantenimiento;
		this.descripcion = descripcion;
		this.tecnico = tecnico;
		this.estado_software = estado_software;
		this.fecha_mantenimiento = fecha_mantenimiento;
	}

	public int getIdMantenimiento() { return id; }
	public void setIdMantenimiento(int id) { this.id = id; }

	public int getEquipo_id() { return equipo_id; }
	public void setEquipo_id(int equipo_id) { this.equipo_id = equipo_id; }

	public String getTipo_mantenimiento() { return tipo_mantenimiento; }
	public void setTipo_mantenimiento(String tipo_mantenimiento) { this.tipo_mantenimiento = tipo_mantenimiento; }

	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

	public String getTecnico() { return tecnico; }
	public void setTecnico(String tecnico) { this.tecnico = tecnico; }

	public String getEstado_software() { return estado_software; }
	public void setEstado_software(String estado_software) { this.estado_software = estado_software; }

	public java.util.Date getFecha_mantenimiento() { return fecha_mantenimiento; }
	public void setFecha_mantenimiento(java.util.Date fecha_mantenimiento) { this.fecha_mantenimiento = fecha_mantenimiento; }
}
