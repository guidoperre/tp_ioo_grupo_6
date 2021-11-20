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

    public static List<PeticionDTO> getPeticionesCriticas(){
        return PeticionesTable.getPeticionesCriticas();
    }

    public static List<PeticionDTO> getPeticiones(){
        return PeticionesTable.getAllPeticiones();
    }

    public static List<PeticionDTO> getAllPeticiones() {
        return getPeticiones();
    }

    public static void modifyPeticiones(PeticionDTO peticion) {
        PeticionesTable.modifyPeticiones(peticion);
    }

    public static List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        return PeticionesTable.getAllPeticionesPaciente(paciente);
    }
}
