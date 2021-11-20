package ui.peticiones;

import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import utils.DataUtils;

import javax.swing.*;
import java.awt.*;

public class PeticionesItem {
    private JPanel pacienteItem;
    private JLabel nombrePeticion;
    private JLabel fechaCargaPeticion;
    private JLabel icon;
    private JLabel obraSocialPeticion;
    private JLabel fechaEntregaPeticion;

    public PeticionesItem() {

    }

    public Component getPanel() {
        return pacienteItem;
    }

    public void setComponents(PeticionDTO peticion) {
        nombrePeticion.setText(peticion.getPaciente().getNombre());
        obraSocialPeticion.setText(peticion.getObraSocial());
        fechaCargaPeticion.setText("Fecha de carga: " + DataUtils.getFechaFromDate(peticion.getFechaCarga()));
        fechaEntregaPeticion.setText("Fecha de entrega: " + DataUtils.getFechaFromDate(peticion.getFechaEntrega()));
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/peticiones.png"));
    }
}
