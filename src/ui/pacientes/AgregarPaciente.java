package ui.pacientes;

import models.Rol;
import models.SexoEnum;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarPaciente {
    private final JFrame frame = new JFrame("Agregar Paciente");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField nombreTextField;
    private JButton addButton;
    private JComboBox<SexoEnum> sexoSpinner;
    private JButton deleteButton;
    private JTextField dniTextField;
    private JTextField domicilioTextField;
    private JTextField edadTextField;
    private JTextField emailTextField;

    private Paciente paciente;

    public AgregarPaciente() {
        init();
    }

    public AgregarPaciente(Paciente paciente) {
        this.paciente = paciente;
        init();
        initPaciente();
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

        addListener();
    }

    private void initPaciente() {
        title.setText("Editar paciente");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        nombreTextField.setText(paciente.getNombre());
        dniTextField.setText(String.valueOf(paciente.getDni()));
        domicilioTextField.setText(paciente.getDomicilio());
        sexoSpinner.setSelectedItem(paciente.getSexo());
        edadTextField.setText(String.valueOf(paciente.getEdad()));
        emailTextField.setText(paciente.getMail());

        deleteListener();
        addListener();
    }

    private void createUIComponents() {
        addBackListener();
        setSexo();
    }

    private Boolean checkFields() {
        String nombre = nombreTextField.getText();
        String dni = dniTextField.getText();
        String domicilio = domicilioTextField.getText();
        String edad = edadTextField.getText();
        String email = emailTextField.getText();

        if (!nombre.equals("") && !dni.equals("") && !domicilio.equals("") && !edad.equals("") && !email.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "DEBE COMPLETAR TODOS LOS CAMPOS", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Pacientes();
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                frame.dispose();
                if (paciente != null) {
                    paciente.setNombre(nombreTextField.getText());
                    paciente.setDni(Integer.parseInt(dniTextField.getText()));
                    paciente.setDomicilio(domicilioTextField.getText());
                    paciente.setSexo((SexoEnum) sexoSpinner.getSelectedItem());
                    paciente.setEdad(Integer.parseInt(edadTextField.getText()));
                    paciente.setMail(emailTextField.getText());
                    PacientesTable.modifyPaciente(paciente);
                } else {
                    PacientesTable.addPaciente(new Paciente(
                            nombreTextField.getText(),
                            Integer.parseInt(dniTextField.getText()),
                            domicilioTextField.getText(),
                            emailTextField.getText(),
                            Integer.parseInt(edadTextField.getText()),
                            (SexoEnum) sexoSpinner.getSelectedItem()

                    ));
                }

                new Pacientes();
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            frame.dispose();
            PacientesTable.deletePaciente(paciente);
            new Pacientes();
        });
    }

    private void setSexo() {
        sexoSpinner = new JComboBox<>();
        List<SexoEnum> sexosList = List.of(SexoEnum.values());
        DefaultComboBoxModel<SexoEnum> sexos = new DefaultComboBoxModel<>();
        sexos.addAll(sexosList);
        sexoSpinner.setModel(sexos);
    }
}
