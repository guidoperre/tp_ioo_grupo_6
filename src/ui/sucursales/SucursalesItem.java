package ui.sucursales;

import ui.sucursales.model.Sucursal;
import ui.sucursales.controlador.SucursalesController;

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

    public void setComponents(Sucursal sucursal) {
        numeroSucursal.setText("Sucursal " + Long.toString(SucursalesController.getId(sucursal)));
        direccionSucursal.setText(SucursalesController.getDireccion(sucursal));
        responsableSucursal.setText(SucursalesController.getResponsable(sucursal));
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/home.png"));
    }
}
