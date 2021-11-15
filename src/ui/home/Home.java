package ui.home;

import ui.login.Login;
import ui.pacientes.Pacientes;
import ui.sucursales.Sucursales;

import javax.swing.*;
import java.awt.*;
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
    }

    private void createUIComponents() {
        practicaLogo = new JLabel(new ImageIcon("resources/practicas.png"));
        peticionesLogo = new JLabel(new ImageIcon("resources/peticiones.png"));
        resultadosLogo = new JLabel(new ImageIcon("resources/resultados.png"));
        usuariosLogo = new JLabel(new ImageIcon("resources/usuarios.png"));

        // Evento click botonSalir
        setLogout();
        setPacientes();
        setSucursales();
    }

    private void setLogout() {
        exitButton = new JLabel(new ImageIcon("resources/salir.png"));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Login();
            }
        });
    }

    private void setPacientes() {
        pacienteLogo = new JLabel(new ImageIcon("resources/pacientes.png"));
        pacienteLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Pacientes();
            }
        });
    }

    private void setSucursales() {
        sucursalesLogo = new JLabel(new ImageIcon("resources/home.png"));
        sucursalesLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Sucursales();
            }
        });
    }
}
