package ui.home;

import ui.login.Login;
import ui.pacientes.Pacientes;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Pantalla principal de medicina binaria
 */
public class Home {
    private final JFrame frame = new JFrame("Home");

    private JLabel exitButton;
    private JPanel panel;
    private JPanel topBar;
    private JPanel bottomBar;
    private JLabel practicaLogo;
    private JLabel sucursalesLogo;
    private JLabel pacienteLogo;
    private JLabel peticionesLogo;
    private JLabel resultadosLogo;
    private JLabel usuariosLogo;

    public Home() {
        init();
    }

    // Inicializa la ventana
    private void init() {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        sucursalesLogo = new JLabel(new ImageIcon("resources/home.png"));
        practicaLogo = new JLabel(new ImageIcon("resources/practicas.png"));
        peticionesLogo = new JLabel(new ImageIcon("resources/peticiones.png"));
        resultadosLogo = new JLabel(new ImageIcon("resources/resultados.png"));
        usuariosLogo = new JLabel(new ImageIcon("resources/usuarios.png"));

        // Evento click botonSalir
        setLogout();
        setPacientes();
    }

    private void setLogout() {
        exitButton = new JLabel(new ImageIcon("resources/salir.png"));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                new Login();
            }
        });
    }

    private void setPacientes() {
        pacienteLogo = new JLabel(new ImageIcon("resources/pacientes.png"));
        pacienteLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                new Pacientes();
            }
        });
    }
}
