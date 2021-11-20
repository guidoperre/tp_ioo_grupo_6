package ui.resultados;

import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;

import javax.swing.*;
import java.awt.*;

public class ResultadosItem {
    private JPanel pacienteItem;
    private JLabel nombrePractica;
    private JLabel icon;
    private JLabel estadoResultado;

    public ResultadosItem() {

    }

    public Component getPanel() {
        return pacienteItem;
    }

    public void setComponents(ResultadoDTO resultado) {
        Practica practica = PracticasTable.getPracticas(resultado.getCodigoPractica());
        if (practica != null) {
            nombrePractica.setText(practica.getNombre());
        }
        estadoResultado.setText(resultado.getEstado().toString());
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("resources/resultados.png"));
    }
}
