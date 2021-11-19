package ui.usuarios.model;

import models.Rol;
import java.util.Date;
import java.util.List;

public class Usuario extends UsuarioDTO {

    public Usuario(String nombre, int dni, String domicilio, String mail, String username, String password, Date fecnac, Rol rol) {
        super(nombre, dni, domicilio, mail, username, password, fecnac, rol);
    }

    public static UsuarioDTO login(String username, String password) {
        List<UsuarioDTO> usuarios = UsuariosTable.getAllUsuarios();
        for (UsuarioDTO usuario: usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getUsername().equals(password))
                return usuario;
        }
        return null;
    }
}
