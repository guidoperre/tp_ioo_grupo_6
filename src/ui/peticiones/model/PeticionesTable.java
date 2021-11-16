package ui.peticiones.model;

import ui.pacientes.models.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PeticionesTable {

    private static ArrayList<Peticion> peticiones;
    private static Long indexCount = 0L;

    public static void init() {
        peticiones = new ArrayList<>();
    }

    public static List<Peticion> getAllPeticiones() {
        return peticiones;
    }

    public static List<Peticion> getAllPeticionesPaciente(Paciente paciente) {
        List<Peticion> peticionesPaciente = new ArrayList<>();
        for (Peticion p: peticiones) {
            if (p.getPaciente().equals(paciente)) {
                peticionesPaciente.add(p);
            }
        }
        return peticionesPaciente;
    }

    public static List<Peticion> getPeticionesCriticas() {
        List<Peticion> aux = new ArrayList<>();
        for (Peticion peticion: peticiones) {
            if (peticion.isCritica()) {
                aux.add(peticion);
            }
        }
        return aux;
    }


    public static Peticion getPeticiones(String nombre) {
        for (Peticion p: peticiones) {
            if (p.getPaciente().getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    public static void addPeticiones(Peticion peticion) {
        peticion.setId(indexCount);
        peticiones.add(peticion);
        indexCount++;
    }

    public static void modifyPeticiones(Peticion peticion) {
        for (int i = 0; i < peticiones.size(); i++) {
            Peticion aux = peticiones.get(i);
            if (Objects.equals(aux.getId(), peticion.getId())) {
                peticiones.remove(i);
                peticiones.add(peticion);
                break;
            }
        }
    }

    public static void deletePeticiones(Peticion peticion) {
        for (int i = 0; i < peticiones.size(); i++) {
            Peticion aux = peticiones.get(i);
            if (Objects.equals(aux.getId(), peticion.getId())) {
                peticiones.remove(i);
                break;
            }
        }
    }
}
