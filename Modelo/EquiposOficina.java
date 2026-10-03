package Modelo;

public class EquiposOficina {
	private int id;
	private String codigo_equipo;
	private String tipo_equipo;
	private String ubicacion;
	private String estado_operativo;

	public EquiposOficina() {}

	public EquiposOficina(int id, String codigo_equipo, String tipo_equipo, String ubicacion, String estado_operativo) {
		this.id = id;
		this.codigo_equipo = codigo_equipo;
		this.tipo_equipo = tipo_equipo;
		this.ubicacion = ubicacion;
		this.estado_operativo = estado_operativo;
	}

	public int getIdEquipo() { return id; }
	public void setIdEquipo(int id) { this.id = id; }

	public String getCodigo_equipo() { return codigo_equipo; }
	public void setCodigo_equipo(String codigo_equipo) { this.codigo_equipo = codigo_equipo; }

	public String getTipo_equipo() { return tipo_equipo; }
	public void setTipo_equipo(String tipo_equipo) { this.tipo_equipo = tipo_equipo; }

	public String getUbicacion() { return ubicacion; }
	public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

	public String getEstado_operativo() { return estado_operativo; }
	public void setEstado_operativo(String estado_operativo) { this.estado_operativo = estado_operativo; }
}
