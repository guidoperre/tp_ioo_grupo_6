package ui.peticiones;

import app.Application;
import navigation.Screen;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.AgregarUsuario;

import javax.swing.*;
import java.awt.*;
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
    private JList<Practica> practicasList;
    private JLabel addPractica;
    private JComboBox<Paciente> pacientesSpinner;
    private JComboBox<Sucursal> sucursalSpinner;
    private JComboBox<Practica> practicasSpinner;
    private JLabel removePractica;

    private final Peticion peticion;

    public AgregarPeticion() {
        this.peticion = new Peticion();
        addListener();
        practicasList.setModel(getPracticas(peticion.getPracticas()));
    }

    public AgregarPeticion(Peticion peticion) {
        this.peticion = peticion;
        addListener();
        practicasList.setModel(getPracticas(peticion.getPracticas()));
        initUsuario();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void initUsuario() {
        title.setText("Agregar peticion");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        pacientesSpinner.setSelectedItem(peticion.getPaciente());
        obraSocialTextField.setText(peticion.getObraSocial());
        sucursalSpinner.setSelectedItem(peticion.getSucursal());

        deleteListener();
        addListener();
    }

    private void createUIComponents() {
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
                if (peticion != null) {
                    peticion.setObraSocial(obraSocialTextField.getText());
                    peticion.setPaciente((Paciente) pacientesSpinner.getSelectedItem());
                    peticion.setSucursal((Sucursal) sucursalSpinner.getSelectedItem());
                } else {
                    PeticionesTable.addPeticiones(new Peticion(
                            (Paciente) pacientesSpinner.getSelectedItem(),
                            obraSocialTextField.getText(),
                            (Sucursal) sucursalSpinner.getSelectedItem(),
                            new Date(),
                            new Date()
                    ));
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

    private ListModel<Practica> getPracticas(List<Practica> practicas) {
        DefaultListModel<Practica> practicasModel = new DefaultListModel<>();
        practicasModel.addAll(practicas);
        return practicasModel;
    }

    private void setPracticasSpinner() {
        practicasSpinner = new JComboBox<>();
        List<Practica> practicas = PracticasTable.getAllPracticas();
        DefaultComboBoxModel<Practica> practicasItem = new DefaultComboBoxModel<>();
        practicasItem.addAll(practicas);
        practicasSpinner.setModel(practicasItem);
    }

    private void addPractica() {
        addPractica = new JLabel(new ImageIcon("resources/add.png"));
        addPractica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Practica practica = (Practica) practicasSpinner.getSelectedItem();
                peticion.addPractica(practica);
                practicasList.setModel(getPracticas(peticion.getPracticas()));
            }
        });
    }

    private void removePractica() {
        removePractica = new JLabel(new ImageIcon("resources/remove.png"));
        removePractica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Practica practica = practicasList.getSelectedValue();
                peticion.removePractica(practica);
                practicasList.setModel(getPracticas(peticion.getPracticas()));
            }
        });
    }

    private void setPaciente() {
        pacientesSpinner = new JComboBox<>();
        List<Paciente> pacientes = PacientesTable.getAllPacientes();
        DefaultComboBoxModel<Paciente> pacientesItem = new DefaultComboBoxModel<>();
        pacientesItem.addAll(pacientes);
        pacientesSpinner.setModel(pacientesItem);
    }

    private void setSucursal() {
        sucursalSpinner = new JComboBox<>();
        List<Sucursal> sucursales = SucursalesTable.getAllSucursales();
        DefaultComboBoxModel<Sucursal> sucursalItem = new DefaultComboBoxModel<>();
        sucursalItem.addAll(sucursales);
        sucursalSpinner.setModel(sucursalItem);
    }
}
