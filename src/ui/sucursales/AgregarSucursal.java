package ui.sucursales;

import app.Application;
import navigation.Screen;
import ui.usuarios.controlador.UsuarioDTO;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
import ui.sucursales.controlador.SucursalesController;
import ui.usuarios.controlador.UsuarioController;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarSucursal implements Screen {

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField direccionTextField;
    private JButton addButton;
    private JComboBox<UsuarioDTO> responsableSpinner;
    private JButton deleteButton;

    private Sucursal sucursal;

    public AgregarSucursal() {
        addListener();
    }

    public AgregarSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
        addListener();
        initSucursal();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void initSucursal() {
        title.setText("Editar sucursal");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        direccionTextField.setText(sucursal.getDireccion());
        responsableSpinner.setSelectedItem(sucursal.getResponsable());

        deleteListener();
    }

    private void createUIComponents() {
        addBackListener();
        setResponsable();
    }

    private Boolean checkFields() {
        String direccion = direccionTextField.getText();

        if (!direccion.equals("") && responsableSpinner.getSelectedItem() != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(panel, "DEBE COMPLETAR TODOS LOS CAMPOS", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new Sucursales());
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                if (sucursal != null) {
                    SucursalesController.setDireccion(sucursal, direccionTextField.getText());
                    SucursalesController.setResponsable(sucursal, (Usuario) responsableSpinner.getSelectedItem());
                    SucursalesController.modifySucursal(sucursal);
                } else {
                    SucursalesController.addSucursal(new Sucursal(
                            direccionTextField.getText(),
                            (Usuario) responsableSpinner.getSelectedItem()
                    ));
                }
                Application.manager.navigateTo(new Sucursales());
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            if (sucursal.getPeticionesFinalizadas().size() > 0) {
                JOptionPane.showMessageDialog(panel, "ESTA SUCURSAL NO PUEDE ELMINARSE PORQUE POSEE PETICIONES CON RESULTADOS FINALIZADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                Boolean movido = false;
                for (Sucursal sucursalItem: SucursalesController.getAllSucursales()) {
                    if (sucursalItem.getId() != SucursalesController.getId(sucursal)) {
                        SucursalesController.movePeticiones(sucursal, sucursalItem);
                        movido = true;
                        SucursalesController.deleteSucursal(sucursal);
                        JOptionPane.showMessageDialog(panel, "LAS PETICIONES ACTIVAS SE HAN MOVIDO A LA SUCURSAL: Sucursal " + sucursalItem.getId(), "CORRECTO", JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                }

                if (!movido) {
                    JOptionPane.showMessageDialog(panel, "ESTA SUCURSAL NO PUEDE ELMINARSE PORQUE NO HAY SUCURSALES DISPONIBLES PARA TRANSFERIR LAS PETICIONES", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            Application.manager.navigateTo(new Sucursales());
        });
    }

    private void setResponsable() {
        responsableSpinner = new JComboBox<>();
        List<UsuarioDTO> usuarios = UsuarioController.getAllUsuarios();
        DefaultComboBoxModel<UsuarioDTO> usuarioSpinner = new DefaultComboBoxModel<>();
        usuarioSpinner.addAll(usuarios);
        responsableSpinner.setModel(usuarioSpinner);
    }
}
