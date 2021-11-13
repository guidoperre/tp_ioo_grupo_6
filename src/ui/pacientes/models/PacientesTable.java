package ui.pacientes.models;

import java.util.ArrayList;

public class PacientesTable {

    private static ArrayList<Paciente> pacientes;
    private static Long indexCount = 0L;

    public static void init() {
        pacientes = new ArrayList<>();
    }

    public static void addPaciente(Paciente paciente) {
        paciente.setId(indexCount);
        pacientes.add(paciente);
        indexCount++;
    }

    public static void modifyPaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente aux = pacientes.get(i);
            if (aux.getId() == paciente.getId()) {
                aux.setNombre(paciente.getNombre());
                aux.setDni(paciente.getDni());
                aux.setDomicilio(paciente.getDomicilio());
                aux.setEdad(paciente.getEdad());
                aux.setSexo(paciente.getSexo());
                aux.setMail(paciente.getMail());
                pacientes.remove(i);
                break;
            }
        }
    }

    public static void deletePaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente aux = pacientes.get(i);
            if (aux.getId() == paciente.getId()) {
                pacientes.remove(i);
                break;
            }
        }
    }
}
