package ui.usuarios;

import ui.home.Home;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Usuarios {
    private final JFrame frame = new JFrame("Usuarios");

    private JLabel title;
    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;

    public Usuarios() {
        init();
    }

    // Inicializa la ventana
    public void init() {
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
    }

    private void createUIComponents() {
        addBackListener();
        addUsuariosListener();
        showUsuarios();
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Home();
            }
        });
    }

    private void addUsuariosListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new AgregarUsuario();
            }
        });
    }

    private void showUsuarios() {
        listPanel = new JPanel();
        ListModel<Usuario> usuarios = getUsuarios();

        JList<Usuario> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new UsuariosViewHolder());
        list.setModel(usuarios);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<Usuario> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    frame.dispose();
                    new AgregarUsuario((Usuario) target.getModel().getElementAt(index));
                }
            }
        });
    }

    private ListModel<Usuario> getUsuarios() {
        List<Usuario> usuarios = UsuariosTable.getAllUsuarios();
        DefaultListModel<Usuario> usuariosModel = new DefaultListModel<>();
        usuariosModel.addAll(usuarios);
        return usuariosModel;
    }

    static class UsuariosViewHolder extends JPanel implements ListCellRenderer<Usuario> {

        public UsuariosViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends Usuario> list,
            Usuario value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            UsuariosItem item = new UsuariosItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
