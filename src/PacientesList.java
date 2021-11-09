import javax.swing.*;

public class PacientesList {

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;

    public PacientesList() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        frame.setContentPane(new PacientesList().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
    }
}
