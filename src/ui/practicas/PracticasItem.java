package ui.practicas;

import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;

import javax.swing.*;
import java.awt.*;

public class PracticasItem {
    private JPanel practicaItem;
    private JLabel nombrePractica;
    private JLabel codigoPractica;
    private JLabel activoPractica;
    private JLabel demoraPractica;

    private JLabel icon;

    public PracticasItem() {

    }

    public Component getPanel() {
        return practicaItem;
    }

    public void setComponents(PracticaDTO practica) {
        nombrePractica.setText(practica.getNombre());
        codigoPractica.setText("Codigo " + practica.getCodigo());
        if (practica.getActivo()) {
            activoPractica.setText("ACTIVA");
        } else {
            activoPractica.setText("INACTIVA");
        }
        demoraPractica.setText("Demora " + practica.getHoras() + " horas");
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/practicas.png"));
    }
}
