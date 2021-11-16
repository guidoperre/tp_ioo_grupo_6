package ui.usuarios.controlador;

import models.Rol;
import ui.usuarios.model.Usuario;
import ui.usuarios.Usuarios;
import ui.usuarios.model.UsuariosTable;
import utils.DataUtils;

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

    public static String getNombre(Usuario usuario) {
        return usuario.getNombre();
    }

    public static String getRol(Usuario usuario) {
        return usuario.getRol().name();
    }

    public static String getDni(Usuario usuario) {
        return String.valueOf(usuario.getDni());
    }

    public static String getDomicilio(Usuario usuario) {
        return usuario.getDomicilio();
    }

    public static String getEdad(Usuario usuario) {
        return Integer.toString(usuario.getEdad()) + " años";
    }

    public static String getMail(Usuario usuario) {
        return usuario.getMail();
    }

    public static String getUsername(Usuario usuario) {
        return usuario.getUsername();
    }

    public static String getPassword(Usuario usuario) {
        return usuario.getPassword();
    }

    public static String getFechaNacimiento(Usuario usuario) {
        return DataUtils.getFechaFromDate(usuario.getFechaNacimiento());
    }

    public static void setNombre(Usuario usuario, String name) {
        usuario.setNombre(name);
    }

    public static void setDni(Usuario usuario, int dni) {
        usuario.setDni(dni);
    }

    public static void setDomicilio(Usuario usuario, String domicilio) {
        usuario.setDomicilio(domicilio);
    }

    public static void setFechaNacimiento(Usuario usuario, Date fecha) {
        usuario.setFechaNacimiento(fecha);
    }

    public static void setMail(Usuario usuario, String mail) {
        usuario.setMail(mail);
    }


    public static void setUsername(Usuario usuario, String username) {
        usuario.setUsername(username);
    }


    public static void setPassword(Usuario usuario, String password) {
        usuario.setPassword(password);
    }

    public static void setRol(Usuario usuario, Rol rol) {
        usuario.setRol(rol);
    }

    public static void modifyUsuario(Usuario usuario) {
        UsuariosTable.modifyUsuario(usuario);
    }

    public static void addUsuario(Usuario usuario) {
        UsuariosTable.addUsuario(usuario);
    }

    public static void deleteUsuario(Usuario usuario) {
        UsuariosTable.deleteUsuario(usuario);
    }
}
