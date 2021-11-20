package ui.practicas.controlador;

import models.Regla;
import ui.pacientes.controlador.PacienteControler;
import ui.pacientes.models.Paciente;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.practicas.model.PracticasTable;
import ui.usuarios.model.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class PracticaController {
    private static PracticaController instance;

    PracticaDTO practica;

    private PracticaController() {
        // no-op
    }

    public static PracticaController getInstance() {
        if (instance == null) {
            instance = new PracticaController();
        }
        return instance;
    }

    public List<PracticaDTO> getAllPracticas() {
        return PracticasTable.getAllPracticas();
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

    public void addPractica(String nombre, int horas, boolean activo){
        if(practica.getId() != null){
            practica.setNombre(nombre);
            practica.setHoras(horas);
            practica.setActivo(activo);
        } else {
            PracticasTable.addPractica(
                    new PracticaDTO(
                            0, activo, nombre, horas, new ArrayList<>(), new ArrayList<>()
                    )
                );
        }
    }
}
