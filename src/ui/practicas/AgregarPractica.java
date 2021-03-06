package ui.practicas;

import app.Application;
import models.OperadorRegla;
import models.Regla;
import navigation.Screen;
import ui.pacientes.models.Paciente;
import ui.peticiones.Peticiones;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.sucursales.model.Sucursal;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class AgregarPractica implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JButton addButton;
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
    private JList<Regla> valoresReservadosList;
    private JList<Regla> valoresCriticosList;
    private JTextField nombreTextField;
    private JTextField horasTextField;

    private final Practica practica;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public AgregarPractica() {
        this.practica = new Practica();
        addListener();
        valoresReservadosList.setModel(getValores(practica.getValoresReservados()));
        valoresCriticosList.setModel(getValores(practica.getValoresCriticos()));
    }

    public AgregarPractica(Practica practica) {
        this.practica = practica;
        initPractica();
        addListener();
        valoresReservadosList.setModel(getValores(practica.getValoresReservados()));
        valoresCriticosList.setModel(getValores(practica.getValoresCriticos()));
    }

    private void initPractica() {
        title.setText("Editar pr??ctica");
        addButton.setText("Editar");

        nombreTextField.setText(practica.getNombre());
        horasTextField.setText(String.valueOf(practica.getHoras()));
        activoCheckBox.setSelected(practica.getActivo());
    }

    private void createUIComponents() {
        addBackListener();

        setValoresCriticosList();
        setValoresReservadosList();
        setValorCriticoSpinner();
        setValorReservadoSpinner();
        addValoresCriticos();
        addValoresReservados();
        removeValoresCriticos();
        removeValoresReservados();
    }

    private Boolean checkFields() {
        String nombre = nombreTextField.getText();
        String horas = horasTextField.getText();

        if (!nombre.equals("") && !horas.equals("")) {
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
                Application.manager.navigateTo(new Practicas());
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                if (checkFields()) {
                    practica.setNombre(nombreTextField.getText());
                    practica.setHoras(Integer.parseInt(horasTextField.getText()));
                    practica.setActivo(activoCheckBox.isSelected());

                    if (practica.getId() != null) {
                        PracticasTable.modifyPractica(practica);
                    } else {
                        PracticasTable.addPractica(practica);
                    }

                    Application.manager.navigateTo(new Practicas());
                }
            }
        });
    }

    private void setValoresCriticosList() {
        valoresCriticosList = new JList<>();
        valoresCriticosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        valoresCriticosList.setLayoutOrientation(JList.VERTICAL);
        valoresCriticosList.setSize(300,300);
    }

    private void setValoresReservadosList() {
        valoresReservadosList = new JList<>();
        valoresReservadosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        valoresReservadosList.setLayoutOrientation(JList.VERTICAL);
        valoresReservadosList.setSize(300,300);
    }

    private ListModel<Regla> getValores(List<Regla> valores) {
        DefaultListModel<Regla> reglasModel = new DefaultListModel<>();
        reglasModel.addAll(valores);
        return reglasModel;
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
                    panel.revalidate();
                    panel.repaint();
                }
                default -> {
                    valorCriticoMaximo.setVisible(true);
                    valorCriticoMinimo.setVisible(true);
                    panel.revalidate();
                    panel.repaint();
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
                    valorReservadoMaximo.setVisible(false);
                    valorReservadoMinimo.setVisible(true);
                    panel.revalidate();
                    panel.repaint();
                }
                default -> {
                    valorReservadoMinimo.setVisible(true);
                    valorReservadoMaximo.setVisible(true);
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });
    }

    private void addValoresCriticos() {
        addValorCritico = new JLabel(new ImageIcon("resources/add.png"));
        addValorCritico.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OperadorRegla operadorRegla = (OperadorRegla) valoresCriticosSpinner.getSelectedItem();
                if (operadorRegla != null) {
                    try {
                        Regla regla = switch (operadorRegla) {
                            case MAYOR_IGUAL, MAYOR, MENOR, MENOR_IGUAL, IGUAL -> new Regla(
                                    Float.parseFloat(valorCriticoMinimo.getText()),
                                    -1f,
                                    operadorRegla
                            );
                            default -> new Regla(
                                    Float.parseFloat(valorCriticoMinimo.getText()),
                                    Float.parseFloat(valorCriticoMaximo.getText()),
                                    operadorRegla
                            );
                        };
                        practica.addValorCritico(regla);
                        valoresCriticosList.setModel(getValores(practica.getValoresCriticos()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                panel,
                                "EL NUMERO DEBE ESTAR EN FORMATO X.X",
                                "ERROR",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
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

    private void addValoresReservados() {
        addValorReservado = new JLabel(new ImageIcon("resources/add.png"));
        addValorReservado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OperadorRegla operadorRegla = (OperadorRegla) valoresReservadosSpinner.getSelectedItem();
                if (operadorRegla != null) {
                    try {
                        Regla regla = switch (operadorRegla) {
                            case MAYOR_IGUAL, MAYOR, MENOR, MENOR_IGUAL, IGUAL -> new Regla(
                                    Float.parseFloat(valorReservadoMinimo.getText()),
                                    -1f,
                                    operadorRegla
                            );
                            default -> new Regla(
                                    Float.parseFloat(valorReservadoMinimo.getText()),
                                    Float.parseFloat(valorReservadoMaximo.getText()),
                                    operadorRegla
                            );
                        };
                        practica.addValorReservado(regla);
                        valoresReservadosList.setModel(getValores(practica.getValoresReservados()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                panel,
                                "EL NUMERO DEBE ESTAR EN FORMATO X.X",
                                "ERROR",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
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

    private void removeValoresCriticos() {
        removeValorCritico = new JLabel(new ImageIcon("resources/remove.png"));
        removeValorCritico.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Regla regla = valoresCriticosList.getSelectedValue();
                if (regla != null) {
                    practica.removeValorReservado(regla);
                    valoresCriticosList.setModel(getValores(practica.getValoresCriticos()));
                } else {
                    JOptionPane.showMessageDialog(
                            panel,
                            "DEBE SELECCIONAR UNA REGLA",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }

    private void removeValoresReservados() {
        removeValorReservado= new JLabel(new ImageIcon("resources/remove.png"));
        removeValorReservado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Regla regla = valoresReservadosList.getSelectedValue();
                if (regla != null) {
                    practica.removeValorReservado(regla);
                    valoresReservadosList.setModel(getValores(practica.getValoresReservados()));
                } else {
                    JOptionPane.showMessageDialog(
                            panel,
                            "DEBE SELECCIONAR UNA REGLA",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }
}
