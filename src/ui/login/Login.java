package ui.login;

import javax.swing.*;

public class Login {
    private JPanel panel;
    private JLabel imageLabel;
    private JTextField usernameEt;
    private JPasswordField passwordEt;
    private JButton iniciarBtn;

    public Login() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        frame.setContentPane(new Login().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        imageLabel = new JLabel(new ImageIcon("resources/icon.png"));
    }
}
