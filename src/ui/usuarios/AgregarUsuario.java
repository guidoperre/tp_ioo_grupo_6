package ui.usuarios;

import models.Rol;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
import utils.DataUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AgregarUsuario {
    private final JFrame frame = new JFrame("Agregar usuario");

    private JLabel title;
    private JLabel backButton;
    private JPanel panel;
    private JTextField nombreTextField;
    private JButton addButton;
    private JComboBox<Rol> rolSpinner;
    private JButton deleteButton;
    private JTextField dniTextField;
    private JTextField domicilioTextField;
    private JTextField fechaNacimientoTextField;
    private JTextField emailTextField;
    private JTextField usuarioTextField;
    private JTextField contrasenaTextField;

    private Usuario usuario;

    public AgregarUsuario() {
        init();
    }

    public AgregarUsuario(Usuario usuario) {
        this.usuario = usuario;
        init();
        initUsuario();
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

    private void initUsuario() {
        title.setText("Editar usuario");
        addButton.setText("Editar");
        deleteButton.setVisible(true);

        nombreTextField.setText(usuario.getNombre());
        dniTextField.setText(String.valueOf(usuario.getDni()));
        domicilioTextField.setText(usuario.getDomicilio());
        fechaNacimientoTextField.setText(DataUtils.getFechaFromDate(usuario.getFechaNacimiento()));
        emailTextField.setText(usuario.getMail());
        usuarioTextField.setText(usuario.getUsername());
        contrasenaTextField.setText(usuario.getPassword());
        rolSpinner.setSelectedItem(usuario.getRol());

        deleteListener();
        addListener();
    }

    private void createUIComponents() {
        addBackListener();
        setRol();
    }

    private Boolean checkFields() {
        String nombre = nombreTextField.getText();
        String dni = dniTextField.getText();
        String direccion = domicilioTextField.getText();
        String fechaNacimiento = fechaNacimientoTextField.getText();
        String email = emailTextField.getText();
        String usuario = usuarioTextField.getText();
        String contrasena = contrasenaTextField.getText();

        if (
                !nombre.equals("") &&
                !dni.equals("") &&
                !direccion.equals("") &&
                !fechaNacimiento.equals("") &&
                !email.equals("") &&
                !usuario.equals("") &&
                !contrasena.equals("") &&
                rolSpinner.getSelectedItem() != null
        ) {
            return true;
        } else if (DataUtils.getFechaFromString(fechaNacimiento) == null) {
            JOptionPane.showMessageDialog(frame, "EL FORMATO DE FECHA DEBE SER DD/MM/AAAA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
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
                new Usuarios();
            }
        });
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            if (checkFields()) {
                frame.dispose();
                if (usuario != null) {
                    usuario.setNombre(nombreTextField.getText());
                    usuario.setDni(Integer.parseInt(dniTextField.getText()));
                    usuario.setDomicilio(domicilioTextField.getText());
                    usuario.setFechaNacimiento(DataUtils.getFechaFromString(fechaNacimientoTextField.getText()));
                    usuario.setMail(emailTextField.getText());
                    usuario.setUsername(usuarioTextField.getText());
                    usuario.setPassword(contrasenaTextField.getText());
                    usuario.setRol((Rol) rolSpinner.getSelectedItem());
                } else {
                    UsuariosTable.addUsuario(new Usuario(
                            nombreTextField.getText(),
                            Integer.parseInt(dniTextField.getText()),
                            domicilioTextField.getText(),
                            emailTextField.getText(),
                            usuarioTextField.getText(),
                            contrasenaTextField.getText(),
                            DataUtils.getFechaFromString(fechaNacimientoTextField.getText()),
                            (Rol) rolSpinner.getSelectedItem()
                    ));
                }
                new Usuarios();
            }
        });
    }

    private void deleteListener() {
        deleteButton.addActionListener(e -> {
            frame.dispose();
            UsuariosTable.deleteUsuario(usuario);
            new Usuarios();
        });
    }

    private void setRol() {
        rolSpinner = new JComboBox<>();
        List<Rol> roles = List.of(Rol.values());
        DefaultComboBoxModel<Rol> rolItems = new DefaultComboBoxModel<>();
        rolItems.addAll(roles);
        rolSpinner.setModel(rolItems);
    }
}
