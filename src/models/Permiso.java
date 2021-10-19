package models;

import java.util.List;

public class Permiso extends Base {
    private List<Accion> acciones;

    public Permiso(List<Accion> acciones) {
        this.acciones = acciones;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
