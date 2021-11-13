package ui.pacientes;

import models.SexoEnum;

import javax.swing.*;

public class AgregarPaciente {
    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField textField1;
    private JButton button1;
    private JComboBox<SexoEnum> comboBox1;

    public AgregarPaciente() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        frame.setContentPane(new AgregarPaciente().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));

        setSexo();
    }

    private void setSexo() {
        comboBox1 = new JComboBox<>();
        DefaultComboBoxModel<SexoEnum> sexos = new DefaultComboBoxModel<>();
        sexos.addElement(SexoEnum.MASCULINO);
        sexos.addElement(SexoEnum.FEMENINO);
        comboBox1.setModel(sexos);
    }
}
