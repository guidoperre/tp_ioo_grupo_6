package ui.practicas;

import app.Application;
import models.OperadorRegla;
import models.Regla;
import navigation.Screen;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.sucursales.Sucursales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AgregarPractica implements Screen {
    
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

    private Practica practica;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public AgregarPractica() {
        addListener();
    }

    public AgregarPractica(Practica practica) {
        this.practica = practica;
        initPractica();
    }

    private void initPractica() {
        title.setText("Agregar Práctica");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        nombreTextField.setText(practica.getNombre());

        deleteListener();
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
                Application.manager.navigateTo(new Practicas());
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
            PracticasTable.deletePractica(practica);
            Application.manager.navigateTo(new Practicas());
        });
    }

    private void addValorCritico() {

    }

    private void addValorReservado() {

    }

    private void setValoresCriticos() {
        valoresCriticosSpinner = new JComboBox<>();
        List<Regla> criticos = new ArrayList<>();
        criticos.add(new Regla(0, 5, OperadorRegla.MENOR));
        DefaultComboBoxModel<Regla> criticosItem = new DefaultComboBoxModel<>();
        criticosItem.addAll(criticos);
        valoresCriticosSpinner.setModel(criticosItem);
    }

    private void setValoresReservados() {

    }



}
