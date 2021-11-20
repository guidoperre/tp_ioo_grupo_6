package ui.sucursales.model;

import ui.peticiones.model.Peticion;
import ui.usuarios.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sucursal extends SucursalDTO {

    public Sucursal(String direccion, Usuario responsable) {
        super(direccion, responsable);
    }

    public Sucursal(String direccion, Usuario responsable, List<Peticion> peticiones) {
        super(direccion, responsable, peticiones);
    }
}
