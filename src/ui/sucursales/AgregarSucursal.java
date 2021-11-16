package ui.sucursales;

import app.Application;
import navigation.Screen;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
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
    private JComboBox<Usuario> responsableSpinner;
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
                    sucursal.setDireccion(direccionTextField.getText());
                    sucursal.setResponsable((Usuario) responsableSpinner.getSelectedItem());
                } else {
                    SucursalesTable.addSucursal(new Sucursal(
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
                SucursalesTable.deleteSucursal(sucursal);
                // TODO: Derivar sucursale
            }
            Application.manager.navigateTo(new Sucursales());
        });
    }

    private void setResponsable() {
        responsableSpinner = new JComboBox<>();
        List<Usuario> usuarios = UsuariosTable.getAllUsuarios();
        DefaultComboBoxModel<Usuario> usuarioSpinner = new DefaultComboBoxModel<>();
        usuarioSpinner.addAll(usuarios);
        responsableSpinner.setModel(usuarioSpinner);
    }
}
