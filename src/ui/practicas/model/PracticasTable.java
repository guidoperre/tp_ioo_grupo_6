package ui.practicas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PracticasTable {

    private static ArrayList<Practica> practicas;
    private static Long indexCount = 0L;

    public static void init() {
        practicas = new ArrayList<>();
    }

    public static List<Practica> getAllPracticas() {
        return practicas;
    }

    public static Practica getPracticas(int codigo) {
        for (Practica p: practicas) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public static void addPractica(Practica practica) {
        practica.setId(indexCount);
        practicas.add(practica);
        indexCount++;
    }

    public static void modifyPractica(Practica practica) {
        for (int i = 0; i < practicas.size(); i++) {
            Practica aux = practicas.get(i);
            if (Objects.equals(aux.getId(), practica.getId())) {
                practicas.remove(i);
                practicas.add(practica);
                break;
            }
        }
    }

    public static void deletePractica(Practica practica) {
        for (int i = 0; i < practicas.size(); i++) {
            Practica aux = practicas.get(i);
            if (Objects.equals(aux.getId(), practica.getId())) {
                practicas.remove(i);
                break;
            }
        }
    }
}
