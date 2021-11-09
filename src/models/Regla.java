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
        super();
        this.min = min;
        this.max = max;
        this.operador = operador;
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }

    public Boolean isValorCritico(float valor) {
        switch (this.operador) {
            case mayorigual -> {
                if (this.max >= valor)
                    return true;
            } case mayor -> {
                if (this.max > valor)
                    return true;
            } case menorigual -> {
                if (this.min <= valor)
                    return true;
            } case menor -> {
                if (this.min < valor)
                    return true;
            } case igual -> {
                if (this.min == valor && this.max == valor)
                    return true;
            } case entre -> {
                if (this.min >= valor && this.max <= valor)
                    return true;
            } default -> {
                return false;
            }
        }
        return false;
    }
}
