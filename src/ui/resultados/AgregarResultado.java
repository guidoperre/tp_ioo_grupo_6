package ui.resultados;

import app.Application;
import models.EstadoResultado;
import navigation.Screen;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.controlador.PeticionControler;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.resultados.model.Resultado;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AgregarResultado implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
    private JButton deleteButton;
    private JComboBox<PacienteDTO> pacientesSpinner;
    private JComboBox<Peticion> peticionesSpinner;
    private JComboBox<Practica> practicasSpinner;
    private JComboBox<EstadoResultado> estadoSpinner;
    private JTextField resultadoValorTextField;

    private Peticion peticion;
    private final Resultado resultado;

    public AgregarResultado() {
        this.resultado = new Resultado();
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
        List<Peticion> peticions = PeticionControler.getAllPeticiones();
        for (Peticion p: peticions) {
            for (Resultado resultado: p.getResultados()) {
                if (resultado.getId().equals(this.resultado.getId())) {
                    return p;
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
            pacientesSpinner.setEnabled(false);
            peticionesSpinner.setSelectedItem(peticion);
            peticionesSpinner.setEnabled(false);
            practicasSpinner.setSelectedItem(PracticasTable.getPracticas(resultado.getCodigoPractica()));
            practicasSpinner.setEnabled(false);
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
                Resultado resultadoViejo = resultado;
                Peticion peticion = this.peticion;

                try {
                    resultado.setValor(Float.parseFloat(resultadoValorTextField.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            panel,
                            "EL FORMATO DEL VALOR DEBE SER X.X",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                resultado.setCodigoPractica(((Practica) practicasSpinner.getSelectedItem()).getCodigo());
                resultado.setEstado((EstadoResultado) estadoSpinner.getSelectedItem());

                if (peticion != null) {
                    peticion.removeResultado(resultadoViejo);
                    peticion.addResultado(resultado);
                    PeticionControler.modifyPeticiones(peticion);
                } else {
                    Peticion aux = (Peticion) peticionesSpinner.getSelectedItem();
                    if (aux != null) {
                        aux.addResultado(resultado);
                        PeticionControler.modifyPeticiones(aux);
                    } else {
                        JOptionPane.showMessageDialog(
                                panel,
                                "OCURRIO UN ERROR DESCONOCIDO",
                                "ERROR",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
                Application.manager.navigateTo(new Resultados());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            peticion.removeResultado(resultado);
            PeticionControler.modifyPeticiones(peticion);
            Application.manager.navigateTo(new Resultados());
        });
    }

    private void setPacientesSpinner() {
        pacientesSpinner = new JComboBox<>();
        List<PacienteDTO> pacientes = PacientesTable.getAllPacientes();
        DefaultComboBoxModel<PacienteDTO> pacientesItem = new DefaultComboBoxModel<>();
        pacientesItem.addAll(pacientes);
        pacientesSpinner.setModel(pacientesItem);

        pacientesSpinner.addItemListener(e -> {
            Paciente paciente = (Paciente) e.getItem();
            List<Peticion> peticionesPaciente = PeticionControler.getAllPeticionesPaciente(paciente);
            DefaultComboBoxModel<Peticion> peticionesPacienteItem = new DefaultComboBoxModel<>();
            peticionesPacienteItem.addAll(peticionesPaciente);
            peticionesSpinner.setModel(peticionesPacienteItem);
            practicasSpinner.setSelectedItem(null);
        });
    }

    private void setPeticionSpinner() {
        peticionesSpinner = new JComboBox<>();
        peticionesSpinner.addItemListener(e -> {
            Peticion peticion = (Peticion) e.getItem();
            if (this.peticion != null) {
                DefaultComboBoxModel<Practica> practicasPeticionItem = new DefaultComboBoxModel<>();
                practicasPeticionItem.addAll(PracticasTable.getAllPracticas());
                practicasSpinner.setModel(practicasPeticionItem);
            } else {
                if (peticion.getPracticas().size() != peticion.getResultados().size()) {
                    List<Practica> practicasPendientes = new ArrayList<>();
                    for (Practica p: peticion.getPracticas()) {
                        boolean pendiente = true;
                        for (Resultado r: peticion.getResultados()) {
                            if (r.getCodigoPractica() == p.getCodigo()) {
                                pendiente = false;
                                break;
                            }
                        }
                        if (pendiente) {
                            practicasPendientes.add(p);
                        }
                    }
                    DefaultComboBoxModel<Practica> practicasPeticionItem = new DefaultComboBoxModel<>();
                    practicasPeticionItem.addAll(practicasPendientes);
                    practicasSpinner.setModel(practicasPeticionItem);
                } else {
                    JOptionPane.showMessageDialog(
                            panel,
                            "NO EXISTEN PRACTICAS PENDIENTES DE CARGAR RESULTADOS PARA ESTA PETICION",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
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
