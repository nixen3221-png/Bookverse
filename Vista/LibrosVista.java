package Vista;

import Controlador.LibrosControlador;
import Modelo.Libros;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibrosVista extends JPanel {
    private final LibrosControlador libroController;
    private JTextField txtIdOculto, txtTitulo, txtAutor, txtGenero, txtPrecio, txtStock;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public LibrosVista() {
        libroController = new LibrosControlador();
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Gestion de Libros"));

        txtIdOculto = new JTextField();
        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);
        txtGenero = new JTextField(20);
        txtPrecio = new JTextField(20);
        txtStock = new JTextField(20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Titulo"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtTitulo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Autor"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtAutor, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Genero"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtGenero, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Precio"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; form.add(new JLabel("Stock"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL; form.add(txtStock, gbc);

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

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Titulo", "Autor", "Genero", "Precio", "Stock", "Fecha"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 250));

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtIdOculto.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtTitulo.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtAutor.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtGenero.setText(modeloTabla.getValueAt(fila, 3).toString());
                txtPrecio.setText(modeloTabla.getValueAt(fila, 4).toString());
                txtStock.setText(modeloTabla.getValueAt(fila, 5).toString());
            }
        });

        btnRegistrar.addActionListener(e -> {
            try {
                BigDecimal precio = new BigDecimal(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());
                boolean ok = libroController.registrarLibro(txtTitulo.getText().trim(), txtAutor.getText().trim(), txtGenero.getText().trim(), precio, stock);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Libro registrado con exito.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y stock deben ser numericos validos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            try {
                int id = Integer.parseInt(txtIdOculto.getText());
                BigDecimal precio = new BigDecimal(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());
                boolean ok = libroController.actualizarLibro(id, txtTitulo.getText().trim(), txtAutor.getText().trim(), txtGenero.getText().trim(), precio, stock);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Libro actualizado con exito.");
                    limpiar();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y stock deben ser numericos validos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtIdOculto.getText().isEmpty()) return;
            int id = Integer.parseInt(txtIdOculto.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Desea eliminar este libro?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = libroController.eliminarLibro(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Libro eliminado.");
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
        List<Libros> lista = libroController.listarLibros();
        for (Libros l : lista) {
            modeloTabla.addRow(new Object[]{l.getIdLibro(), l.getTitulo(), l.getAutor(), l.getGenero(), l.getPrecio(), l.getStock_disponible(), l.getFecha_registro()});
        }
    }

    private void limpiar() {
        txtIdOculto.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        tabla.clearSelection();
    }
}