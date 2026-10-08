package Controlador;

import Dao.PedidosProveedoresConexion;
import Modelo.PedidosProveedores;
import java.util.List;

public class PedidosProveedoresControlador {
    private PedidosProveedoresConexion dao;

    public PedidosProveedoresControlador() {
        this.dao = new PedidosProveedoresConexion();
    }

    public boolean registrarPedido(int libroId, int proveedorId, int cantidad) {
        PedidosProveedores pedido = new PedidosProveedores();
        pedido.setLibro_id(libroId);
        pedido.setProveedor_id(proveedorId);
        pedido.setCantidad(cantidad);
        return dao.crearPedido(pedido);
    }

    public List<PedidosProveedores> listarPedidos() {
        return dao.listarPedidos();
    }

    public boolean actualizarPedido(int id, int libroId, int proveedorId, int cantidad, String estado) {
        PedidosProveedores pedido = new PedidosProveedores(id, libroId, proveedorId, cantidad, estado, null);
        return dao.modificarPedido(pedido);
    }

    public boolean eliminarPedido(int id) {
        return dao.eliminarPedido(id);
    }
}