package ui.usuarios;

import app.Application;
import models.Rol;
import navigation.Screen;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuarioDTO;
import ui.usuarios.controlador.UsuarioController;
import utils.DataUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarUsuario implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField nombreTextField;
    private JButton addButton;
    private JComboBox<Rol> rolSpinner;
    private JButton deleteButton;
    private JTextField dniTextField;
    private JTextField domicilioTextField;
    private JTextField fechaNacimientoTextField;
    private JTextField emailTextField;
    private JTextField usuarioTextField;
    private JTextField contrasenaTextField;

    private final UsuarioController controller;

    public AgregarUsuario(UsuarioController controller) {
        this.controller = controller;
        addListener();
        initUsuario();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void initUsuario() {
        UsuarioDTO usuario = controller.getUsuario();

        if (usuario != null) {
            title.setText("Editar usuario");
            addButton.setText("Editar");
            deleteButton.setVisible(true);
            nombreTextField.setText(usuario.getNombre());
            dniTextField.setText(String.valueOf(usuario.getDni()));
            domicilioTextField.setText(usuario.getDomicilio());
            fechaNacimientoTextField.setText(DataUtils.getFechaFromDate(usuario.getFechaNacimiento()));
            emailTextField.setText(usuario.getMail());
            usuarioTextField.setText(usuario.getUsername());
            contrasenaTextField.setText(usuario.getPassword());
            rolSpinner.setSelectedItem(usuario.getRol());
        } else {
            title.setText("Agregar usuario");
            addButton.setText("Agregar");
            deleteButton.setVisible(false);
        }

        deleteListener();
    }

    private void createUIComponents() {
        addBackListener();
        setRol();
    }

    private Boolean checkFields() {
        String nombre = nombreTextField.getText();
        String dni = dniTextField.getText();
        String direccion = domicilioTextField.getText();
        String fechaNacimiento = fechaNacimientoTextField.getText();
        String email = emailTextField.getText();
        String usuario = usuarioTextField.getText();
        String contrasena = contrasenaTextField.getText();

        if (
                !nombre.equals("") &&
                !dni.equals("") &&
                !direccion.equals("") &&
                DataUtils.getFechaFromString(fechaNacimiento) != null &&
                !email.equals("") &&
                !usuario.equals("") &&
                !contrasena.equals("") &&
                rolSpinner.getSelectedItem() != null
        ) {
            return true;
        } else if (!fechaNacimiento.equals("") && DataUtils.getFechaFromString(fechaNacimiento) == null) {
            JOptionPane.showMessageDialog(panel, "EL FORMATO DE FECHA DEBE SER DD/MM/AAAA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            JOptionPane.showMessageDialog(panel, "DEBE COMPLETAR TODOS LOS CAMPOS", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new Usuarios());
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                controller.addUsuario(
                        nombreTextField.getText(),
                        Integer.parseInt(dniTextField.getText()),
                        domicilioTextField.getText(),
                        DataUtils.getFechaFromString(fechaNacimientoTextField.getText()),
                        emailTextField.getText(),
                        usuarioTextField.getText(),
                        contrasenaTextField.getText(),
                        (Rol) rolSpinner.getSelectedItem()
                );
                Application.manager.navigateTo(new Usuarios());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            controller.deleteUsuario();
            Application.manager.navigateTo(new Usuarios());
        });
    }

    private void setRol() {
        rolSpinner = new JComboBox<>();
        List<Rol> roles = List.of(Rol.values());
        DefaultComboBoxModel<Rol> rolItems = new DefaultComboBoxModel<>();
        rolItems.addAll(roles);
        rolSpinner.setModel(rolItems);
    }
}
