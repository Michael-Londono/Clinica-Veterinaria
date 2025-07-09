package vista;

import dao.PersonaDAO;
import modelo.Persona;

import javax.swing.*;
import java.awt.event.*;

public class VentanaPersonas extends JFrame {
    private JTextField txtDocumento, txtNombre, txtTelefono;
    private JTextArea txtResultado;
    private PersonaDAO dao = new PersonaDAO();

    public VentanaPersonas() {
        setTitle("Gestión de Personas");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblDoc = new JLabel("Documento:");
        lblDoc.setBounds(20, 20, 100, 25);
        add(lblDoc);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(120, 20, 200, 25);
        add(txtDocumento);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 200, 25);
        add(txtNombre);

        JLabel lblTel = new JLabel("Teléfono:");
        lblTel.setBounds(20, 100, 100, 25);
        add(lblTel);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 100, 200, 25);
        add(txtTelefono);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(20, 140, 100, 25);
        add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(130, 140, 100, 25);
        add(btnConsultar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(240, 140, 100, 25);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 180, 100, 25);
        add(btnEliminar);

        JButton btnListar = new JButton("Consultar Lista");
        btnListar.setBounds(130, 180, 210, 25);
        add(btnListar);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(20, 220, 340, 170);
        add(scroll);

        btnRegistrar.addActionListener(e -> {
            Persona p = new Persona(txtDocumento.getText(), txtNombre.getText(), txtTelefono.getText());
            if (dao.insertar(p)) {
                txtResultado.setText("Persona registrada:\n" + p);
            } else {
                txtResultado.setText("Error al registrar persona.");
            }
        });

        btnConsultar.addActionListener(e -> {
            Persona p = dao.consultar(txtDocumento.getText());
            if (p != null) {
                txtNombre.setText(p.getNombre());
                txtTelefono.setText(p.getTelefono());
                txtResultado.setText("Consulta exitosa:\n" + p);
            } else {
                txtResultado.setText("La persona no existe.");
            }
        });

        btnActualizar.addActionListener(e -> {
            Persona p = new Persona(txtDocumento.getText(), txtNombre.getText(), txtTelefono.getText());
            if (dao.actualizar(p)) {
                txtResultado.setText("Persona actualizada:\n" + p);
            } else {
                txtResultado.setText("Error al actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (dao.eliminar(txtDocumento.getText())) {
                txtResultado.setText("Persona eliminada correctamente.");
            } else {
                txtResultado.setText("No se encontró la persona.");
            }
        });

        btnListar.addActionListener(e -> {
            var lista = dao.listar();
            if (lista.isEmpty()) {
                txtResultado.setText("No hay personas registradas.");
            } else {
                txtResultado.setText("Lista de personas:\n");
                lista.forEach(p -> txtResultado.append(p.toString() + "\n"));
            }
        });

        setVisible(true);
    }
}
