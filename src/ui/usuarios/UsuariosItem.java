package ui.usuarios;

import ui.usuarios.controlador.UsuarioDTO;
import ui.usuarios.model.Usuario;
import ui.usuarios.controlador.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class UsuariosItem {
    private JPanel pacienteItem;
    private JLabel nombreUsuario;
    private JLabel edadUsuario;
    private JLabel icon;
    private JLabel dniUsuario;
    private JLabel direccionUsuario;
    private JLabel rolUsuario;
    private JLabel emailUsuario;

    public UsuariosItem() {

    }

    public Component getPanel() {
        return pacienteItem;
    }

    public void setComponents(UsuarioDTO usuario) {
        nombreUsuario.setText(UsuarioController.getNombre(usuario));
        rolUsuario.setText(UsuarioController.getRol(usuario).name());
        dniUsuario.setText(UsuarioController.getDni(usuario));
        direccionUsuario.setText(UsuarioController.getDomicilio(usuario));
        edadUsuario.setText(UsuarioController.getEdad(usuario));
        emailUsuario.setText(UsuarioController.getMail(usuario));
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/usuarios.png"));
    }
}
