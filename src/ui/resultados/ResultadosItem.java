package ui.resultados;

import ui.peticiones.model.Peticion;
import utils.DataUtils;

import javax.swing.*;
import java.awt.*;

public class ResultadosItem {
    private JPanel pacienteItem;
    private JLabel nombrePeticion;
    private JLabel fechaCargaPeticion;
    private JLabel icon;
    private JLabel obraSocialPeticion;
    private JLabel fechaEntregaPeticion;

    public ResultadosItem() {

    }

    public Component getPanel() {
        return pacienteItem;
    }

    public void setComponents(Peticion peticion) {
        nombrePeticion.setText(peticion.getPaciente().getNombre());
        obraSocialPeticion.setText(peticion.getObraSocial());
        fechaCargaPeticion.setText("Fecha de carga: " + DataUtils.getFechaFromDate(peticion.getFechaCarga()));
        fechaEntregaPeticion.setText("Fecha de entrega: " + DataUtils.getFechaFromDate(peticion.getFechaEntrega()));
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/peticiones.png"));
    }
}
