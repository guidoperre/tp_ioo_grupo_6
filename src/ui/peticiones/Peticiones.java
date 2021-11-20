package ui.peticiones;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.peticiones.controlador.PeticionController;
import ui.peticiones.model.PeticionDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Peticiones implements Screen {

    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;
    private JCheckBox criticosCheckBox;
    private JList<PeticionDTO> list;

    final private PeticionController controllerPeticion;

    public Peticiones() {
        controllerPeticion = PeticionController.getInstance();
        list.setModel(getPeticiones());
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        addBackListener();
        addPeticionesListener();
        showPeticiones();
        setCriticosCheckBox();
    }

    private void setCriticosCheckBox() {
        criticosCheckBox = new JCheckBox();
        criticosCheckBox.setText("Solo valores criticos");

        criticosCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                list.setModel(getPeticionesCriticas());
            } else {
                list.setModel(getPeticiones());
            }
        });
    }

    private void addBackListener() {
        backButton = new JLabel(new ImageIcon("resources/atras.png"));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controllerPeticion.setPeticion(null);
                Application.manager.navigateTo(new Home());
            }
        });
    }

    private void addPeticionesListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controllerPeticion.setPeticion(null);
                Application.manager.navigateTo(new AgregarPeticion());
            }
        });
    }

    private void showPeticiones() {
        listPanel = new JPanel();

        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new PeticionesViewHolder());
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<PeticionDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    controllerPeticion.setPeticion((PeticionDTO) target.getModel().getElementAt(index));
                    Application.manager.navigateTo(new AgregarPeticion());
                }
            }
        });
    }

    private ListModel<PeticionDTO> getPeticiones() {
        List<PeticionDTO> peticiones = controllerPeticion.getAllPeticiones();
        DefaultListModel<PeticionDTO> peticionesModel = new DefaultListModel<>();
        peticionesModel.addAll(peticiones);
        return peticionesModel;
    }

    private ListModel<PeticionDTO> getPeticionesCriticas() {
        List<PeticionDTO> peticiones = controllerPeticion.getPeticionesCriticas();
        DefaultListModel<PeticionDTO> peticionesModel = new DefaultListModel<>();
        peticionesModel.addAll(peticiones);
        return peticionesModel;
    }

    static class PeticionesViewHolder extends JPanel implements ListCellRenderer<PeticionDTO> {

        public PeticionesViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends PeticionDTO> list,
            PeticionDTO value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            PeticionesItem item = new PeticionesItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
