package models;

public class Regla {

    private Long id;
    private float min;
    private float max;
    private OperadorRegla operador;

    public Regla(float min, float max, OperadorRegla operador) {
        super();
        this.min = min;
        this.max = max;
        this.operador = operador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public OperadorRegla getOperador() {
        return operador;
    }

    public void setOperador(OperadorRegla operador) {
        this.operador = operador;
    }

    public Boolean isValorCritico(float valor) {
        switch (operador) {
            case MAYOR_IGUAL -> {
                if (this.max >= valor)
                    return true;
            } case MAYOR -> {
                if (this.max > valor)
                    return true;
            } case MENOR_IGUAL -> {
                if (this.min <= valor)
                    return true;
            } case MENOR -> {
                if (this.min < valor)
                    return true;
            } case IGUAL -> {
                if (this.min == valor && this.max == valor)
                    return true;
            } case ENTRE -> {
                if (this.min >= valor && this.max <= valor)
                    return true;
            } default -> {
                return false;
            }
        }
        return false;
    }
}
