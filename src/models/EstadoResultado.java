package models;

public enum EstadoResultado {
    PENDIENTE,
    FINALIZADO;

    @Override
    public String toString() {
        return switch (this) {
            case PENDIENTE -> "En curso";
            case FINALIZADO -> "Finalizado";
        };
    }
}
