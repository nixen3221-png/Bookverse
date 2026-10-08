package Vista;

import Controlador.PedidosProveedoresControlador;
import Modelo.PedidosProveedores;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PedidosVista extends JPanel {
    private final PedidosProveedoresControlador pedidoController;
    private JTextField txtIdOculto, txtLibroId, txtProveedorId, txtCantidad;
    private JComboBox<String> cbEstado;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public PedidosVista() {
        pedidoController = new PedidosProveedoresControlador();
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Gestion de Pedidos a Proveedores"));

        txtIdOculto = new JTextField();
        txtLibroId = new JTextField(20);
        txtProveedorId = new JTextField(20);
        txtCantidad = new JTextField(20);
        cbEstado = new JComboBox<>(new String[]{"Pendiente", "Completado", "Cancelado"});

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("ID Libro"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtLibroId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("ID Proveedor"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtProveedorId, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Cantidad"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Estado"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(cbEstado, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelBotones.setBackground(Color.WHITE);
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; form.add(panelBotones, gbc);

        modeloTabla = new DefaultTableModel(new String[]{"ID Pedido", "ID Libro", "ID Proveedor", "Cantidad", "Estado", "Fecha"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 250));

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtIdOculto.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtLibroId.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtProveedorId.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtCantidad.setText(modeloTabla.getValueAt(fila, 3).toString());
                cbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
            }
        });

        btnRegistrar.addActionListener(e -> {
            try {
                int libroId = Integer.parseInt(txtLibroId.getText().trim());
                int proveedorId = Integer.parseInt(txtProveedorId.getText().trim());
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                boolean ok = pedidoController.registrarPedido(libroId, proveedorId, cantidad);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Pedido registrado con exito.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de libro, ID de proveedor y cantidad deben ser numericos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            try {
                int id = Integer.parseInt(txtIdOculto.getText());
                int libroId = Integer.parseInt(txtLibroId.getText().trim());
                int proveedorId = Integer.parseInt(txtProveedorId.getText().trim());
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                String estado = cbEstado.getSelectedItem().toString();
                boolean ok = pedidoController.actualizarPedido(id, libroId, proveedorId, cantidad, estado);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Pedido actualizado con exito.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Los campos numericos son invalidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            int id = Integer.parseInt(txtIdOculto.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Desea eliminar este pedido?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = pedidoController.eliminarPedido(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Pedido eliminado.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiar());

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<PedidosProveedores> lista = pedidoController.listarPedidos();
        for (PedidosProveedores pp : lista) {
            modeloTabla.addRow(new Object[]{pp.getIdPedido(), pp.getLibro_id(), pp.getProveedor_id(), pp.getCantidad(), pp.getEstado_pedido(), pp.getFecha_pedido()});
        }
    }

    private void limpiar() {
        txtIdOculto.setText("");
        txtLibroId.setText("");
        txtProveedorId.setText("");
        txtCantidad.setText("");
        cbEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }
}