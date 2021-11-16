package ui.practicas;

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
    private final JFrame frame = new JFrame("Practicas");

    private JLabel title;
    private JLabel backButton;
    private JLabel addPractica;
    private JPanel panel;
    private JPanel listPanel;

    public Practicas() {
        init();
    }

    @Override
    public JPanel getPanel() {
        return panel;
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
        addPracticasListener();
        showPracticas();
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

    private void addPracticasListener() {
        addPractica = new JLabel(new ImageIcon("resources/add.png"));
        addPractica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new AgregarPractica();
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
                    frame.dispose();
                    new AgregarPractica((Practica) target.getModel().getElementAt(index));
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
