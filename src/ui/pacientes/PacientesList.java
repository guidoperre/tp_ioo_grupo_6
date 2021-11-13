package ui.pacientes;

import ui.pacientes.models.Paciente;
import models.SexoEnum;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PacientesList{

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    public PacientesList() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        frame.setContentPane(new PacientesList().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));

        createPacientesList();
    }

    private void createPacientesList() {
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
