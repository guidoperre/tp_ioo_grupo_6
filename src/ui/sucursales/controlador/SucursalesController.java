package ui.sucursales.controlador;

import models.GrupoPractica;
import models.Rol;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.model.Usuario;

import java.util.Date;
import java.util.List;

public class SucursalesController extends Sucursal {

    public SucursalesController(String direccion, Usuario responsable) {
        super(direccion, responsable);
    }

    public static List<Sucursal> getAllSucursales() {
        return SucursalesTable.getAllSucursales();
    }

    public static Long getId(Sucursal sucursal) {
        return sucursal.getId();
    }

    public static String getDireccion(Sucursal sucursal) {
        return sucursal.getDireccion();
    }

    public static String getResponsable(Sucursal sucursal) {
        return sucursal.getResponsable().getNombre();
    }

    public static void setDireccion(Sucursal sucursal, String direccion) {
        sucursal.setDireccion(direccion);
    }


    public static void setResponsable(Sucursal sucursal, Usuario usuario) {
        sucursal.setResponsable(usuario);
    }

    public static void modifySucursal(Sucursal sucursal) {
        SucursalesTable.modifySucursal(sucursal);
    }

    public static void addSucursal(Sucursal sucursal) {
        SucursalesTable.addSucursal(sucursal);
    }

    public static void movePeticiones(Sucursal sucursal, Sucursal sucursalItem) {
        sucursal.movePeticiones(sucursalItem);
    }

    public static void deleteSucursal(Sucursal sucursal) {
        SucursalesTable.deleteSucursal(sucursal);
    }
}
