package models;

import java.util.ArrayList;
import java.util.List;

public class Sucursal extends Base {
    private String direccion;
    private Usuario responsable;
    private List<Peticion> peticiones;
    private List<Sucursal> sucursales;

    public Sucursal(String direccion, Usuario responsable, List<Peticion> peticiones) {
        super();
        this.direccion = direccion;
        this.responsable = responsable;
        this.peticiones = peticiones;
        this.sucursales.add(this);
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete(Sucursal destino) {
        if (this.getPeticionesFinalizadas().size() > 0)
            return false;
        this.transferirPeticiones(destino);
        this.deleteSucursalPorId(this.getId());
        return super.delete();
    }

    public void agregarPeticion(Peticion peticion) {
        this.peticiones.add(peticion);
    }

    private void transferirPeticiones(Sucursal destino) {
        for (Peticion peticion: this.peticiones) {
            if (peticion.isActiva())
                destino.agregarPeticion(peticion);
        }
    }

    private List<Peticion> getPeticionesFinalizadas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (Peticion peticion: this.peticiones) {
            if (!peticion.isActiva())
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

    public Sucursal deleteSucursalPorId(Integer id) {
        for (Sucursal sucursal: new ArrayList<Sucursal>(sucursales)) {
            if (sucursal.getId() == id)
                sucursales.remove(sucursal);
        }
        return null;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }
}
