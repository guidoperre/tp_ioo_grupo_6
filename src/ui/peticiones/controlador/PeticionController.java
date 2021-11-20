package ui.peticiones.controlador;

import models.Rol;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.sucursales.model.SucursalDTO;

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

    public void modifyPeticion(PeticionDTO peticion) {
        PeticionesTable.modifyPeticiones(peticion);
    }

    public List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        return PeticionesTable.getAllPeticionesPaciente(paciente);
    }

    public void deletePeticion() {
        PeticionesTable.deletePeticiones(peticion);
    }
}
