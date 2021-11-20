package ui.pacientes.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacientesTable {

    private static ArrayList<PacienteDTO> pacientes;
    private static Long indexCount = 0L;

    public static void init() {
        pacientes = new ArrayList<>();
    }

    public static List<PacienteDTO> getAllPacientes() {
        return pacientes;
    }

    public static PacienteDTO getPaciente(String nombre) {
        for (PacienteDTO p: pacientes) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    public static void addPaciente(PacienteDTO paciente) {
        paciente.setId(indexCount);
        pacientes.add(paciente);
        indexCount++;
    }

    public static void modifyPaciente(PacienteDTO paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            PacienteDTO aux = pacientes.get(i);
            if (Objects.equals(aux.getId(), paciente.getId())) {
                pacientes.remove(i);
                pacientes.add(paciente);
                break;
            }
        }
    }

    public static void deletePaciente(PacienteDTO paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            PacienteDTO aux = pacientes.get(i);
            if (Objects.equals(aux.getId(), paciente.getId())) {
                pacientes.remove(i);
                break;
            }
        }
    }
}
