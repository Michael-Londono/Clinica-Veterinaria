package vista;

import javax.swing.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Clínica Veterinaria ABC");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnPersonas = new JButton("Gestionar Personas");
        btnPersonas.setBounds(50, 30, 200, 40);
        add(btnPersonas);

        JButton btnMascotas = new JButton("Gestionar Mascotas");
        btnMascotas.setBounds(50, 90, 200, 40);
        add(btnMascotas);

        btnPersonas.addActionListener(e -> new VentanaPersonas());
        btnMascotas.addActionListener(e -> new VentanaMascotas());

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
