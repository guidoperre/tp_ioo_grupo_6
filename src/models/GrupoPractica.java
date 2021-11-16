package models;

import ui.practicas.model.Practica;

import java.util.List;

public class GrupoPractica {

    private Long id;
    private String nombre;
    private List<Practica> practicas;

    public GrupoPractica(String nombre, List<Practica> practicas) {
        super();
        this.nombre = nombre;
        this.practicas = practicas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void addPractica(Practica practica) {
        practicas.add(practica);
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }
}
