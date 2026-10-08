package Vista;

import Controlador.EquiposOficinaControlador;
import Modelo.EquiposOficina;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EquiposVista extends JPanel {
    private final EquiposOficinaControlador equipoController;
    private JTextField txtIdOculto, txtCodigo, txtTipo, txtUbicacion;
    private JComboBox<String> cbEstado;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public EquiposVista() {
        equipoController = new EquiposOficinaControlador();
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Registro de Equipos"));

        txtIdOculto = new JTextField();
        txtCodigo = new JTextField(20);
        txtTipo = new JTextField(20);
        txtUbicacion = new JTextField(20);
        cbEstado = new JComboBox<>(new String[]{"Operativo", "En Mantenimiento", "Fuera de Servicio"});

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Codigo"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Tipo"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Ubicacion"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtUbicacion, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Estado"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(cbEstado, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelBotones.setBackground(Color.WHITE);
        JButton btnCrear = new JButton("Registrar");
        JButton btnModificar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; form.add(panelBotones, gbc);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Codigo", "Tipo", "Ubicacion", "Estado"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 250));

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtIdOculto.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtCodigo.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtTipo.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtUbicacion.setText(modeloTabla.getValueAt(fila, 3).toString());
                cbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
            }
        });

        btnCrear.addActionListener(e -> {
            boolean ok = equipoController.registrarEquipo(txtCodigo.getText(), txtTipo.getText(), txtUbicacion.getText());
            if (ok) {
                JOptionPane.showMessageDialog(this, "Equipo registrado exitosamente.");
                limpiar();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            int id = Integer.parseInt(txtIdOculto.getText());
            boolean ok = equipoController.actualizarEquipo(id, txtCodigo.getText(), txtTipo.getText(), txtUbicacion.getText(), cbEstado.getSelectedItem().toString());
            if (ok) {
                JOptionPane.showMessageDialog(this, "Equipo actualizado exitosamente.");
                limpiar();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            int id = Integer.parseInt(txtIdOculto.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Desea eliminar este equipo?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = equipoController.eliminarEquipo(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Equipo eliminado.");
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
        List<EquiposOficina> lista = equipoController.listarEquipos();
        for (EquiposOficina eq : lista) {
            modeloTabla.addRow(new Object[]{eq.getIdEquipo(), eq.getCodigo_equipo(), eq.getTipo_equipo(), eq.getUbicacion(), eq.getEstado_operativo()});
        }
    }

    private void limpiar() {
        txtIdOculto.setText("");
        txtCodigo.setText("");
        txtTipo.setText("");
        txtUbicacion.setText("");
        cbEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }
}