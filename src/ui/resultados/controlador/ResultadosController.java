package ui.resultados.controlador;

import ui.resultados.model.ResultadoDTO;

public class ResultadosController {

    private static ResultadosController instance;

    private ResultadoDTO resultado;

    public static ResultadosController getInstance() {
        if (instance == null) {
            instance = new ResultadosController();
        }
        return instance;
    }

    public void setResultado(ResultadoDTO resultado) {
        this.resultado = resultado;
    }

    public ResultadoDTO getResultado() {
        return resultado;
    }
}
