package Controlador;

import Dao.ProveedoresConexion;
import Modelo.Proveedores;
import java.util.List;

public class ProveedoresControlador {
    private ProveedoresConexion dao;

    public ProveedoresControlador() {
        this.dao = new ProveedoresConexion();
    }

    public boolean registrarProveedor(String empresa, String contacto, String telefono) {
        Proveedores prov = new Proveedores();
        prov.setNombre_empresa(empresa);
        prov.setContacto(contacto);
        prov.setTelefono(telefono);
        return dao.crearProveedor(prov);
    }

    public List<Proveedores> listarProveedores() {
        return dao.listarProveedores();
    }

    public boolean actualizarProveedor(int id, String empresa, String contacto, String telefono) {
        Proveedores prov = new Proveedores(id, empresa, contacto, telefono);
        return dao.modificarProveedor(prov);
    }

    public boolean eliminarProveedor(int id) {
        return dao.eliminarProveedor(id);
    }
}