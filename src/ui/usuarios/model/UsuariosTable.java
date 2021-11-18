package ui.usuarios.model;

import ui.usuarios.controlador.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuariosTable {
    public static UsuarioDTO usuario = null;

    private static ArrayList<UsuarioDTO> usuarios;
    private static Long indexCount = 0L;

    public static void init() {
        usuarios = new ArrayList<>();
    }

    public static List<UsuarioDTO> getAllUsuarios() {
        return usuarios;
    }

    public static UsuarioDTO getUsuario(String nombre) {
        for (UsuarioDTO p: usuarios) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }


    public static void addUsuario(UsuarioDTO usuario) {
        usuario.setId(indexCount);
        usuarios.add(usuario);
        indexCount++;
    }

    public static void modifyUsuario(UsuarioDTO usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioDTO aux = usuarios.get(i);
            if (Objects.equals(aux.getId(), usuario.getId())) {
                usuarios.remove(i);
                usuarios.add(usuario);
                break;
            }
        }
    }

    public static void deleteUsuario(UsuarioDTO usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioDTO aux = usuarios.get(i);
            if (Objects.equals(aux.getId(), usuario.getId())) {
                usuarios.remove(i);
                break;
            }
        }
    }
}
