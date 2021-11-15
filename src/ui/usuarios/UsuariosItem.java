package ui.usuarios;

import ui.usuarios.model.Usuario;

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

    public void setComponents(Usuario usuario) {
        nombreUsuario.setText(usuario.getNombre());
        rolUsuario.setText(usuario.getRol().name());
        dniUsuario.setText(String.valueOf(usuario.getDni()));
        direccionUsuario.setText(usuario.getDomicilio());
        edadUsuario.setText(usuario.getEdad() + " años");
        emailUsuario.setText(usuario.getMail());
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/usuarios.png"));
    }
}
