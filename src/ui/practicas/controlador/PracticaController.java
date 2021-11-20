package ui.practicas.controlador;

import models.Regla;
import ui.pacientes.controlador.PacienteControler;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;

import java.util.List;

public class PracticaController {
    private static PracticaController instance;
    private PracticaController() {
        // no-op
    }

    public static PracticaController getInstance() {
        if (instance == null) {
            instance = new PracticaController();
        }
        return instance;
    }

    public List<Regla> getValoresReservados(PracticaDTO practica) {
        return practica.getValoresReservados();
    }

    public List<Regla> getValoresCriticos(PracticaDTO practica) {
        return practica.getValoresCriticos();
    }

    public void addValorReservado(PracticaDTO practica, Regla valorReservado) {
        practica.addValorReservado(valorReservado);
    }

    public void removeValorReservado(PracticaDTO practica, Regla valorReservado) {
        practica.removeValorReservado(valorReservado);
    }

    public void addValorCritico(PracticaDTO practica, Regla valorCritico) {
        practica.addValorCritico(valorCritico);
    }

    public void removeValorCritico(PracticaDTO practica, Regla valorCritico) {
        practica.removeValorCritico(valorCritico);
    }

    public void setValoresCriticos(PracticaDTO practica, List<Regla> valoresCriticos) {
       practica.setValoresCriticos(valoresCriticos);
    }
}
