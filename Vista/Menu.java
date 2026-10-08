package Vista;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    public Menu() {
        setTitle("BookVerse - Sistema de Gestion y Mantenimiento TI");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Equipos de Oficina", new EquiposVista());
        pestanas.addTab("Mantenimiento", new MantenimientoVista());
        pestanas.addTab("Libros", new LibrosVista());
        pestanas.addTab("Proveedores", new ProveedoresVista());
        pestanas.addTab("Pedidos", new PedidosVista());

        panelPrincipal.add(pestanas, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }
}