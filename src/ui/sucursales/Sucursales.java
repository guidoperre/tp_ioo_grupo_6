package ui.sucursales;

import ui.sucursales.model.Sucursal;
import ui.home.Home;
import ui.pacientes.models.PacientesTable;
import ui.sucursales.model.SucursalesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Sucursales {
    private final JFrame frame = new JFrame("Sucursales");

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    public Sucursales() {
        init();
    }

    // Inicializa la ventana
    public void init() {
        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();

        //Calculate the frame location
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        frame.setLocation(x, y);
        frame.setVisible(true);
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
                frame.dispose();
                new Home();
            }
        });
    }

    private void addPacienteListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new AgregarSucursal();
            }
        });
    }

    private void showPacientes() {
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
                    frame.dispose();
                    new AgregarSucursal((Sucursal) target.getModel().getElementAt(index));
                }
            }
        });
    }

    private ListModel<Sucursal> getSucursales() {
        List<Sucursal> sucursales = SucursalesTable.getAllSucursales();
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
