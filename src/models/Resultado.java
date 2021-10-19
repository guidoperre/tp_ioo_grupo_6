package models;

public class Resultado extends Base {
    private float valor;
    private enum estadoEnum {
        pendiente,
        finalizada
    }
    private estadoEnum estado;

    public Resultado(float valor, estadoEnum estado) {
        this.valor = valor;
        this.estado = estado;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
