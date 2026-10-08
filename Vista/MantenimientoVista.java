package Vista;

import Controlador.MantenimientoEquiposControlador;
import Modelo.MantenimientoEquipos;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MantenimientoVista extends JPanel {
    private final MantenimientoEquiposControlador mantController;
    private JTextField txtIdOculto, txtEquipoId, txtDescripcion, txtTecnico, txtEstadoSoftware;
    private JComboBox<String> cbTipoMant;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public MantenimientoVista() {
        mantController = new MantenimientoEquiposControlador();
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Control de Mantenimiento"));

        txtIdOculto = new JTextField();
        txtEquipoId = new JTextField(20);
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
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtEquipoId, gbc);

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
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 1; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE; form.add(panelBotones, gbc);

        modeloTabla = new DefaultTableModel(new String[]{"ID Mant", "ID Equipo", "Tipo", "Descripcion", "Tecnico", "Estado Software", "Fecha"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 250));

        btnRegistrar.addActionListener(e -> {
            try {
                int equipoId = Integer.parseInt(txtEquipoId.getText().trim());
                boolean ok = mantController.registrarMantenimiento(equipoId, cbTipoMant.getSelectedItem().toString(), txtDescripcion.getText().trim(), txtTecnico.getText().trim(), txtEstadoSoftware.getText().trim());
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Mantenimiento registrado con exito.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID del equipo debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void limpiar() {
        txtIdOculto.setText("");
        txtEquipoId.setText("");
        cbTipoMant.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtTecnico.setText("");
        txtEstadoSoftware.setText("Pendiente Validacion");
        tabla.clearSelection();
    }
}