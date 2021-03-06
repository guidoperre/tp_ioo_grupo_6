package ui.sucursales.model;

import ui.peticiones.model.Peticion;
import ui.usuarios.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {

    private Long id;
    private String direccion;
    private Usuario responsable;
    private final List<Peticion> peticiones;

    public Sucursal(String direccion, Usuario responsable) {
        this.direccion = direccion;
        this.responsable = responsable;
        this.peticiones = new ArrayList<>();
    }

    public Sucursal(String direccion, Usuario responsable, List<Peticion> peticiones) {
        this.direccion = direccion;
        this.responsable = responsable;
        this.peticiones = peticiones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public void addPeticion(Peticion peticion) {
        this.peticiones.add(peticion);
    }

    public void addPeticiones(List<Peticion> peticiones) {
        this.peticiones.addAll(peticiones);
    }

    public void movePeticiones(Sucursal destino) {
        for (Peticion peticion: this.peticiones) {
            if (peticion.isActiva())
                destino.addPeticion(peticion);
        }
    }

    public List<Peticion> getPeticionesActivas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (Peticion peticion: this.peticiones) {
            if (!peticion.isActiva())
                peticiones.add(peticion);
        }
        return peticiones;
    }

    public List<Peticion> getPeticionesFinalizadas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (Peticion peticion: this.peticiones) {
            if (peticion.isFinalizada())
                peticiones.add(peticion);
        }
        return peticiones;
    }

    public List<Peticion> getPeticionesCriticas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (Peticion peticion: this.peticiones) {
            if (peticion.isCritica())
                peticiones.add(peticion);
        }
        return peticiones;
    }

    @Override
    public String toString() {
        return direccion;
    }
}
