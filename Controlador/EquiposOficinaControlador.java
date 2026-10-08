package Controlador;

import Dao.EquiposOficinaConexion;
import Modelo.EquiposOficina;
import java.util.List;

public class EquiposOficinaControlador {
    private EquiposOficinaConexion dao;

    public EquiposOficinaControlador() {
        this.dao = new EquiposOficinaConexion();
    }

    public boolean registrarEquipo(String codigo, String tipo, String ubicacion) {
        EquiposOficina equipo = new EquiposOficina();
        equipo.setCodigo_equipo(codigo);
        equipo.setTipo_equipo(tipo);
        equipo.setUbicacion(ubicacion);
        return dao.crearEquipo(equipo);
    }

    public List<EquiposOficina> listarEquipos() {
        return dao.listarEquipos();
    }

    public boolean actualizarEquipo(int id, String codigo, String tipo, String ubicacion, String estado) {
        EquiposOficina equipo = new EquiposOficina(id, codigo, tipo, ubicacion, estado);
        return dao.modificarEquipo(equipo);
    }

    public boolean eliminarEquipo(int id) {
        return dao.eliminarEquipo(id);
    }
}