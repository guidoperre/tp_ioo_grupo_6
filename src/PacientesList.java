import models.Paciente;
import models.SexoEnum;

import javax.swing.*;
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
            JPanel item = new JPanel();
            GridLayout mainLayout = new GridLayout(5,2);
            item.setLayout(mainLayout);

            JLabel icon = new JLabel(new ImageIcon("resources/usuarios.png"));
            item.add(icon, 0, 0);


            JLabel nombre = new JLabel(value.getNombre());
            JLabel documento = new JLabel(String.valueOf(value.getDni()));
            JLabel domicilio = new JLabel(value.getDomicilio());
            JLabel sexo = new JLabel(value.getSexo().name());
            JLabel email = new JLabel(value.getMail());
            JLabel edad = new JLabel(String.valueOf(value.getEdad()));

            item.add(icon, 1, 0);
            item.add(nombre, 1, 0);
            item.add(documento, 1, 0);
            item.add(domicilio, 1, 0);
            item.add(sexo, 1, 0);
            item.add(email, 1, 0);
            item.add(edad, 1, 0);

            return item;
        }
    }
}
