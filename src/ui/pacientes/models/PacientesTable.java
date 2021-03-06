package ui.pacientes.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacientesTable {

    private static ArrayList<Paciente> pacientes;
    private static Long indexCount = 0L;

    public static void init() {
        pacientes = new ArrayList<>();
    }

    public static List<Paciente> getAllPacientes() {
        return pacientes;
    }

    public static Paciente getPaciente(String nombre) {
        for (Paciente p: pacientes) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }


    public static void addPaciente(Paciente paciente) {
        paciente.setId(indexCount);
        pacientes.add(paciente);
        indexCount++;
    }

    public static void modifyPaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente aux = pacientes.get(i);
            if (Objects.equals(aux.getId(), paciente.getId())) {
                pacientes.remove(i);
                pacientes.add(paciente);
                break;
            }
        }
    }

    public static void deletePaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente aux = pacientes.get(i);
            if (Objects.equals(aux.getId(), paciente.getId())) {
                pacientes.remove(i);
                break;
            }
        }
    }
}
