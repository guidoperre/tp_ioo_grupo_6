package models;

public class Resultado extends Base {
    private float valor;
    private enum estadoEnum {
        pendiente,
        finalizada
    }
    private estadoEnum estado;
    private int codigoPractica;

    public Resultado(float valor, estadoEnum estado) {
        this.valor = valor;
        this.estado = estado;
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }

    public Boolean isActivo () {
        return this.estado != estadoEnum.finalizada;
    }

    public float getValor() {
        return valor;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }
}
