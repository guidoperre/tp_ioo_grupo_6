package ui.pacientes;

import ui.home.Home;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Pacientes {
    private final JFrame frame = new JFrame("Pacientes");

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    public Pacientes() {
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
                super.mouseClicked(e);
                new Home();
                frame.setVisible(false);
            }
        });
    }

    private void addPacienteListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new AgregarPaciente();
                frame.setVisible(false);
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
                if (me.getClickCount() == 1) {
                    JList target = (JList) me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        new AgregarPaciente((Paciente) target.getModel().getElementAt(index));
                        frame.setVisible(false);
                    }
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
