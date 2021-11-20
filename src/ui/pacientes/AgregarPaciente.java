package ui.pacientes;

import app.Application;
import models.Sexo;
import navigation.Screen;
import ui.pacientes.controlador.PacienteControler;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarPaciente implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField nombreTextField;
    private JButton addButton;
    private JComboBox<Sexo> sexoSpinner;
    private JButton deleteButton;
    private JTextField dniTextField;
    private JTextField domicilioTextField;
    private JTextField edadTextField;
    private JTextField emailTextField;

    private PacienteDTO paciente;

    final private PacienteControler controller;

    public AgregarPaciente() {
        controller = new PacienteControler();
        addListener();
    }

    public AgregarPaciente(PacienteDTO paciente) {
        controller = new PacienteControler();
        this.paciente = paciente;
        addListener();
        initPaciente();
    }

    @Override
    public JPanel getPanel() {
        return panel;
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
            JOptionPane.showMessageDialog(panel, "DEBE COMPLETAR TODOS LOS CAMPOS", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new Pacientes());
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                if (paciente != null) {
                    paciente.setNombre(nombreTextField.getText());
                    paciente.setDni(Integer.parseInt(dniTextField.getText()));
                    paciente.setDomicilio(domicilioTextField.getText());
                    paciente.setSexo((Sexo) sexoSpinner.getSelectedItem());
                    paciente.setEdad(Integer.parseInt(edadTextField.getText()));
                    paciente.setMail(emailTextField.getText());
                    PacientesTable.modifyPaciente(paciente);
                } else {
                    PacientesTable.addPaciente(new PacienteDTO(
                            nombreTextField.getText(),
                            Integer.parseInt(dniTextField.getText()),
                            domicilioTextField.getText(),
                            emailTextField.getText(),
                            Integer.parseInt(edadTextField.getText()),
                            (Sexo) sexoSpinner.getSelectedItem()

                    ));
                }
                Application.manager.navigateTo(new Pacientes());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            if (controller.getPeticionesFinalizadas(paciente).size() > 0) {
                JOptionPane.showMessageDialog(panel, "ESTE PACIENTE NO PUEDE ELMINARSE PORQUE POSEE PETICIONES CON RESULTADOS FINALIZADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                PacientesTable.deletePaciente(paciente);
            }
            Application.manager.navigateTo(new Pacientes());
        });
    }

    private void setSexo() {
        sexoSpinner = new JComboBox<>();
        List<Sexo> sexosList = List.of(Sexo.values());
        DefaultComboBoxModel<Sexo> sexos = new DefaultComboBoxModel<>();
        sexos.addAll(sexosList);
        sexoSpinner.setModel(sexos);
    }
}
