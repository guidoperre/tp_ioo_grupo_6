package models;

import java.util.List;

public class Practica extends Base {
    private int codigo;
    private Boolean activo;
    private String nombre;
    private List<Regla> valoresCriticos;
    private List<Regla> valoresReservados;

    public Practica(int codigo, Boolean activo, String nombre, List<Regla> valoresCriticos, List<Regla> valoresReservados) {
        this.codigo = codigo;
        this.activo = activo;
        this.nombre = nombre;
        this.valoresCriticos = valoresCriticos;
        this.valoresReservados = valoresReservados;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
