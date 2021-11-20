package ui.practicas;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.pacientes.controlador.PacienteControler;
import ui.pacientes.models.PacienteDTO;
import ui.practicas.controlador.PracticaController;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.practicas.model.PracticasTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Practicas implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JLabel addPractica;
    private JPanel panel;
    private JPanel listPanel;

    private final PracticaController controller;

    public Practicas() {
        controller = PracticaController.getInstance();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        addBackListener();
        addPracticasListener();
        showPracticas();
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

    private void addPracticasListener() {
        addPractica = new JLabel(new ImageIcon("resources/add.png"));
        addPractica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.setPractica(new PracticaDTO());
                Application.manager.navigateTo(new AgregarPractica());
            }
        });
    }

    private void showPracticas() {
        listPanel = new JPanel();
        ListModel<PracticaDTO> practicas = getPracticas();

        JList<PracticaDTO> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new PracticasViewHolder());
        list.setModel(practicas);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<PracticaDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    controller.setPractica((PracticaDTO) target.getModel().getElementAt(index));
                    Application.manager.navigateTo(new AgregarPractica(controller));
                }
            }
        });
    }

    private ListModel<PracticaDTO> getPracticas() {
        List<PracticaDTO> practicas = PracticasTable.getAllPracticas();
        DefaultListModel<PracticaDTO> practicasModel = new DefaultListModel<>();
        practicasModel.addAll(practicas);
        return practicasModel;
    }

    static class PracticasViewHolder extends JPanel implements ListCellRenderer<PracticaDTO> {

        public PracticasViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends PracticaDTO> list,
            PracticaDTO value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            PracticasItem item = new PracticasItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
