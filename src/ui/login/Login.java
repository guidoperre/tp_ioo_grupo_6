package ui.login;

import models.Usuario;
import ui.home.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Login {
    private final JFrame frame = new JFrame("Login");

    private JPanel panel;
    private JLabel imageLabel;
    private JTextField usernameEt;
    private JPasswordField passwordEt;
    private JButton iniciarBtn;
    private JLabel errorLbl;

    public Login() {
        init();
        setLogin();
    }

    // Inicializa la ventana
    private void init() {
        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();

        //Calculate the frame location
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        frame.setLocation(x, y);
        frame.setVisible(true);

        Usuario admin = new Usuario("Admin", 12345678, "Calle 123", "mail@mail.com", "admin", "admin", new Date(), Usuario.rolEnum.administrador);
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
                    new Home();
                    frame.setVisible(false);
                }
            }
        });
    }
}
