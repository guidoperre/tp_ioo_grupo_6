package ui.sucursales.controlador;

import ui.sucursales.model.SucursalDTO;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.model.UsuarioDTO;

import javax.swing.*;
import java.util.List;

public class SucursalesController {
    private static SucursalesController instance;
    SucursalDTO sucursal;

    public SucursalesController(){
        // no-op
    }

    public static SucursalesController getInstance() {
        if (instance == null) {
            instance = new SucursalesController();
        }
        return instance;
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

    public boolean deleteSucursal() {
        if(sucursal.getPeticionesFinalizadas().size() > 0){
            return false;
        }
        Boolean movido = false;
        for (SucursalDTO sucursalItem: this.getAllSucursales()) {
            if (sucursalItem.getId() != sucursal.getId()) {
                sucursal.movePeticiones(sucursalItem);
                movido = true;
                SucursalesTable.deleteSucursal(sucursal);
                break;
            }
            return true;
        }
        SucursalesTable.deleteSucursal(sucursal);
        return true;
    }
}
