import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {

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
    private JFrame frame = new JFrame("Home");


    public Home() {
        // Inicializar
        init();

        // Evento click botonSalir
        exit();
    }

    private void createUIComponents() {
        exitButton = new JLabel(new ImageIcon("resources/salir.png"));

        pacienteLogo = new JLabel(new ImageIcon("resources/pacientes.png"));
        sucursalesLogo = new JLabel(new ImageIcon("resources/home.png"));
        practicaLogo = new JLabel(new ImageIcon("resources/practicas.png"));
        peticionesLogo = new JLabel(new ImageIcon("resources/peticiones.png"));
        resultadosLogo = new JLabel(new ImageIcon("resources/resultados.png"));
        usuariosLogo = new JLabel(new ImageIcon("resources/usuarios.png"));
    }

    private void init() {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void exit() {
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                Login login = new Login();
            }
        });
    }
}
