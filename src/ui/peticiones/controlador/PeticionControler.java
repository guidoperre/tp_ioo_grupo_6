package ui.peticiones.controlador;

import ui.pacientes.models.PacienteDTO;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;


import java.util.Date;
import java.util.List;

public class PeticionControler {
    public PeticionControler() {
        // no-op
    }

    public List<PeticionDTO> getPeticionesCriticas(){
        return PeticionesTable.getPeticionesCriticas();
    }

    public List<PeticionDTO> getPeticiones(){
        return PeticionesTable.getAllPeticiones();
    }

    public List<PeticionDTO> getAllPeticiones() {
        return getPeticiones();
    }

    public void modifyPeticiones(PeticionDTO peticion) {
        PeticionesTable.modifyPeticiones(peticion);
    }

    public List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        return PeticionesTable.getAllPeticionesPaciente(paciente);
    }
}
