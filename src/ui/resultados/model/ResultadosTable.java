package ui.resultados.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultadosTable {

    private static ArrayList<Resultado> resultados;
    private static Long indexCount = 0L;

    public static void init() {
        resultados = new ArrayList<>();
    }

    public static List<Resultado> getAllResultados() {
        return resultados;
    }

    public static Resultado getResultado(int codigoPractica) {
        for (Resultado p: resultados) {
            if (p.getCodigoPractica() == codigoPractica) {
                return p;
            }
        }
        return null;
    }

    public static void addResultado(Resultado resultado) {
        resultado.setId(indexCount);
        resultados.add(resultado);
        indexCount++;
    }

    public static void modifyResultado(Resultado resultado) {
        for (int i = 0; i < resultados.size(); i++) {
            Resultado aux = resultados.get(i);
            if (Objects.equals(aux.getId(), resultado.getId())) {
                resultados.remove(i);
                resultados.add(resultado);
                break;
            }
        }
    }

    public static void deleteResultado(Resultado resultado) {
        for (int i = 0; i < resultados.size(); i++) {
            Resultado aux = resultados.get(i);
            if (Objects.equals(aux.getId(), resultado.getId())) {
                resultados.remove(i);
                break;
            }
        }
    }
}
