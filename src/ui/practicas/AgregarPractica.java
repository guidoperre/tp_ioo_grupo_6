package ui.practicas;

import app.Application;
import navigation.Screen;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
//        valoresCriticosSpinner = new JComboBox<>();
//        List<OperadorRegla> operadorReglaList = List.of(OperadorRegla.values());
//        DefaultComboBoxModel<OperadorRegla> operadorRegla = new DefaultComboBoxModel<>();
//        operadorRegla.addAll(operadorReglaList);
//        valoresCriticosSpinner.setModel(operadorRegla);
//
//        valoresCriticosSpinner.addItemListener(e -> {
//            OperadorRegla operadorRegla1 = (OperadorRegla) e.getItem();
//            switch (operadorRegla1) {
//                case MAYOR_IGUAL, MAYOR, MENOR, MENOR_IGUAL, IGUAL -> {
//                    valorCriticoMaximoTextField.setVisible(false);
//                    valorCriticoMinimoTextField.setVisible(true);
//                }
//                default -> {
//                    valorCriticoMaximoTextField.setVisible(true);
//                    valorCriticoMinimoTextField.setVisible(true);
//                }
//            }
//        });
    }

    private void setValorReservadoSpinner() {
//        valoresReservadosSpinner = new JComboBox<>();
//        List<OperadorRegla> operadorReglaList = List.of(OperadorRegla.values());
//        DefaultComboBoxModel<OperadorRegla> operadorRegla = new DefaultComboBoxModel<>();
//        operadorRegla.addAll(operadorReglaList);
//        valoresReservadosSpinner.setModel(operadorRegla);
//
//        valoresReservadosSpinner.addItemListener(e -> {
//            OperadorRegla operadorRegla1 = (OperadorRegla) e.getItem();
//            switch (operadorRegla1) {
//                case MAYOR_IGUAL, MAYOR, MENOR, MENOR_IGUAL, IGUAL -> {
//                    valorReservadoMinimoTextField.setVisible(false);
//                    valorReservadoMaximoTextField.setVisible(true);
//                }
//                default -> {
//                    valorReservadoMinimoTextField.setVisible(true);
//                    valorReservadoMaximoTextField.setVisible(true);
//                }
//            }
//        });
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
