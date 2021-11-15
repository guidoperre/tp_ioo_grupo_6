package models;

import java.util.List;

public class GrupoPractica extends Base {
    private String nombre;
    private List<Practica> practicas;

    public GrupoPractica(String nombre, List<Practica> practicas) {
        super();
        this.nombre = nombre;
        this.practicas = practicas;
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }
}
