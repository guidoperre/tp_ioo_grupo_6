package ui.peticiones.model;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PeticionesTable {

    private static ArrayList<PeticionDTO> peticiones;
    private static Long indexCount = 0L;

    public static void init() {
        peticiones = new ArrayList<>();
    }

    public static List<PeticionDTO> getAllPeticiones() {
        return peticiones;
    }

    public static List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        List<PeticionDTO> peticionesPaciente = new ArrayList<>();
        for (PeticionDTO p: peticiones) {
            if (p.getPaciente().equals(paciente)) {
                peticionesPaciente.add(p);
            }
        }
        return peticionesPaciente;
    }

    public static List<PeticionDTO> getPeticionesCriticas() {
        List<PeticionDTO> aux = new ArrayList<>();
        for (PeticionDTO peticion: peticiones) {
            if (new Peticion(
                    new Paciente(peticion.getPaciente().getNombre(), peticion.getPaciente().getDni(), peticion.getPaciente().getDomicilio(), peticion.getPaciente().getMail(), peticion.getPaciente().getEdad(), peticion.getPaciente().getSexo()),
                    peticion.getObraSocial(),
                    peticion.getSucursal(),
                    peticion.getFechaCarga(),
                    peticion.getFechaEntrega(),
                    peticion.getPracticas(),
                    peticion.getResultados()
            ).isCritica()) {
                aux.add(peticion);
            }
        }
        return aux;
    }


    public static PeticionDTO getPeticiones(String nombre) {
        for (PeticionDTO p: peticiones) {
            if (p.getPaciente().getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    public static void addPeticiones(PeticionDTO peticion) {
        peticion.setId(indexCount);
        peticiones.add(peticion);
        indexCount++;
    }

    public static void modifyPeticiones(PeticionDTO peticion) {
        for (int i = 0; i < peticiones.size(); i++) {
            PeticionDTO aux = peticiones.get(i);
            if (Objects.equals(aux.getId(), peticion.getId())) {
                peticiones.remove(i);
                peticiones.add(peticion);
                return;
            }
        }
    }

    public static void deletePeticiones(PeticionDTO peticion) {
        for (int i = 0; i < peticiones.size(); i++) {
            PeticionDTO aux = peticiones.get(i);
            if (Objects.equals(aux.getId(), peticion.getId())) {
                peticiones.remove(i);
                break;
            }
        }
    }
}
