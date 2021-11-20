package ui.resultados;

import app.Application;
import navigation.Screen;
import ui.home.Home;
import ui.pacientes.controlador.PacienteControler;
import ui.pacientes.models.PacienteDTO;
import ui.peticiones.controlador.PeticionController;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.PracticaDTO;
import ui.practicas.model.PracticasTable;
import ui.resultados.model.ResultadoDTO;
import ui.resultados.controlador.ResultadosController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Resultados implements Screen {

    private JLabel backButton;
    private JLabel addPaciente;
    private JPanel panel;
    private JPanel listPanel;
    private JComboBox<PacienteDTO> pacienteSpinner;
    private JList<ResultadoDTO> list;

    private ResultadosController resultadosController = ResultadosController.getInstance();
    private PacienteControler pacienteController = PacienteControler.getInstance();
    private PeticionController peticionController = PeticionController.getInstance();

    public Resultados() {
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        resultadosController = ResultadosController.getInstance();
        pacienteController = PacienteControler.getInstance();
        peticionController = PeticionController.getInstance();

        addBackListener();
        addPeticionesListener();
        showResultados();
        setPacientesSpinner();
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

    private void addPeticionesListener() {
        addPaciente = new JLabel(new ImageIcon("resources/add.png"));
        addPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resultadosController.setResultado(new ResultadoDTO());
                Application.manager.navigateTo(new AgregarResultado());
            }
        });
    }

    private void setPacientesSpinner() {
        pacienteSpinner = new JComboBox<>();
        List<PacienteDTO> pacientes = pacienteController.getAllPacientes();
        DefaultComboBoxModel<PacienteDTO> pacientesItem = new DefaultComboBoxModel<>();
        pacientesItem.addAll(pacientes);
        pacienteSpinner.setModel(pacientesItem);

        pacienteSpinner.addItemListener(e -> {
            PacienteDTO paciente = (PacienteDTO) e.getItem();
            list.setModel(getResultados(paciente));
        });
    }

    private void showResultados() {
        listPanel = new JPanel();

        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setCellRenderer(new ResultadosViewHolder());
        list.setSize(300,300);
        listListener(list);

        listPanel.add(list);
    }

    private void listListener(JList<ResultadoDTO> list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList target = (JList) me.getSource();
                int index = target.locationToIndex(me.getPoint());
                if (index >= 0) {
                    ResultadoDTO resultado = (ResultadoDTO) target.getModel().getElementAt(index);
                    PracticaDTO practica = PracticasTable.getPracticas(resultado.getCodigoPractica());
                    if (practica != null) {
                        if (practica.isValorReservado(resultado.getValor())) {
                            JOptionPane.showMessageDialog(
                                    panel,
                                    "RETIRAR POR SUCURSAL",
                                    "ERROR",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            resultadosController.setResultado(resultado);
                            Application.manager.navigateTo(
                                new AgregarResultado()
                            );
                        }
                    }
                }
            }
        });
    }

    private ListModel<ResultadoDTO> getResultados(PacienteDTO paciente) {
        List<PeticionDTO> peticiones = peticionController.getAllPeticionesPaciente(paciente);
        List<ResultadoDTO> resultados = new ArrayList<>();
        for (PeticionDTO p: peticiones) {
            resultados.addAll(p.getResultados());
        }

        DefaultListModel<ResultadoDTO> resultadosModel = new DefaultListModel<>();
        resultadosModel.addAll(resultados);
        return resultadosModel;
    }

    static class ResultadosViewHolder extends JPanel implements ListCellRenderer<ResultadoDTO> {

        public ResultadosViewHolder() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            JList<? extends ResultadoDTO> list,
            ResultadoDTO value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            ResultadosItem item = new ResultadosItem();
            item.setComponents(value);
            return item.getPanel();
        }
    }
}
