package models;

import java.util.List;

public class Accion extends Base {
    private int codigo;
    private enum permiso {
        recepcion,
        laboratorio,
        administrador
    }
    private List<permiso> permisos;

    public Accion(int codigo, List<permiso> permisos) {
        this.codigo = codigo;
        this.permisos = permisos;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
