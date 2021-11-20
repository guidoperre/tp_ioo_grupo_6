package ui.resultados;

import app.Application;
import models.EstadoResultado;
import navigation.Screen;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.controlador.PeticionController;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.PracticaDTO;
import ui.practicas.model.PracticasTable;
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

    private final ResultadosController resultadosController = ResultadosController.getInstance();
    private final PeticionController peticionController = PeticionController.getInstance();

    public AgregarResultado() {
        addListener();
        initResultado();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private PeticionDTO getPeticion(ResultadoDTO resultado) {
        List<PeticionDTO> peticions = peticionController.getAllPeticiones();
        for (PeticionDTO p: peticions) {
            for (ResultadoDTO r: p.getResultados()) {
                if (r.getId().equals(resultado.getId())) {
                    return p;
                }
            }
        }
        return null;
    }

    private void initResultado() {
        ResultadoDTO resultado = resultadosController.getResultado();

        if (resultado.getEstado() != null) {
            PeticionDTO peticion = getPeticion(resultado);

            title.setText("Editar resultado");
            addButton.setText("Editar");
            deleteButton.setVisible(true);

            if (peticion != null) {
                peticionController.setPeticion(peticion);
                pacientesSpinner.setSelectedItem(peticion.getPaciente());
                pacientesSpinner.setEnabled(false);
                peticionesSpinner.setSelectedItem(peticion);
                peticionesSpinner.setEnabled(false);
                practicasSpinner.setSelectedItem(PracticasTable.getPracticas(resultado.getCodigoPractica()));
                practicasSpinner.setEnabled(false);
            } else {
                peticionController.setPeticion(null);
            }

            estadoSpinner.setSelectedItem(resultado.getEstado());
            resultadoValorTextField.setText(String.valueOf(resultado.getValor()));

            deleteListener();
        } else {
            peticionController.setPeticion(null);
            title.setText("Agregar resultado");
            addButton.setText("Agregar");
            deleteButton.setVisible(false);
        }
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
                float valor;
                try {
                    valor = Float.parseFloat(resultadoValorTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            panel,
                            "EL FORMATO DEL VALOR DEBE SER X.X",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                peticionController.modifyPeticion(
                        resultadosController.getResultado(),
                        (PeticionDTO) peticionesSpinner.getSelectedItem(),
                        valor,
                        ((PracticaDTO) practicasSpinner.getSelectedItem()).getCodigo(),
                        (EstadoResultado) estadoSpinner.getSelectedItem()
                );
                Application.manager.navigateTo(new Resultados());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            peticionController.getPeticion().removeResultado(resultadosController.getResultado());
            PeticionesTable.modifyPeticiones(peticionController.getPeticion());
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
            if (peticionController.getPeticion() != null) {
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
