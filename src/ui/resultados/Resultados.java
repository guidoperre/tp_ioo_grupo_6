package ui.resultados;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.resultados.model.Resultado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Resultados implements Screen {

    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;
    private JComboBox<Paciente> pacienteSpinner;
    private JList<Resultado> list;

    public Resultados() {

    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        addBackListener();
        addPeticionesListener();
        showResultados();
        setPacientesSpinner();
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new Home());
            }
        });
    }

    private void addPeticionesListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new AgregarResultado());
            }
        });
    }

    private void setPacientesSpinner() {
        pacienteSpinner = new JComboBox<>();
        List<Paciente> pacientes = PacientesTable.getAllPacientes();
        DefaultComboBoxModel<Paciente> pacientesItem = new DefaultComboBoxModel<>();
        pacientesItem.addAll(pacientes);
        pacienteSpinner.setModel(pacientesItem);

        pacienteSpinner.addItemListener(e -> {
            Paciente paciente = (Paciente) e.getItem();
            list.setModel(getResultados(paciente));
        });
    }

    private void showResultados() {
        listPanel = new JPanel();

        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new ResultadosViewHolder());
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<Resultado> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    Application.manager.navigateTo(
                            new AgregarResultado((Resultado) target.getModel().getElementAt(index))
                    );
                }
            }
        });
    }

    private ListModel<Resultado> getResultados(Paciente paciente) {
        List<Peticion> peticiones = PeticionesTable.getAllPeticionesPaciente(paciente);
        List<Resultado> resultados = new ArrayList<>();
        for (Peticion p: peticiones) {
            resultados.addAll(p.getResultados());
        }

        DefaultListModel<Resultado> resultadosModel = new DefaultListModel<>();
        resultadosModel.addAll(resultados);
        return resultadosModel;
    }

    static class ResultadosViewHolder extends JPanel implements ListCellRenderer<Resultado> {

        public ResultadosViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends Resultado> list,
            Resultado value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            ResultadosItem item = new ResultadosItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
