package ui.login;

import app.Application;
import navigation.Screen;
import ui.usuarios.model.Usuario;
import ui.home.Home;
import ui.usuarios.model.UsuariosTable;

import javax.swing.*;

public class Login implements Screen {
    private final JFrame frame = new JFrame("Login");

    private JPanel panel;
    private JLabel imageLabel;
    private JTextField usernameEt;
    private JPasswordField passwordEt;
    private JButton iniciarBtn;
    private JLabel errorLbl;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Login() {
        setLogin();
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
                Usuario user = Usuario.login(username, password);
                if (user == null) {
                    errorLbl.setText("Credenciales incorrectas");
                } else {
                    UsuariosTable.usuario = user;
                    Application.manager.navigateTo(new Home());
                }
            }
        });
    }
}
