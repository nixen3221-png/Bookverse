package Vista;

import Controlador.ProveedoresControlador;
import Modelo.Proveedores;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProveedoresVista extends JPanel {
    private final ProveedoresControlador proveedorController;
    private JTextField txtIdOculto, txtEmpresa, txtContacto, txtTelefono;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public ProveedoresVista() {
        proveedorController = new ProveedoresControlador();
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Gestion de Proveedores"));

        txtIdOculto = new JTextField();
        txtEmpresa = new JTextField(20);
        txtContacto = new JTextField(20);
        txtTelefono = new JTextField(20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Empresa"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtEmpresa, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Contacto"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtContacto, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Telefono"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtTelefono, gbc);

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

        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; form.add(panelBotones, gbc);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Empresa", "Contacto", "Telefono"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 250));

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtIdOculto.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtEmpresa.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtContacto.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtTelefono.setText(modeloTabla.getValueAt(fila, 3).toString());
            }
        });

        btnRegistrar.addActionListener(e -> {
            boolean ok = proveedorController.registrarProveedor(txtEmpresa.getText().trim(), txtContacto.getText().trim(), txtTelefono.getText().trim());
            if (ok) {
                JOptionPane.showMessageDialog(this, "Proveedor registrado con exito.");
                limpiar();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            int id = Integer.parseInt(txtIdOculto.getText());
            boolean ok = proveedorController.actualizarProveedor(id, txtEmpresa.getText().trim(), txtContacto.getText().trim(), txtTelefono.getText().trim());
            if (ok) {
                JOptionPane.showMessageDialog(this, "Proveedor actualizado con exito.");
                limpiar();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            int id = Integer.parseInt(txtIdOculto.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Desea eliminar este proveedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = proveedorController.eliminarProveedor(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Proveedor eliminado.");
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
        List<Proveedores> lista = proveedorController.listarProveedores();
        for (Proveedores p : lista) {
            modeloTabla.addRow(new Object[]{p.getIdProveedor(), p.getNombre_empresa(), p.getContacto(), p.getTelefono()});
        }
    }

    private void limpiar() {
        txtIdOculto.setText("");
        txtEmpresa.setText("");
        txtContacto.setText("");
        txtTelefono.setText("");
        tabla.clearSelection();
    }
}