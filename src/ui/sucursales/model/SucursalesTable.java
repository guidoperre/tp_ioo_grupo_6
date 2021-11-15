package ui.sucursales.model;

import ui.pacientes.models.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SucursalesTable {

    private static ArrayList<Sucursal> sucursales;
    private static Long indexCount = 0L;

    public static void init() {
        sucursales = new ArrayList<>();
    }

    public static List<Sucursal> getAllSucursales() {
        return sucursales;
    }

    public static Sucursal getSucursal(String direccion) {
        for (Sucursal p: sucursales) {
            if (p.getDireccion().equals(direccion)) {
                return p;
            }
        }
        return null;
    }


    public static void addSucursal(Sucursal sucursal) {
        sucursal.setId(indexCount);
        sucursales.add(sucursal);
        indexCount++;
    }

    public static void modifySucursal(Sucursal sucursal) {
        for (int i = 0; i < sucursales.size(); i++) {
            Sucursal aux = sucursales.get(i);
            if (Objects.equals(aux.getId(), sucursal.getId())) {
                sucursales.remove(i);
                sucursales.add(sucursal);
                break;
            }
        }
    }

    public static void deleteSucursal(Sucursal sucursal) {
        for (int i = 0; i < sucursales.size(); i++) {
            Sucursal aux = sucursales.get(i);
            if (Objects.equals(aux.getId(), sucursal.getId())) {
                sucursales.remove(i);
                break;
            }
        }
    }
}
