package ui.resultados;

import app.Application;
import models.EstadoResultado;
import navigation.Screen;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadosTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarResultado implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
    private JButton deleteButton;
    private JComboBox<Paciente> pacientesSpinner;
    private JComboBox<Peticion> peticionesSpinner;
    private JComboBox<Practica> practicasSpinner;
    private JComboBox<EstadoResultado> estadoSpinner;
    private JTextField resultadoValorTextField;

    private Peticion peticion;
    private Resultado resultado;

    public AgregarResultado() {
        addListener();
    }

    public AgregarResultado(Resultado resultado) {
        this.resultado = resultado;
        this.peticion = getPeticion();
        addListener();
        initResultado();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private Peticion getPeticion() {
        List<Peticion> peticions = PeticionesTable.getAllPeticiones();
        for (Peticion p: peticions) {
            for (Resultado resultado: p.getResultados()) {
                if (resultado.getId().equals(this.resultado.getId())) {
                    peticion = p;
                    break;
                }
            }
        }
        return null;
    }

    private void initResultado() {
        title.setText("Editar resultado");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        if (peticion != null) {
            pacientesSpinner.setSelectedItem(peticion.getPaciente());
            pacientesSpinner.setEditable(false);
            peticionesSpinner.setSelectedItem(peticion);
            peticionesSpinner.setEditable(false);
            practicasSpinner.setSelectedItem(PracticasTable.getPracticas(resultado.getCodigoPractica()));
            practicasSpinner.setEditable(false);
        }

        estadoSpinner.setSelectedItem(resultado.getEstado());
        resultadoValorTextField.setText(String.valueOf(resultado.getValor()));

        deleteListener();
    }

    private void createUIComponents() {
        addBackListener();

        setPracticasSpinner();
        setPeticionSpinner();
        setPacientesSpinner();
        setEstadoSpinner();
    }

    private Boolean checkFields() {
        String valor = resultadoValorTextField.getText();

        if (
                !valor.equals("") &&
                pacientesSpinner.getSelectedItem() != null &&
                peticionesSpinner.getSelectedItem() != null &&
                practicasSpinner.getSelectedItem() != null &&
                estadoSpinner.getSelectedItem() != null
        ) {
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
                Application.manager.navigateTo(new Resultados());
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                if (peticion != null) {

                } else {

                }
                Application.manager.navigateTo(new Resultados());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            ResultadosTable.deleteResultado(resultado);
            Application.manager.navigateTo(new Resultados());
        });
    }

    private void setPacientesSpinner() {
        pacientesSpinner = new JComboBox<>();
        List<Paciente> pacientes = PacientesTable.getAllPacientes();
        DefaultComboBoxModel<Paciente> pacientesItem = new DefaultComboBoxModel<>();
        pacientesItem.addAll(pacientes);
        pacientesSpinner.setModel(pacientesItem);

        pacientesSpinner.addItemListener(e -> {
            Paciente paciente = (Paciente) e.getItem();
            List<Peticion> peticionesPaciente = PeticionesTable.getAllPeticionesPaciente(paciente);
            DefaultComboBoxModel<Peticion> peticionesPacienteItem = new DefaultComboBoxModel<>();
            peticionesPacienteItem.addAll(peticionesPaciente);
            peticionesSpinner.setModel(peticionesPacienteItem);
            practicasSpinner.setModel(null);
        });
    }

    private void setPeticionSpinner() {
        peticionesSpinner = new JComboBox<>();
        peticionesSpinner.addItemListener(e -> {
            Peticion peticion = (Peticion) e.getItem();
            List<Practica> practicasPeticion = peticion.getPracticas();
            DefaultComboBoxModel<Practica> practicasPeticionItem = new DefaultComboBoxModel<>();
            practicasPeticionItem.addAll(practicasPeticion);
            practicasSpinner.setModel(practicasPeticionItem);
        });
    }

    private void setPracticasSpinner() {
        practicasSpinner = new JComboBox<>();
    }

    private void setEstadoSpinner() {
        estadoSpinner = new JComboBox<>();
        List<EstadoResultado> estadosList = List.of(EstadoResultado.values());
        DefaultComboBoxModel<EstadoResultado> estados = new DefaultComboBoxModel<>();
        estados.addAll(estadosList);
        estadoSpinner.setModel(estados);
    }
}
