package ui.login;

import app.Application;
import navigation.Screen;
import ui.usuarios.controlador.UsuarioDTO;
import ui.usuarios.model.Usuario;
import ui.home.Home;
import ui.usuarios.model.UsuariosTable;

import javax.swing.*;

public class Login implements Screen {

    private JPanel panel;
    private JLabel imageLabel;
    private JTextField usernameEt;
    private JPasswordField passwordEt;
    private JButton iniciarBtn;
    private JLabel errorLbl;

    public Login() {
        setLogin();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        imageLabel = new JLabel(new ImageIcon("resources/icon.png"));
    }

    private void setLogin() {
        iniciarBtn.addActionListener(e -> {
            errorLbl.setText("");
            String username = usernameEt.getText();
            String password = String.valueOf(passwordEt.getPassword());

            if (username.length() < 1 || password.length() < 1) {
                errorLbl.setText("Por favor complete todos los datos");
            } else {
                UsuarioDTO user = Usuario.login(username, password);
                if (user == null) {
                    errorLbl.setText("Credenciales incorrectas");
                } else {
                    UsuariosTable.usuario = new Usuario(
                            user.getNombre(),
                            user.getDni(),
                            user.getDomicilio(),
                            user.getMail(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getFechaNacimiento(),
                            user.getRol()
                    );
                    Application.manager.navigateTo(new Home());
                }
            }
        });
    }
}
