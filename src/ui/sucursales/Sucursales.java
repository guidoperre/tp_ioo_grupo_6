package ui.sucursales;

import app.Application;
import navigation.Screen;
import ui.sucursales.model.Sucursal;
import ui.home.Home;
import ui.sucursales.controlador.SucursalesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Sucursales implements Screen {

    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Sucursales() {

    }

    private void createUIComponents() {
        addBackListener();
        addSucursalListener();
        showSucursales();
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

    private void addSucursalListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new AgregarSucursal());
            }
        });
    }

    private void showSucursales() {
        listPanel = new JPanel();
        ListModel<Sucursal> sucursales = getSucursales();

        JList<Sucursal> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new SucursalViewHolder());
        list.setModel(sucursales);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<Sucursal> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    Application.manager.navigateTo(new AgregarSucursal((Sucursal) target.getModel().getElementAt(index)));
                }
            }
        });
    }

    private ListModel<Sucursal> getSucursales() {
        List<Sucursal> sucursales = SucursalesController.getAllSucursales();
        DefaultListModel<Sucursal> sucursalesModel = new DefaultListModel<>();
        sucursalesModel.addAll(sucursales);
        return sucursalesModel;
    }

    static class SucursalViewHolder extends JPanel implements ListCellRenderer<Sucursal> {

        public SucursalViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends Sucursal> list,
            Sucursal value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            SucursalesItem item = new SucursalesItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
