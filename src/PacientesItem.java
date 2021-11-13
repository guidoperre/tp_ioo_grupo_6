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

    public Component getPanel() {
        return pacienteItem;
    }

    public void setComponents(Paciente paciente) {
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
