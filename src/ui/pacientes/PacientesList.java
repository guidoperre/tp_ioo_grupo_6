package ui.pacientes;

import ui.home.Home;
import ui.pacientes.models.Paciente;
import models.SexoEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PacientesList{
    private final JFrame frame = new JFrame("Pacientes");

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    public PacientesList() {
        init();
    }

    // Inicializa la ventana
    public void init() {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
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
                Home home = new Home();
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
                AgregarPaciente agregarPaciente = new AgregarPaciente();
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

        listPanel.add(list);
    }

    //TODO: Traer pacientes cuando este implementado
    private ListModel<Paciente> getPacientes() {
        DefaultListModel<Paciente> pacientes = new DefaultListModel<>();
        pacientes.addElement(new Paciente("Guido Perre",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido Perre",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido Perre",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido Perre",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido Perre",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        return pacientes;
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
