package models;

import java.util.List;

public class Sucursal extends Base {
    private String direccion;
    private Usuario responsable;
    private List<Peticion> peticiones;

    public Sucursal(String direccion, Usuario responsable, List<Peticion> peticiones) {
        this.direccion = direccion;
        this.responsable = responsable;
        this.peticiones = peticiones;
    }


    @Override
    public void update() {}

    @Override
    public void delete() {}
}
