package ui.peticiones;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class AgregarPeticion {
    private final JFrame frame = new JFrame("Agregar peticion");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField obraSocialTextField;
    private JList practicasList;
    private JLabel addPractica;
    private JComboBox<Paciente> pacientesSpinner;
    private JComboBox<Sucursal> sucursalSpinner;
    private JComboBox<Practica> practicasSpinner;

    private Peticion peticion;

    public AgregarPeticion() {
        init();
    }

    public AgregarPeticion(Peticion peticion) {
        this.peticion = peticion;
        init();
        initUsuario();
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
                new Peticiones();
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                frame.dispose();
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
                new Peticiones();
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            frame.dispose();
            PeticionesTable.deletePeticiones(peticion);
            new Peticiones();
        });
    }

    private void setPracticasSpinner() {
        addPractica = new JLabel(new ImageIcon("resources/add.png"));

        practicasSpinner = new JComboBox<>();
        List<Practica> practicas = PracticasTable.getAllPracticas();
        DefaultComboBoxModel<Practica> practicasItem = new DefaultComboBoxModel<>();
        practicasItem.addAll(practicas);
        practicasSpinner.setModel(practicasItem);
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
