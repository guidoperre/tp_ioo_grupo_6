package ui.peticiones.controlador;

import ui.pacientes.models.PacienteDTO;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.usuarios.model.UsuarioDTO;

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

    public void modifyPeticiones(PeticionDTO peticion) {
        PeticionesTable.modifyPeticiones(peticion);
    }

    public List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        return PeticionesTable.getAllPeticionesPaciente(paciente);
    }

    public void deletePeticion() {
        PeticionesTable.deletePeticiones(peticion);
    }
}
