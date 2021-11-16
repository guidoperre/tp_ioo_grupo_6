package ui.sucursales;

import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarSucursal {
    private final JFrame frame = new JFrame("Agregar Sucursal");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField direccionTextField;
    private JButton addButton;
    private JComboBox<Usuario> responsableSpinner;
    private JButton deleteButton;

    private Sucursal sucursal;

    public AgregarSucursal() {
        init();
    }

    public AgregarSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
        init();
        initSucursal();
    }

    // Inicializa la ventana
    private void init() {
        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();

        //Calculate the frame location
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        frame.setLocation(x, y);
        frame.setVisible(true);

        addListener();
    }

    private void initSucursal() {
        title.setText("Editar sucursal");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        direccionTextField.setText(sucursal.getDireccion());
        responsableSpinner.setSelectedItem(sucursal.getResponsable());

        deleteListener();
        addListener();
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
            JOptionPane.showMessageDialog(frame, "DEBE COMPLETAR TODOS LOS CAMPOS", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Sucursales();
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                frame.dispose();
                if (sucursal != null) {
                    sucursal.setDireccion(direccionTextField.getText());
                    sucursal.setResponsable((Usuario) responsableSpinner.getSelectedItem());
                } else {
                    SucursalesTable.addSucursal(new Sucursal(
                            direccionTextField.getText(),
                            (Usuario) responsableSpinner.getSelectedItem()
                    ));
                }
                new Sucursales();
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            frame.dispose();
            if (sucursal.getPeticionesFinalizadas().size() > 0) {
                JOptionPane.showMessageDialog(frame, "ESTA SUCURSAL NO PUEDE ELMINARSE PORQUE POSEE PETICIONES CON RESULTADOS FINALIZADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                SucursalesTable.deleteSucursal(sucursal);
                // TODO: Derivar sucursale
            }
            new Sucursales();
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
