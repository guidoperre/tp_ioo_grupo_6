package ui.practicas;

import models.Regla;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AgregarPractica {
    private final JFrame frame = new JFrame("Agregar practica");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField nombreTextField;
    private JList<Regla> valoresCriticosList;
    private JList<Regla> valoresReservadosList;
    private JLabel addPractica;
    private JLabel addValorCritico;
    private JLabel addValorReservado;
    private JLabel removeValorCritico;
    private JLabel removeValorReservado;
    private JTextField cantidadDeHorasTextField;
    private JCheckBox activoCheckBoxField;
    private JComboBox<Regla> valoresCriticosSpinner;
    private JComboBox<Regla> valoresReservadosSpinner;
    private JLabel removePractica;

    private final Practica practica;

    public AgregarPractica() {
        this.practica = new Practica();
        init();
    }

    public AgregarPractica(Practica practica) {
        this.practica = practica;

        init();
        initPractica();
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

    private void initPractica() {
        title.setText("Agregar Práctica");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        nombreTextField.setText(practica.getNombre());

        deleteListener();
        addListener();
    }

    private void createUIComponents() {
        addBackListener();
        setValoresCriticos();
        setValoresReservados();

    }

    private Boolean checkFields() {
       return true;
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Practicas();
            }
        });
    }

    private void addListener() {
//        addButton.addActionListener(e -> {
//            if (checkFields()) {
//                frame.dispose();
//                if (peticion != null) {
//                    peticion.setObraSocial(obraSocialTextField.getText());
//                    peticion.setPaciente((Paciente) pacientesSpinner.getSelectedItem());
//                    peticion.setSucursal((Sucursal) sucursalSpinner.getSelectedItem());
//                } else {
//                    PeticionesTable.addPeticiones(new Peticion(
//                            (Paciente) pacientesSpinner.getSelectedItem(),
//                            obraSocialTextField.getText(),
//                            (Sucursal) sucursalSpinner.getSelectedItem(),
//                            new Date(),
//                            new Date()
//                    ));
//                }
//                new Peticiones();
//            }
//        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            frame.dispose();
            PracticasTable.deletePractica(practica);
            new Practicas();
        });
    }

    private void addValorCritico() {

    }

    private void addValorReservado() {

    }

    private void setValoresCriticos() {
        valoresCriticosSpinner = new JComboBox<>();
        List<Regla> criticos = new ArrayList<>();
        criticos.add(new Regla(0, 5, Regla.operadorEnum.menor));
        DefaultComboBoxModel<Regla> criticosItem = new DefaultComboBoxModel<>();
        criticosItem.addAll(criticos);
        valoresCriticosSpinner.setModel(criticosItem);
    }

    private void setValoresReservados() {

    }



}
