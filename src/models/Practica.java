package models;

import java.util.ArrayList;
import java.util.List;

public class Practica extends Base {
    private int codigo;
    private Boolean activo;
    private String nombre;
    private List<Regla> valoresCriticos;
    private List<Regla> valoresReservados;
    private List<Practica> practicas;

    public Practica(int codigo, Boolean activo, String nombre, List<Regla> valoresCriticos, List<Regla> valoresReservados) {
        super();
        this.codigo = codigo;
        this.activo = activo;
        this.nombre = nombre;
        this.valoresCriticos = valoresCriticos;
        this.valoresReservados = valoresReservados;
        this.practicas.add(this);
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        this.deletePracticaPorId(this.getId());
        return super.delete();
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Practica> getPracticas() {
        return practicas;
    }

    public Boolean isValorCritico(float valor) {
        Boolean critico = false;
        for (Regla regla: this.valoresCriticos) {
            if (regla.isValorCritico(valor))
                critico = true;
        }
        return critico;
    }

    public Practica deletePracticaPorId(Integer id) {
        for (Practica practica: new ArrayList<Practica>(practicas)) {
            if (practica.getId() == id)
                practicas.remove(practica);
        }
        return null;
    }
}
