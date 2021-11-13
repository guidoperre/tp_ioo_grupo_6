package ui.pacientes;

import models.SexoEnum;
import ui.pacientes.models.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgregarPaciente {
    private final JFrame frame = new JFrame("Agregar Paciente");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField textField1;
    private JButton addButton;
    private JComboBox<SexoEnum> comboBox1;
    private JButton deleteButton;

    private Paciente paciente = null;

    public AgregarPaciente() {
        init();
    }

    public AgregarPaciente(Paciente paciente) {
        this.paciente = paciente;
        init();
        title.setText("Editar paciente");
        addButton.setText("Editar");
        deleteButton.setVisible(true);
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
