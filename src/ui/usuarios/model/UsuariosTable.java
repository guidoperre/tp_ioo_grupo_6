package ui.usuarios.model;

import ui.usuarios.controlador.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuariosTable {
    public static Usuario usuario = null;

    private static ArrayList<Usuario> usuarios;
    private static Long indexCount = 0L;

    public static void init() {
        usuarios = new ArrayList<>();
    }

    public static List<UsuarioDTO> getAllUsuarios() {
        ArrayList<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario p: usuarios) {
            usuariosDTO.add(
                    new UsuarioDTO(
                            p.getNombre(),
                            p.getDni(),
                            p.getDomicilio(),
                            p.getMail(),
                            p.getUsername(),
                            p.getPassword(),
                            p.getFechaNacimiento(),
                            p.getRol()
                    )
            );
        }
        return usuariosDTO;
    }

    public static UsuarioDTO getUsuario(String nombre) {
        for (Usuario p: usuarios) {
            if (p.getNombre().equals(nombre)) {
                return new UsuarioDTO(
                        p.getNombre(),
                        p.getDni(),
                        p.getDomicilio(),
                        p.getMail(),
                        p.getUsername(),
                        p.getPassword(),
                        p.getFechaNacimiento(),
                        p.getRol()
                );
            }
        }
        return null;
    }


    public static void addUsuario(UsuarioDTO usuario) {
        usuario.setId(indexCount);
        Usuario nuevo = new Usuario(
                usuario.getNombre(),
                usuario.getDni(),
                usuario.getDomicilio(),
                usuario.getMail(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getFechaNacimiento(),
                usuario.getRol()
        );
        usuarios.add(nuevo);
        indexCount++;
    }

    public static void modifyUsuario(UsuarioDTO usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (Objects.equals(aux.getId(), usuario.getId())) {
                usuarios.remove(i);
                usuarios.add(new Usuario(
                        usuario.getNombre(),
                        usuario.getDni(),
                        usuario.getDomicilio(),
                        usuario.getMail(),
                        usuario.getUsername(),
                        usuario.getPassword(),
                        usuario.getFechaNacimiento(),
                        usuario.getRol()
                ));
                break;
            }
        }
    }

    public static void deleteUsuario(UsuarioDTO usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (Objects.equals(aux.getId(), usuario.getId())) {
                usuarios.remove(i);
                break;
            }
        }
    }
}
