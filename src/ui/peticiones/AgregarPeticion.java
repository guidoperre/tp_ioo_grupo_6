package ui.peticiones;

import app.Application;
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
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalDTO;
import ui.sucursales.model.SucursalesTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
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
    private JComboBox<Sucursal> sucursalSpinner;
    private JComboBox<PracticaDTO> practicasSpinner;
    private JLabel removePractica;

    private final PeticionDTO peticion;
    final private PeticionController controller;

    public AgregarPeticion() {
        controller = new PeticionController();
        this.peticion = new PeticionDTO();
        addListener();
        practicasList.setModel(getPracticas(peticion.getPracticas()));
    }

    public AgregarPeticion(PeticionDTO peticion) {
        controller = new PeticionController();
        this.peticion = peticion;
        practicasList.setModel(getPracticas(peticion.getPracticas()));
        addListener();
        initUsuario();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void initUsuario() {
        title.setText("Editar peticion");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        pacientesSpinner.setSelectedItem(peticion.getPaciente());
        obraSocialTextField.setText(peticion.getObraSocial());
        sucursalSpinner.setSelectedItem(peticion.getSucursal());

        deleteListener();
    }

    private void createUIComponents() {
        addBackListener();
        setPaciente();
//        setSucursal();

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
                peticion.setObraSocial(obraSocialTextField.getText());
                peticion.setPaciente((PacienteDTO) pacientesSpinner.getSelectedItem());
                peticion.setSucursal((Sucursal) sucursalSpinner.getSelectedItem());

                if (peticion.getId() != null) {
                    PeticionesTable.modifyPeticiones(peticion);
                } else {
                    peticion.setFechaCarga(new Date());
                    peticion.setFechaEntrega(new Date());
                    PeticionesTable.addPeticiones(peticion);
                }

                Application.manager.navigateTo(new Peticiones());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            PeticionesTable.deletePeticiones(peticion);
            Application.manager.navigateTo(new Peticiones());
        });
    }

    private void setPracticasList() {
        practicasList = new JList<>();
        practicasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        practicasList.setLayoutOrientation(JList.VERTICAL);
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
        List<PracticaDTO> practicas = PracticasTable.getAllPracticas();
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
                    peticion.addPractica(practica);
                    practicasList.setModel(getPracticas(peticion.getPracticas()));
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
                    peticion.removePractica(practica);
                    practicasList.setModel(getPracticas(peticion.getPracticas()));
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

//    private void setSucursal() {
//        sucursalSpinner = new JComboBox<>();
//        List<SucursalDTO> sucursales = SucursalesTable.getAllSucursales();
//        DefaultComboBoxModel<Sucursal> sucursalItem = new DefaultComboBoxModel<>();
//        sucursalItem.addAll(sucursales);
//        sucursalSpinner.setModel(sucursalItem);
//    }
}
