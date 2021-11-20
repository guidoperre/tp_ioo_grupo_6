package ui.sucursales.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SucursalesTable {
    private static Sucursal sucursal = null;
    private static ArrayList<SucursalDTO> sucursales;
    private static Long indexCount = 0L;

    public static void init() {
        sucursales = new ArrayList<>();
    }

    public static List<SucursalDTO> getAllSucursales() {
        return sucursales;
    }

    public static SucursalDTO getSucursal(String direccion) {
        for (SucursalDTO p: sucursales) {
            if (p.getDireccion().equals(direccion)) {
                return p;
            }
        }
        return null;
    }


    public static void addSucursal(SucursalDTO sucursal) {
        sucursal.setId(indexCount);
        sucursales.add(sucursal);
        indexCount++;
    }

    public static void modifySucursal(SucursalDTO sucursal) {
        for (int i = 0; i < sucursales.size(); i++) {
            SucursalDTO aux = sucursales.get(i);
            if (Objects.equals(aux.getId(), sucursal.getId())) {
                sucursales.remove(i);
                sucursales.add(sucursal);
                break;
            }
        }
    }

    public static void deleteSucursal(SucursalDTO sucursal) {
        for (int i = 0; i < sucursales.size(); i++) {
            SucursalDTO aux = sucursales.get(i);
            if (Objects.equals(aux.getId(), sucursal.getId())) {
                sucursales.remove(i);
                break;
            }
        }
    }
}
