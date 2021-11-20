package ui.resultados.model;

import models.EstadoResultado;

public class Resultado extends ResultadoDTO {


    public Resultado() {
        super();
    }

    public Resultado(float valor, EstadoResultado estado, int codigoPractica) {
        super(valor, estado, codigoPractica);
    }

}
