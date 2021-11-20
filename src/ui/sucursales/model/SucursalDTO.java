package ui.sucursales.model;

import models.PersonaDTO;
import models.Rol;
import ui.peticiones.model.Peticion;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuarioDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SucursalDTO {

    private Long id;
    private String direccion;
    private UsuarioDTO responsable;
    private final List<Peticion> peticiones;

    public SucursalDTO(String direccion, UsuarioDTO responsable) {
        this.direccion = direccion;
        this.responsable = responsable;
        this.peticiones = new ArrayList<>();
    }

    public SucursalDTO(String direccion, UsuarioDTO responsable, List<Peticion> peticiones) {
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

    public UsuarioDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(UsuarioDTO responsable) {
        this.responsable = responsable;
    }

    public void addPeticion(Peticion peticion) {
        this.peticiones.add(peticion);
    }

    public void addPeticiones(List<Peticion> peticiones) {
        this.peticiones.addAll(peticiones);
    }

    public void movePeticiones(SucursalDTO destino) {
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
