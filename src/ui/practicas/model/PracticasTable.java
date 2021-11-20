package ui.practicas.model;

import ui.pacientes.models.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PracticasTable {

    private static ArrayList<PracticaDTO> practicas;
    private static Long indexCount = 0L;

    public static void init() {
        practicas = new ArrayList<>();
    }

    public static List<PracticaDTO> getAllPracticas() {
        return practicas;
    }

    public static PracticaDTO getPracticas(int codigo) {
        for (PracticaDTO p: practicas) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public static void addPractica(PracticaDTO practica) {
        practica.setId(indexCount);
        practicas.add(practica);
        indexCount++;
    }

    public static void modifyPractica(PracticaDTO practica) {
        for (int i = 0; i < practicas.size(); i++) {
            PracticaDTO aux = practicas.get(i);
            if (Objects.equals(aux.getId(), practica.getId())) {
                practicas.remove(i);
                practicas.add(practica);
                break;
            }
        }
    }

    public static void deletePractica(PracticaDTO practica) {
        for (int i = 0; i < practicas.size(); i++) {
            PracticaDTO aux = practicas.get(i);
            if (Objects.equals(aux.getId(), practica.getId())) {
                practicas.remove(i);
                break;
            }
        }
    }
}
