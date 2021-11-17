package models;

public enum OperadorRegla {
    MAYOR_IGUAL,
    MAYOR,
    MENOR_IGUAL,
    MENOR,
    IGUAL,
    ENTRE;

    @Override
    public String toString() {
        return switch (this) {
            case MAYOR_IGUAL -> "Mayor o igual";
            case MAYOR -> "Mayor";
            case MENOR_IGUAL -> "Menor o igual";
            case MENOR -> "Menor";
            case IGUAL -> "Igual";
            case ENTRE -> "Entre";
        };
    }
}
