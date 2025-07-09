package vista;

import dao.MascotaDAO;
import dao.PersonaDAO;
import modelo.Mascota;
import modelo.Persona;

import javax.swing.*;

public class VentanaMascotas extends JFrame {
    private JTextField txtId, txtNombre, txtEspecie, txtRaza, txtDueno;
    private JTextArea txtResultado;
    private MascotaDAO dao = new MascotaDAO();
    private PersonaDAO personaDAO = new PersonaDAO();

    public VentanaMascotas() {
        setTitle("Gestión de Mascotas");
        setSize(420, 480);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 20, 200, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 200, 25);
        add(txtNombre);

        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(20, 100, 100, 25);
        add(lblEspecie);

        txtEspecie = new JTextField();
        txtEspecie.setBounds(120, 100, 200, 25);
        add(txtEspecie);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setBounds(20, 140, 100, 25);
        add(lblRaza);

        txtRaza = new JTextField();
        txtRaza.setBounds(120, 140, 200, 25);
        add(txtRaza);

        JLabel lblDueno = new JLabel("Doc. Dueño:");
        lblDueno.setBounds(20, 180, 100, 25);
        add(lblDueno);

        txtDueno = new JTextField();
        txtDueno.setBounds(120, 180, 200, 25);
        add(txtDueno);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(20, 220, 100, 25);
        add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(130, 220, 100, 25);
        add(btnConsultar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(240, 220, 120, 25);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 260, 100, 25);
        add(btnEliminar);

        JButton btnListar = new JButton("Consultar Lista");
        btnListar.setBounds(130, 260, 230, 25);
        add(btnListar);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(20, 300, 370, 130);
        add(scroll);

        btnRegistrar.addActionListener(e -> {
            String docDueno = txtDueno.getText();
            Persona dueno = personaDAO.consultar(docDueno);
            if (dueno == null) {
                txtResultado.setText("Dueño no registrado.");
                return;
            }

            Mascota m = new Mascota(txtNombre.getText(), txtEspecie.getText(), txtRaza.getText(), docDueno);
            if (dao.insertar(m)) {
                txtResultado.setText("Mascota registrada:\n" + m + "\nDueño: " + dueno.getNombre());
            } else {
                txtResultado.setText("Error al registrar mascota.");
            }
        });

        btnConsultar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Mascota m = dao.consultar(id);
                if (m != null) {
                    txtNombre.setText(m.getNombre());
                    txtEspecie.setText(m.getEspecie());
                    txtRaza.setText(m.getRaza());
                    txtDueno.setText(m.getDocumentoDueno());
                    Persona dueno = personaDAO.consultar(m.getDocumentoDueno());
                    String nombreDueno = (dueno != null) ? dueno.getNombre() : "No encontrado";
                    txtResultado.setText("Mascota encontrada:\n" + m + "\nDueño: " + nombreDueno);
                } else {
                    txtResultado.setText("Mascota no encontrada.");
                }
            } catch (Exception ex) {
                txtResultado.setText("ID inválido.");
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Mascota m = new Mascota(id, txtNombre.getText(), txtEspecie.getText(), txtRaza.getText(), txtDueno.getText());
                if (dao.actualizar(m)) {
                    txtResultado.setText("Mascota actualizada:\n" + m);
                } else {
                    txtResultado.setText("Error al actualizar mascota.");
                }
            } catch (Exception ex) {
                txtResultado.setText("ID inválido.");
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                if (dao.eliminar(id)) {
                    txtResultado.setText("Mascota eliminada correctamente.");
                } else {
                    txtResultado.setText("Mascota no encontrada.");
                }
            } catch (Exception ex) {
                txtResultado.setText("ID inválido.");
            }
        });

        btnListar.addActionListener(e -> {
            var lista = dao.listar();
            if (lista.isEmpty()) {
                txtResultado.setText("No hay mascotas registradas.");
            } else {
                txtResultado.setText("Lista de mascotas:\n");
                lista.forEach(m -> {
                    Persona p = personaDAO.consultar(m.getDocumentoDueno());
                    String nombreDueno = (p != null) ? p.getNombre() : "No registrado";
                    txtResultado.append(m.toString() + " - Dueño: " + nombreDueno + "\n");
                });
            }
        });

        setVisible(true);
    }
}
