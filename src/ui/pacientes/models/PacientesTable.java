package ui.pacientes.models;

import java.util.ArrayList;
import java.util.List;

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
