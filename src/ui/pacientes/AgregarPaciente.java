package ui.pacientes;

import models.SexoEnum;
import ui.pacientes.models.Paciente;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgregarPaciente {
    private final JFrame frame = new JFrame("Agregar Paciente");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField textField1;
    private JButton button1;
    private JComboBox<SexoEnum> comboBox1;

    public AgregarPaciente() {
        init();
    }

    public AgregarPaciente(Paciente paciente) {
        init();
        title.setText("Editar paciente");
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
        addBackListener();
        setSexo();
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Pacientes();
                frame.setVisible(false);
            }
        });
    }

    private void setSexo() {
        comboBox1 = new JComboBox<>();
        DefaultComboBoxModel<SexoEnum> sexos = new DefaultComboBoxModel<>();
        sexos.addElement(SexoEnum.MASCULINO);
        sexos.addElement(SexoEnum.FEMENINO);
        comboBox1.setModel(sexos);
    }
}
