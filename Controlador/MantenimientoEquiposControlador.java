package Controlador;

import Dao.MantenimientoEquiposConexion;
import Modelo.MantenimientoEquipos;
import java.util.List;

public class MantenimientoEquiposControlador {
    private MantenimientoEquiposConexion dao;

    public MantenimientoEquiposControlador() {
        this.dao = new MantenimientoEquiposConexion();
    }

    public boolean registrarMantenimiento(int equipoId, String tipoMantenimiento, String descripcion, String tecnico, String estadoSoftware) {
        MantenimientoEquipos mant = new MantenimientoEquipos();
        mant.setEquipo_id(equipoId);
        mant.setTipo_mantenimiento(tipoMantenimiento);
        mant.setDescripcion(descripcion);
        mant.setTecnico(tecnico);
        mant.setEstado_software(estadoSoftware);
        return dao.crearMantenimiento(mant);
    }

    public List<MantenimientoEquipos> obtenerHistorialMantenimiento() {
        return dao.listarMantenimientos();
    }

    public boolean actualizarEstadoSoftware(int idMantenimiento, int equipoId, String tipoMantenimiento, String descripcion, String tecnico, String nuevoEstadoSoftware) {
        MantenimientoEquipos mant = new MantenimientoEquipos(idMantenimiento, equipoId, tipoMantenimiento, descripcion, tecnico, nuevoEstadoSoftware, null);
        return dao.modificarMantenimiento(mant);
    }

    public boolean eliminarMantenimiento(int id) {
        return dao.eliminarMantenimiento(id);
    }
}