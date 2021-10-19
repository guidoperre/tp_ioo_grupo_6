package models;

import java.util.List;

public class GrupoPractica extends Base {
    private String nombre;
    private List<Practica> practicas;

    public GrupoPractica(String nombre, List<Practica> practicas) {
        this.nombre = nombre;
        this.practicas = practicas;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
