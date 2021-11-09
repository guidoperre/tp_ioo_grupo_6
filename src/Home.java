import javax.swing.*;

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

    public Home() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        frame.setContentPane(new Home().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
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
}
