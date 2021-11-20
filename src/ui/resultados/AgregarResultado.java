package ui.resultados;

import app.Application;
import models.EstadoResultado;
import navigation.Screen;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.controlador.PeticionController;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.practicas.model.PracticasTable;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;
import ui.resultados.controlador.ResultadosController;

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
    private JComboBox<PeticionDTO> peticionesSpinner;
    private JComboBox<PracticaDTO> practicasSpinner;
    private JComboBox<EstadoResultado> estadoSpinner;
    private JTextField resultadoValorTextField;

    private PeticionDTO peticion;
    private ResultadoDTO resultado;
    private final ResultadosController controller;
    final private PeticionController peticionController;

    public AgregarResultado() {
        peticionController = PeticionController.getInstance();
        this.controller = new ResultadosController();
        addListener();
    }

    public AgregarResultado(ResultadoDTO resultado) {
        peticionController = PeticionController.getInstance();
        this.controller = new ResultadosController();
        this.resultado = resultado;
        this.peticion = getPeticion();
        addListener();
        initResultado();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private PeticionDTO getPeticion() {
        List<PeticionDTO> peticions = peticionController.getAllPeticiones();
//        for (PeticionDTO p: peticions) {
//            for (ResultadoDTO resultado: p.getResultados()) {
//                if (resultado.getId().equals(resultado.getId(resultado))) {
//                    return p;
//                }
//            }
//        }
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
                ResultadoDTO resultadoViejo = resultado;
                PeticionDTO peticion = this.peticion;

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
                resultado.setCodigoPractica(((PracticaDTO) practicasSpinner.getSelectedItem()).getCodigo());
                resultado.setEstado((EstadoResultado) estadoSpinner.getSelectedItem());

                if (peticion != null) {
                    peticion.removeResultado(resultadoViejo);
                    peticion.addResultado(resultado);
                    peticionController.modifyPeticion(peticion);
                } else {
                    PeticionDTO aux = (PeticionDTO) peticionesSpinner.getSelectedItem();
                    if (aux != null) {
                        aux.addResultado(resultado);
                        peticionController.modifyPeticion(aux);
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
            peticionController.modifyPeticion(peticion);
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
            PacienteDTO paciente = (PacienteDTO) e.getItem();
            List<PeticionDTO> peticionesPaciente = peticionController.getAllPeticionesPaciente(paciente);
            DefaultComboBoxModel<PeticionDTO> peticionesPacienteItem = new DefaultComboBoxModel<>();
            peticionesPacienteItem.addAll(peticionesPaciente);
            peticionesSpinner.setModel(peticionesPacienteItem);
            practicasSpinner.setSelectedItem(null);
        });
    }

    private void setPeticionSpinner() {
        peticionesSpinner = new JComboBox<>();
        peticionesSpinner.addItemListener(e -> {
            PeticionDTO peticion = (PeticionDTO) e.getItem();
            if (this.peticion != null) {
                DefaultComboBoxModel<PracticaDTO> practicasPeticionItem = new DefaultComboBoxModel<>();
                practicasPeticionItem.addAll(PracticasTable.getAllPracticas());
                practicasSpinner.setModel(practicasPeticionItem);
            } else {
                if (peticion.getPracticas().size() != peticion.getResultados().size()) {
                    List<PracticaDTO> practicasPendientes = new ArrayList<>();
                    for (PracticaDTO p: peticion.getPracticas()) {
                        boolean pendiente = true;
                        for (ResultadoDTO r: peticion.getResultados()) {
                            if (r.getCodigoPractica() == p.getCodigo()) {
                                pendiente = false;
                                break;
                            }
                        }
                        if (pendiente) {
                            practicasPendientes.add(p);
                        }
                    }
                    DefaultComboBoxModel<PracticaDTO> practicasPeticionItem = new DefaultComboBoxModel<>();
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
