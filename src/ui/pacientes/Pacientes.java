package ui.pacientes;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.pacientes.controlador.PacienteControler;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.sucursales.model.SucursalDTO;
import ui.usuarios.controlador.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Pacientes implements Screen {

    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    final private PacienteControler controller;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Pacientes() {
        controller = PacienteControler.getInstance();
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
                controller.setPaciente(null);
                Application.manager.navigateTo(new AgregarPaciente());
            }
        });
    }

    private void showPacientes() {
        listPanel = new JPanel();
        ListModel<PacienteDTO> pacientes = getPacientes();

        JList<PacienteDTO> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new PacienteViewHolder());
        list.setModel(pacientes);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<PacienteDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    controller.setPaciente((PacienteDTO) target.getModel().getElementAt(index));
                    Application.manager.navigateTo(new AgregarPaciente(controller));
                }
            }
        });
    }

    private ListModel<PacienteDTO> getPacientes() {
        List<PacienteDTO> pacientes = controller.getPacientes();
        DefaultListModel<PacienteDTO> pacientesModel = new DefaultListModel<>();
        pacientesModel.addAll(pacientes);
        return pacientesModel;
    }

    static class PacienteViewHolder extends JPanel implements ListCellRenderer<PacienteDTO> {

        public PacienteViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends PacienteDTO> list,
            PacienteDTO value,
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
