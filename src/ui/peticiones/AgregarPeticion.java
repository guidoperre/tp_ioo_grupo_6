package ui.peticiones;

import app.Application;
import navigation.Screen;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.controlador.PeticionController;
import ui.peticiones.model.PeticionDTO;
import ui.practicas.controlador.PracticaController;
import ui.practicas.model.PracticaDTO;
import ui.sucursales.model.SucursalDTO;
import ui.sucursales.model.SucursalesTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AgregarPeticion implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField obraSocialTextField;
    private JList<PracticaDTO> practicasList;
    private JLabel addPractica;
    private JComboBox<PacienteDTO> pacientesSpinner;
    private JComboBox<SucursalDTO> sucursalSpinner;
    private JComboBox<PracticaDTO> practicasSpinner;
    private JLabel removePractica;

    private PeticionController peticionController = PeticionController.getInstance();
    private PracticaController practicaController = PracticaController.getInstance();

    public AgregarPeticion() {
        addListener();
        initPeticion();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void initPeticion() {
        PeticionDTO peticion = peticionController.getPeticion();

        if (peticion.getId() != null) {
            title.setText("Editar peticion");
            addButton.setText("Editar");
            deleteButton.setVisible(true);

            pacientesSpinner.setSelectedItem(peticion.getPaciente());
            obraSocialTextField.setText(peticion.getObraSocial());
            sucursalSpinner.setSelectedItem(peticion.getSucursal());
            deleteListener();
        } else {
            title.setText("Agregar peticion");
            addButton.setText("Agregar");
            deleteButton.setVisible(false);
        }
    }

    private void createUIComponents() {
        peticionController = PeticionController.getInstance();
        practicaController = PracticaController.getInstance();

        addBackListener();
        setPaciente();
        setSucursal();

        setPracticasSpinner();
        addPractica();
        removePractica();
        setPracticasList();
    }

    private Boolean checkFields() {
        String obraSocial = obraSocialTextField.getText();

        if (
            !obraSocial.equals("") &&
            pacientesSpinner.getSelectedItem() != null &&
            sucursalSpinner.getSelectedItem() != null
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
                Application.manager.navigateTo(new Peticiones());
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                peticionController.addPeticion(
                        obraSocialTextField.getText(),
                        (PacienteDTO) pacientesSpinner.getSelectedItem(),
                        (SucursalDTO) sucursalSpinner.getSelectedItem()
                );
                Application.manager.navigateTo(new Peticiones());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            peticionController.deletePeticion();
            Application.manager.navigateTo(new Peticiones());
        });
    }

    private void setPracticasList() {
        practicasList = new JList<>();
        practicasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        practicasList.setLayoutOrientation(JList.VERTICAL);
        practicasList.setModel(getPracticas(peticionController.getPeticion().getPracticas()));
        practicasList.setSize(300,300);
    }

    private ListModel<PracticaDTO> getPracticas(List<PracticaDTO> practicas) {
        List<PracticaDTO> practicasActivas = new ArrayList<>();
        for (PracticaDTO p: practicas) {
            if (p.getActivo()) {
                practicasActivas.add(p);
            }
        }
        DefaultListModel<PracticaDTO> practicasModel = new DefaultListModel<>();
        practicasModel.addAll(practicasActivas);
        return practicasModel;
    }

    private void setPracticasSpinner() {
        practicasSpinner = new JComboBox<>();
        List<PracticaDTO> practicas = practicaController.getAllPracticas();
        List<PracticaDTO> practicasActivas = new ArrayList<>();
        for (PracticaDTO p: practicas) {
            if (p.getActivo()) {
                practicasActivas.add(p);
            }
        }
        DefaultComboBoxModel<PracticaDTO> practicasItem = new DefaultComboBoxModel<>();
        practicasItem.addAll(practicasActivas);
        practicasSpinner.setModel(practicasItem);
    }

    private void addPractica() {
        addPractica = new JLabel(new ImageIcon("resources/add.png"));
        addPractica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PracticaDTO practica = (PracticaDTO) practicasSpinner.getSelectedItem();
                if (practica != null) {
                    peticionController.getPeticion().addPractica(practica);
                    practicasList.setModel(getPracticas(peticionController.getPeticion().getPracticas()));
                } else {
                    JOptionPane.showMessageDialog(
                            panel,
                            "DEBE SELECCIONAR UNA PRACTICA",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }

    private void removePractica() {
        removePractica = new JLabel(new ImageIcon("resources/remove.png"));
        removePractica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PracticaDTO practica = practicasList.getSelectedValue();
                if (practica != null) {
                    peticionController.getPeticion().removePractica(practica);
                    practicasList.setModel(getPracticas(peticionController.getPeticion().getPracticas()));
                } else {
                    JOptionPane.showMessageDialog(
                            panel,
                            "DEBE SELECCIONAR UNA PRACTICA",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }

    private void setPaciente() {
        pacientesSpinner = new JComboBox<>();
        List<PacienteDTO> pacientes = PacientesTable.getAllPacientes();
        DefaultComboBoxModel<PacienteDTO> pacientesItem = new DefaultComboBoxModel<>();
        pacientesItem.addAll(pacientes);
        pacientesSpinner.setModel(pacientesItem);
    }

    private void setSucursal() {
        sucursalSpinner = new JComboBox<>();
        List<SucursalDTO> sucursales = SucursalesTable.getAllSucursales();
        DefaultComboBoxModel<SucursalDTO> sucursalItem = new DefaultComboBoxModel<>();
        sucursalItem.addAll(sucursales);
        sucursalSpinner.setModel(sucursalItem);
    }
}
