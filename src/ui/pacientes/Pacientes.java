package ui.pacientes;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.sucursales.AgregarSucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Pacientes implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Pacientes() {

    }

    private void createUIComponents() {
        addBackListener();
        addPacienteListener();
        showPacientes();
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

    private void addPacienteListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new AgregarPaciente());
            }
        });
    }

    private void showPacientes() {
        listPanel = new JPanel();
        ListModel<Paciente> pacientes = getPacientes();

        JList<Paciente> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new PacienteViewHolder());
        list.setModel(pacientes);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<Paciente> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    Application.manager.navigateTo(new AgregarPaciente((Paciente) target.getModel().getElementAt(index)));
                }
            }
        });
    }

    private ListModel<Paciente> getPacientes() {
        List<Paciente> pacientes = PacientesTable.getAllPacientes();
        DefaultListModel<Paciente> pacientesModel = new DefaultListModel<>();
        pacientesModel.addAll(pacientes);
        return pacientesModel;
    }

    static class PacienteViewHolder extends JPanel implements ListCellRenderer<Paciente> {

        public PacienteViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends Paciente> list,
            Paciente value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            PacientesItem item = new PacientesItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
