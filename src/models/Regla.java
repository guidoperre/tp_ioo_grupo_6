package models;

public class Regla extends Base {
    private float min;
    private float max;
    private enum operadorEnum {
        mayorigual,
        mayor,
        menorigual,
        menor,
        igual,
        entre
    }
    private operadorEnum operador;

    public Regla(float min, float max, operadorEnum operador) {
        this.min = min;
        this.max = max;
        this.operador = operador;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
