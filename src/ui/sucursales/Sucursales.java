package ui.sucursales;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.sucursales.model.SucursalDTO;
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
    private JList<SucursalDTO> sucursalesList;

    final private SucursalesController controller;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Sucursales() {
        controller = SucursalesController.getInstance();
        sucursalesList.setModel(getSucursales());
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
        sucursalesList = new JList<>();
        sucursalesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sucursalesList.setLayoutOrientation(JList.VERTICAL);
        sucursalesList.setCellRenderer(new SucursalViewHolder());
        sucursalesList.setSize(300,300);
        listListener(sucursalesList);
        listPanel.add(sucursalesList);
    }

    private void listListener(JList<SucursalDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    controller.setSucursal((SucursalDTO) target.getModel().getElementAt(index));
                    Application.manager.navigateTo(new AgregarSucursal(controller));
                }
            }
        });
    }

    private ListModel<SucursalDTO> getSucursales() {
        List<SucursalDTO> sucursales = controller.getAllSucursales();
        DefaultListModel<SucursalDTO> sucursalesModel = new DefaultListModel<>();
        sucursalesModel.addAll(sucursales);
        return sucursalesModel;
    }

    static class SucursalViewHolder extends JPanel implements ListCellRenderer<SucursalDTO> {

        public SucursalViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends SucursalDTO> list,
            SucursalDTO value,
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
