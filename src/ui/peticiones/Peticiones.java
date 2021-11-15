package ui.peticiones;

import ui.home.Home;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Peticiones {
    private final JFrame frame = new JFrame("Peticiones");

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    public Peticiones() {
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
        addPeticionesListener();
        showPeticiones();
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

    private void addPeticionesListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new AgregarPeticion();
            }
        });
    }

    private void showPeticiones() {
        listPanel = new JPanel();
        ListModel<Peticion> peticiones = getPeticiones();

        JList<Peticion> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new PeticionesViewHolder());
        list.setModel(peticiones);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<Peticion> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    frame.dispose();
                    new AgregarPeticion((Peticion) target.getModel().getElementAt(index));
                }
            }
        });
    }

    private ListModel<Peticion> getPeticiones() {
        List<Peticion> peticiones = PeticionesTable.getAllPeticiones();
        DefaultListModel<Peticion> peticionesModel = new DefaultListModel<>();
        peticionesModel.addAll(peticiones);
        return peticionesModel;
    }

    static class PeticionesViewHolder extends JPanel implements ListCellRenderer<Peticion> {

        public PeticionesViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends Peticion> list,
            Peticion value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            PeticionesItem item = new PeticionesItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
