package ui.usuarios;

import app.Application;
import models.Rol;
import navigation.Screen;
import ui.usuarios.controlador.UsuarioDTO;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
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

    private UsuarioDTO usuario;

    public AgregarUsuario() {
        addListener();
    }

    public AgregarUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        addListener();
        initUsuario();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void initUsuario() {
        title.setText("Editar usuario");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        nombreTextField.setText(UsuarioController.getNombre(usuario));
        dniTextField.setText(UsuarioController.getDni(usuario));
        domicilioTextField.setText(UsuarioController.getDomicilio(usuario));
        fechaNacimientoTextField.setText(UsuarioController.getFechaNacimiento(usuario));
        emailTextField.setText(UsuarioController.getMail(usuario));
        usuarioTextField.setText(UsuarioController.getUsername(usuario));
        contrasenaTextField.setText(UsuarioController.getPassword(usuario));
        rolSpinner.setSelectedItem(UsuarioController.getRol(usuario));

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
                !fechaNacimiento.equals("") &&
                !email.equals("") &&
                !usuario.equals("") &&
                !contrasena.equals("") &&
                rolSpinner.getSelectedItem() != null
        ) {
            return true;
        } else if (DataUtils.getFechaFromString(fechaNacimiento) == null) {
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
                if (usuario != null) {
                    UsuarioController.setNombre(usuario, nombreTextField.getText());
                    UsuarioController.setDni(usuario, Integer.parseInt(dniTextField.getText()));
                    UsuarioController.setDomicilio(usuario, domicilioTextField.getText());
                    UsuarioController.setFechaNacimiento(usuario, DataUtils.getFechaFromString(fechaNacimientoTextField.getText()));
                    UsuarioController.setMail(usuario, emailTextField.getText());
                    UsuarioController.setUsername(usuario, usuarioTextField.getText());
                    UsuarioController.setPassword(usuario, contrasenaTextField.getText());
                    UsuarioController.setRol(usuario, (Rol) rolSpinner.getSelectedItem());
                    UsuarioController.modifyUsuario(usuario);
                } else {
                    UsuarioController.addUsuario(new UsuarioDTO(
                            nombreTextField.getText(),
                            Integer.parseInt(dniTextField.getText()),
                            domicilioTextField.getText(),
                            emailTextField.getText(),
                            usuarioTextField.getText(),
                            contrasenaTextField.getText(),
                            DataUtils.getFechaFromString(fechaNacimientoTextField.getText()),
                            (Rol) rolSpinner.getSelectedItem()
                    ));
                }
                Application.manager.navigateTo(new Usuarios());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            UsuarioController.deleteUsuario(usuario);
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
