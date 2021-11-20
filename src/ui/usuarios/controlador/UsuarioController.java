package ui.usuarios.controlador;

import models.Rol;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuarioDTO;
import ui.usuarios.model.UsuariosTable;
import utils.DataUtils;

import java.util.Date;
import java.util.List;

public class  UsuarioController {
    private static UsuarioController instance;

    UsuarioDTO usuario;

    public UsuarioController() {
        // no-op
    }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public List<UsuarioDTO> getAllUsuarios() {
        return UsuariosTable.getAllUsuarios();
    }

    public void addUsuario(String nombre, int dni, String domicilio, Date fecnac, String email, String username, String password, Rol rol) {
        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setDni(dni);
            usuario.setDomicilio(domicilio);
            usuario.setFechaNacimiento(fecnac);
            usuario.setMail(email);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setRol(rol);
            UsuariosTable.modifyUsuario(usuario);
        } else {
            UsuariosTable.addUsuario(new UsuarioDTO(nombre, dni, domicilio, email, username, password, fecnac, rol));
        }
    }

    public void deleteUsuario() {
        UsuariosTable.deleteUsuario(usuario);
    }
}
