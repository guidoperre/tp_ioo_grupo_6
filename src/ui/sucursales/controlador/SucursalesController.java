package ui.sucursales.controlador;

import ui.sucursales.model.SucursalDTO;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.model.UsuarioDTO;

import java.util.List;

public class SucursalesController {

    SucursalDTO sucursal;

    public SucursalesController(){
        // no-op
    }

    public List<SucursalDTO> getAllSucursales() {
        return SucursalesTable.getAllSucursales();
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public Long getId(SucursalDTO sucursal) {
        return sucursal.getId();
    }

    public String getDireccion(SucursalDTO sucursal) {
        return sucursal.getDireccion();
    }

    public String getResponsable(SucursalDTO sucursal) {
        return sucursal.getResponsable().getNombre();
    }

    public void setDireccion(SucursalDTO sucursal, String direccion) {
        sucursal.setDireccion(direccion);
    }


    public void setResponsable(SucursalDTO sucursal, UsuarioDTO usuario) {
        sucursal.setResponsable(usuario);
    }

    public void modifySucursal(SucursalDTO sucursal) {
        SucursalesTable.modifySucursal(sucursal);
    }

    public void addSucursal(String direccion, UsuarioDTO responsable) {
        if (sucursal != null) {
            sucursal.setDireccion(direccion);
            sucursal.setResponsable(responsable);
            SucursalesTable.modifySucursal(sucursal);
        } else {
            SucursalesTable.addSucursal(new SucursalDTO(direccion, responsable));
        }
    }

    public void movePeticiones(SucursalDTO sucursalItem) {
        sucursal.movePeticiones(sucursalItem);
    }

    public static void deleteSucursal(SucursalDTO sucursal) {
        SucursalesTable.deleteSucursal(sucursal);
    }
}
