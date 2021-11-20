package ui.peticiones.controlador;

import models.EstadoResultado;
import models.Rol;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.PracticaDTO;
import ui.resultados.model.ResultadoDTO;
import ui.sucursales.model.SucursalDTO;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class PeticionController {

    private static PeticionController instance;

    PeticionDTO peticion;

    private PeticionController() {
        // no-op
    }

    public static PeticionController getInstance() {
        if (instance == null) {
            instance = new PeticionController();
        }
        return instance;
    }

    public void setPeticion(PeticionDTO peticion) {
        this.peticion = peticion;
    }

    public PeticionDTO getPeticion() {
        return peticion;
    }

    public List<PeticionDTO> getPeticionesCriticas(){
        return PeticionesTable.getPeticionesCriticas();
    }

    public List<PeticionDTO> getAllPeticiones() {
        return PeticionesTable.getAllPeticiones();
    }

    public void addPeticion(String obraSocial, PacienteDTO paciente, SucursalDTO sucursal) {
        peticion.setObraSocial(obraSocial);
        peticion.setPaciente(paciente);
        peticion.setSucursal(sucursal);
        if (peticion.getId() != null) {
            PeticionesTable.modifyPeticiones(peticion);
        } else {
            peticion.setFechaCarga(new Date());
            peticion.setFechaEntrega(new Date());
            PeticionesTable.addPeticiones(peticion);
        }
    }

    public void modifyPeticion(
        ResultadoDTO resultado,
        PeticionDTO peticion,
        float valor,
        int codigo,
        EstadoResultado estado
    ) {
        ResultadoDTO resultadoNuevo = resultado;

        resultadoNuevo.setValor(valor);
        resultadoNuevo.setCodigoPractica(codigo);
        resultadoNuevo.setEstado(estado);

        if (this.peticion != null) {
            this.peticion.removeResultado(resultado);
            this.peticion.addResultado(resultadoNuevo);
            PeticionesTable.modifyPeticiones(this.peticion);
        } else {
            if (peticion != null) {
                peticion.addResultado(resultadoNuevo);
                PeticionesTable.modifyPeticiones(peticion);
            }
        }
    }

    public List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        return PeticionesTable.getAllPeticionesPaciente(paciente);
    }

    public void deletePeticion() {
        PeticionesTable.deletePeticiones(peticion);
    }
}
