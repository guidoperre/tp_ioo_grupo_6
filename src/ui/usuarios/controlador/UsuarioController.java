package ui.usuarios.controlador;

import models.Rol;
import ui.usuarios.model.Usuario;
import ui.usuarios.Usuarios;
import ui.usuarios.model.UsuariosTable;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UsuarioController extends Usuario {

    public UsuarioController(String nombre, int dni, String domicilio, String mail, String username, String password, Date fecnac, Rol rol) {
        super(nombre, dni, domicilio, mail, username, password, fecnac, rol);
    }

    public static List<Usuario> getAllUsuarios() {
        return UsuariosTable.getAllUsuarios();
    }

}
