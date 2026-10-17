package Vista;

import Controlador.MantenimientoEquiposControlador;
import Controlador.EquiposOficinaControlador;
import Modelo.EquiposOficina;
import Modelo.MantenimientoEquipos;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MantenimientoVista extends JPanel {
    private final MantenimientoEquiposControlador mantController;
    private final EquiposOficinaControlador equiposController;
    private JTextField txtIdOculto, txtDescripcion, txtTecnico, txtEstadoSoftware;
    private JComboBox<EquiposOficina> cbEquipo;
    private JComboBox<String> cbTipoMant;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public MantenimientoVista() {
        mantController = new MantenimientoEquiposControlador();
        equiposController = new EquiposOficinaControlador();
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Control de Mantenimiento"));

        txtIdOculto = new JTextField();
        cbEquipo = new JComboBox<>();
        cbEquipo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof EquiposOficina) {
                    EquiposOficina equipo = (EquiposOficina) value;
                    setText("ID " + equipo.getIdEquipo() + " - " + equipo.getCodigo_equipo() + " (" + equipo.getTipo_equipo() + ")");
                }
                return this;
            }
        });
        actualizarEquipos();
        cbTipoMant = new JComboBox<>(new String[]{"Preventivo", "Correctivo"});
        txtDescripcion = new JTextField(20);
        txtTecnico = new JTextField(20);
        txtEstadoSoftware = new JTextField(20);
        txtEstadoSoftware.setText("Pendiente Validacion");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("ID Equipo"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(cbEquipo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Tipo Mant"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(cbTipoMant, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Descripcion"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtDescripcion, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Tecnico"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtTecnico, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Estado Software"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtEstadoSoftware, gbc);

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

        gbc.gridx = 1; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE; form.add(panelBotones, gbc);

        modeloTabla = new DefaultTableModel(new String[]{"ID Mant", "ID Equipo", "Tipo", "Descripcion", "Tecnico", "Estado Software", "Fecha"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 250));

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int filaModelo = tabla.convertRowIndexToModel(filaSeleccionada);
                txtIdOculto.setText(modeloTabla.getValueAt(filaModelo, 0).toString());
                seleccionarEquipo((Integer) modeloTabla.getValueAt(filaModelo, 1));
                cbTipoMant.setSelectedItem(modeloTabla.getValueAt(filaModelo, 2).toString());
                txtDescripcion.setText(modeloTabla.getValueAt(filaModelo, 3).toString());
                txtTecnico.setText(modeloTabla.getValueAt(filaModelo, 4).toString());
                txtEstadoSoftware.setText(modeloTabla.getValueAt(filaModelo, 5).toString());
            }
        });

        btnRegistrar.addActionListener(e -> {
            EquiposOficina equipo = (EquiposOficina) cbEquipo.getSelectedItem();
            if (equipo == null) {
                JOptionPane.showMessageDialog(this, "Registre un equipo antes de crear un mantenimiento.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            boolean ok = mantController.registrarMantenimiento(equipo.getIdEquipo(), cbTipoMant.getSelectedItem().toString(), txtDescripcion.getText().trim(), txtTecnico.getText().trim(), txtEstadoSoftware.getText().trim());
            if (ok) {
                JOptionPane.showMessageDialog(this, "Mantenimiento registrado con exito.");
                limpiar();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un mantenimiento de la tabla para actualizar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(txtIdOculto.getText().trim());
                EquiposOficina equipo = (EquiposOficina) cbEquipo.getSelectedItem();
                if (equipo == null) {
                    JOptionPane.showMessageDialog(this, "Seleccione un equipo registrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                boolean ok = mantController.actualizarEstadoSoftware(
                        id,
                        equipo.getIdEquipo(),
                        cbTipoMant.getSelectedItem().toString(),
                        txtDescripcion.getText().trim(),
                        txtTecnico.getText().trim(),
                        txtEstadoSoftware.getText().trim()
                );
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Mantenimiento actualizado con éxito.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el mantenimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID del mantenimiento seleccionado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un mantenimiento de la tabla para eliminar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(txtIdOculto.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID del mantenimiento seleccionado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Desea eliminar este mantenimiento?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = mantController.eliminarMantenimiento(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Mantenimiento eliminado.");
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
        List<MantenimientoEquipos> lista = mantController.obtenerHistorialMantenimiento();
        for (MantenimientoEquipos m : lista) {
            modeloTabla.addRow(new Object[]{m.getIdMantenimiento(), m.getEquipo_id(), m.getTipo_mantenimiento(), m.getDescripcion(), m.getTecnico(), m.getEstado_software(), m.getFecha_mantenimiento()});
        }
    }

    public void actualizarEquipos() {
        EquiposOficina equipoSeleccionado = (EquiposOficina) cbEquipo.getSelectedItem();
        int equipoIdSeleccionado = equipoSeleccionado == null ? -1 : equipoSeleccionado.getIdEquipo();
        cbEquipo.removeAllItems();
        for (EquiposOficina equipo : equiposController.listarEquipos()) {
            cbEquipo.addItem(equipo);
        }
        if (equipoIdSeleccionado >= 0) {
            seleccionarEquipo(equipoIdSeleccionado);
        }
    }

    private void seleccionarEquipo(int equipoId) {
        cbEquipo.setSelectedIndex(-1);
        for (int i = 0; i < cbEquipo.getItemCount(); i++) {
            if (cbEquipo.getItemAt(i).getIdEquipo() == equipoId) {
                cbEquipo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void limpiar() {
        txtIdOculto.setText("");
        cbEquipo.setSelectedIndex(cbEquipo.getItemCount() > 0 ? 0 : -1);
        cbTipoMant.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtTecnico.setText("");
        txtEstadoSoftware.setText("Pendiente Validacion");
        tabla.clearSelection();
    }
}