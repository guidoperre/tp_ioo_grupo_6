package ui.practicas.controlador;

import models.Regla;
import ui.pacientes.controlador.PacienteControler;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
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

    public void setPractica(PracticaDTO practica) {
        this.practica = practica;
    }

    public PracticaDTO getPractica() {
        return practica;
    }

    public List<PracticaDTO> getAllPracticas() {
        return PracticasTable.getAllPracticas();
    }

    public List<Regla> getValoresReservados() {
        return practica.getValoresReservados();
    }

    public List<Regla> getValoresCriticos() {
        return practica.getValoresCriticos();
    }

    public void addValorReservado(Regla valorReservado) {
        practica.addValorReservado(valorReservado);
    }

    public void removeValorReservado(Regla valorReservado) {
        practica.removeValorReservado(valorReservado);
    }

    public void addValorCritico(Regla valorCritico) {
        practica.addValorCritico(valorCritico);
    }

    public void removeValorCritico(Regla valorCritico) {
        practica.removeValorCritico(valorCritico);
    }

    public void setValoresCriticos(List<Regla> valoresCriticos) {
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
