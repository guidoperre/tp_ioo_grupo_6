package ui.practicas;

import app.Application;
import models.OperadorRegla;
import navigation.Screen;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarPractica implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
    private JButton deleteButton;
    private JLabel removeValorCritico;
    private JLabel addValorCritico;
    private JLabel addValorReservado;
    private JLabel removeValorReservado;
    private JCheckBox activoCheckBox;
    private JComboBox<OperadorRegla> valoresReservadosSpinner;
    private JTextField valorReservadoMinimo;
    private JTextField valorReservadoMaximo;
    private JTextField valorCriticoMinimo;
    private JTextField valorCriticoMaximo;
    private JComboBox<OperadorRegla> valoresCriticosSpinner;

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
        addListener();
    }

    private void initPractica() {
        title.setText("Editar práctica");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        deleteListener();
    }

    private void createUIComponents() {
        addBackListener();

        setValorCriticoSpinner();
        setValorReservadoSpinner();
        addValoresCriticos();
        addValoresReservados();
        removeValoresCriticos();
        removeValoresReservados();
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
        addButton.addActionListener(e -> {
            if (checkFields()) {

                Application.manager.navigateTo(new Practicas());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            PracticasTable.deletePractica(practica);
            Application.manager.navigateTo(new Practicas());
        });
    }

    private void setValorCriticoSpinner() {
        valoresCriticosSpinner = new JComboBox<>();
        List<OperadorRegla> operadorReglaList = List.of(OperadorRegla.values());
        DefaultComboBoxModel<OperadorRegla> operadorRegla = new DefaultComboBoxModel<>();
        operadorRegla.addAll(operadorReglaList);
        valoresCriticosSpinner.setModel(operadorRegla);

        valoresCriticosSpinner.addItemListener(e -> {
            OperadorRegla operadorRegla1 = (OperadorRegla) e.getItem();
            switch (operadorRegla1) {
                case MAYOR_IGUAL, MAYOR, MENOR, MENOR_IGUAL, IGUAL -> {
                    valorCriticoMaximo.setVisible(false);
                    valorCriticoMinimo.setVisible(true);
                }
                default -> {
                    valorCriticoMaximo.setVisible(true);
                    valorCriticoMinimo.setVisible(true);
                }
            }
        });
    }

    private void setValorReservadoSpinner() {
        valoresReservadosSpinner = new JComboBox<>();
        List<OperadorRegla> operadorReglaList = List.of(OperadorRegla.values());
        DefaultComboBoxModel<OperadorRegla> operadorRegla = new DefaultComboBoxModel<>();
        operadorRegla.addAll(operadorReglaList);
        valoresReservadosSpinner.setModel(operadorRegla);

        valoresReservadosSpinner.addItemListener(e -> {
            OperadorRegla operadorRegla1 = (OperadorRegla) e.getItem();
            switch (operadorRegla1) {
                case MAYOR_IGUAL, MAYOR, MENOR, MENOR_IGUAL, IGUAL -> {
                    valorReservadoMinimo.setVisible(false);
                    valorReservadoMaximo.setVisible(true);
                }
                default -> {
                    valorReservadoMinimo.setVisible(true);
                    valorReservadoMaximo.setVisible(true);
                }
            }
        });
    }

    private void addValoresCriticos() {
        addValorCritico = new JLabel(new ImageIcon("resources/add.png"));
    }

    private void addValoresReservados() {
        addValorReservado = new JLabel(new ImageIcon("resources/add.png"));
    }

    private void removeValoresCriticos() {
        removeValorCritico = new JLabel(new ImageIcon("resources/remove.png"));
    }

    private void removeValoresReservados() {
        removeValorReservado= new JLabel(new ImageIcon("resources/remove.png"));
    }
}
