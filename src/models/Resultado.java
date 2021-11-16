package models;

public class Resultado extends Base {
    private float valor;
    public enum estadoEnum {
        pendiente,
        finalizada
    }
    private estadoEnum estado;
    private int codigoPractica;

    public Resultado(float valor, estadoEnum estado, int codigoPractica) {
        super();
        this.valor = valor;
        this.estado = estado;
        this.codigoPractica = codigoPractica;
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

    public Boolean isFinalizada () {
        return this.estado == estadoEnum.finalizada;
    }

    public float getValor() {
        return valor;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }
}
