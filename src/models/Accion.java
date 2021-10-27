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


    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }
}
