package models;

import java.util.List;

public class Permiso extends Base {
    private List<Accion> acciones;

    public Permiso(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }
}
