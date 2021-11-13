package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuariosTable {

    private static ArrayList<Usuario> usuarios;
    private static Long indexCount = 0L;

    public static void init() {
        usuarios = new ArrayList<>();
    }

    public static List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    public static Usuario getUsuario(String nombre) {
        for (Usuario p: usuarios) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }


    public static void addUsuario(Usuario usuario) {
        usuario.setId(indexCount);
        usuarios.add(usuario);
        indexCount++;
    }

    public static void modifyUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (Objects.equals(aux.getId(), usuario.getId())) {
                usuarios.remove(i);
                usuarios.add(usuario);
                break;
            }
        }
    }

    public static void deleteUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (Objects.equals(aux.getId(), usuario.getId())) {
                usuarios.remove(i);
                break;
            }
        }
    }
}
