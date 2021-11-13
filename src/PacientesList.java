import models.Paciente;
import models.SexoEnum;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;

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
        pacientes.addElement(new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
        pacientes.addElement(new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO));
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
            JPanel item = new JPanel();
            JLabel nombre = new JLabel(value.getNombre());
            JLabel documento = new JLabel(String.valueOf(value.getDni()));
            JLabel domicilio = new JLabel(value.getDomicilio());
            String pacienteSexo;
            if (value.getSexo() == SexoEnum.MASCULINO) {
                pacienteSexo = "Hombre";
            } else {
                pacienteSexo = "Mujer";
            }
            JLabel sexo = new JLabel(pacienteSexo);
            JLabel email = new JLabel(value.getMail());
            JLabel edad = new JLabel(String.valueOf(value.getEdad()));

            item.add(nombre);
            item.add(documento);
            item.add(domicilio);
            item.add(sexo);
            item.add(email);
            item.add(edad);

            return item;
        }
    }
}
