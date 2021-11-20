package ui.usuarios;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.usuarios.model.UsuarioDTO;
import ui.usuarios.controlador.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Usuarios implements Screen {

    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;
    private JList<UsuarioDTO> usuariosList;

    final private UsuarioController controller;

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Usuarios() {
        controller = UsuarioController.getInstance();
        usuariosList.setModel(getUsuarios());
    }

    private void createUIComponents() {
        addBackListener();
        addUsuariosListener();
        setUsuariosList();
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.setUsuario(null);
                Application.manager.navigateTo(new Home());
            }
        });
    }

    private void addUsuariosListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.setUsuario(null);
                Application.manager.navigateTo(new AgregarUsuario());
            }
        });
    }

    private void setUsuariosList() {
        listPanel = new JPanel();
        usuariosList = new JList<>();
        usuariosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usuariosList.setLayoutOrientation(JList.VERTICAL);
        usuariosList.setCellRenderer(new UsuariosViewHolder());
        usuariosList.setSize(300,300);
        listListener(usuariosList);
        listPanel.add(usuariosList);
    }

    private void listListener(JList<UsuarioDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    controller.setUsuario((UsuarioDTO) target.getModel().getElementAt(index));
                    Application.manager.navigateTo(new AgregarUsuario());
                }
            }
        });
    }

    private ListModel<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> usuarios = controller.getAllUsuarios();
        DefaultListModel<UsuarioDTO> usuariosModel = new DefaultListModel<>();
        usuariosModel.addAll(usuarios);
        return usuariosModel;
    }

    static class UsuariosViewHolder extends JPanel implements ListCellRenderer<UsuarioDTO> {

        public UsuariosViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends UsuarioDTO> list,
            UsuarioDTO value,
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
