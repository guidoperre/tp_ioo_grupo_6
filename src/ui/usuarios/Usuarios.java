package ui.usuarios;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.usuarios.controlador.UsuarioDTO;
import ui.usuarios.model.Usuario;
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

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public Usuarios() {

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
                Application.manager.navigateTo(new Home());
            }
        });
    }

    private void addUsuariosListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new AgregarUsuario());
            }
        });
    }

    private void showUsuarios() {
        listPanel = new JPanel();
        ListModel<UsuarioDTO> usuarios = getUsuarios();

        JList<UsuarioDTO> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new UsuariosViewHolder());
        list.setModel(usuarios);
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<UsuarioDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    Application.manager.navigateTo(new AgregarUsuario((UsuarioDTO) target.getModel().getElementAt(index)));
                }
            }
        });
    }

    private ListModel<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> usuarios = UsuarioController.getAllUsuarios();
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
