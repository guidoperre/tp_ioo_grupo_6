package ui.practicas;

import ui.practicas.model.Practica;
import utils.DataUtils;

import javax.swing.*;
import java.awt.*;

public class PracticasItem {
    private JPanel practicaItem;
    private JLabel nombrePractica;
    private JLabel valoresCriticosPractica;
    private JLabel valoresReservadosPractica;
    private JLabel activoPractica;
    private JLabel grupoPractica;

    private JLabel icon;

    public PracticasItem() {

    }

    public Component getPanel() {
        return practicaItem;
    }

    public void setComponents(Practica practica) {
        nombrePractica.setText(practica.getNombre());
        }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/practicas.png"));
    }
}
