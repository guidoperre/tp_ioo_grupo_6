package ui.practicas;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.practicas.model.Practica;
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

    public Practicas() {

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
                Application.manager.navigateTo(new AgregarPractica());
            }
        });
    }

    private void showPracticas() {
        listPanel = new JPanel();
        ListModel<Practica> practicas = getPracticas();

        JList<Practica> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new PracticasViewHolder());
        list.setModel(practicas);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<Practica> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    Application.manager.navigateTo(new AgregarPractica((Practica) target.getModel().getElementAt(index)));
                }
            }
        });
    }

    private ListModel<Practica> getPracticas() {
        List<Practica> practicas = PracticasTable.getAllPracticas();
        DefaultListModel<Practica> practicasModel = new DefaultListModel<>();
        practicasModel.addAll(practicas);
        return practicasModel;
    }

    static class PracticasViewHolder extends JPanel implements ListCellRenderer<Practica> {

        public PracticasViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends Practica> list,
            Practica value,
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
