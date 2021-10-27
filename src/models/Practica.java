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

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }

    public int getCodigo() {
        return codigo;
    }

    public Boolean isValorCritico(float valor) {
        Boolean critico = false;
        for (Regla regla: this.valoresCriticos) {
            if (regla.isValorCritico(valor))
                critico = true;
        }
        return critico;
    }
}
