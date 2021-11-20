package ui.sucursales;

import ui.sucursales.model.SucursalDTO;

import javax.swing.*;
import java.awt.*;

public class SucursalesItem {
    private JPanel pacienteItem;
    private JLabel numeroSucursal;
    private JLabel direccionSucursal;
    private JLabel icon;
    private JLabel responsableSucursal;

    public SucursalesItem() {

    }

    public Component getPanel() {
        return pacienteItem;
    }

    public void setComponents(SucursalDTO sucursal) {
        numeroSucursal.setText("Sucursal " + Long.toString(sucursal.getId()));
        direccionSucursal.setText(sucursal.getDireccion());
        responsableSucursal.setText(sucursal.getResponsable().getNombre());
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/home.png"));
    }
}
