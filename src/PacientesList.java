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
    private JList<Paciente> pacientesList;

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
        //TODO: Traer pacientes cuando este implementado
        PacienteModel pacientes = createPacientes();
        pacientesList = new JList<>(pacientes);
        pacientesList.setCellRenderer(new PacienteViewHolder());
    }

    private PacienteModel createPacientes() {
        PacienteModel pacientes = new PacienteModel();
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        pacientes.addPaciente(
                new Paciente("Guido",42341208, "Siempre viva 742","perreguido@gmail.com", 21, SexoEnum.MASCULINO)
        );
        return pacientes;
    }

    private static class PacienteModel implements ListModel<Paciente> {

        private final ArrayList<Paciente> lista = new ArrayList<>();

        @Override
        public int getSize() {
            return lista.size();
        }

        @Override
        public Paciente getElementAt(int index) {
            return lista.get(index);
        }

        public void addPaciente(Paciente paciente) {
            lista.add(paciente);
        }

        @Override
        public void addListDataListener(ListDataListener l) {

        }

        @Override
        public void removeListDataListener(ListDataListener l) {

        }
    }

    private static class PacienteViewHolder extends JTextArea implements ListCellRenderer<Paciente> {

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Paciente> list,
                Paciente value,
                int index,
                boolean isSelected,
                boolean cellHasFocus
        ) {
            this.
            return this;
        }
    }
}
