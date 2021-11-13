import models.Paciente;

import javax.swing.*;
import java.awt.*;

public class PacientesItem {
    private JPanel pacienteItem;
    private JLabel nombrePaciente;
    private JLabel documentoPaciente;
    private JLabel domicilioPaciente;
    private JLabel sexoPaciente;
    private JLabel edadPaciente;
    private JLabel emailPaciente;
    private JLabel icon;

    public PacientesItem() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        frame.setContentPane(new PacientesItem().pacienteItem);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();
        frame.setVisible(true);
    }

    private void setComponents(Paciente paciente) {
        nombrePaciente.setText(paciente.getNombre());
        documentoPaciente.setText(String.valueOf(paciente.getDni()));
        domicilioPaciente.setText(paciente.getDomicilio());
        sexoPaciente.setText(paciente.getSexo().toString());
        edadPaciente.setText(paciente.getEdad() + " años");
        emailPaciente.setText(paciente.getMail());
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/usuarios.png"));
    }
}
