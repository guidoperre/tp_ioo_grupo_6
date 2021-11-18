package ui.usuarios.controlador;

import models.Rol;
import ui.usuarios.model.UsuariosTable;
import utils.DataUtils;

import java.util.Date;
import java.util.List;

public class UsuarioController {

    public static List<UsuarioDTO> getAllUsuarios() {

        return UsuariosTable.getAllUsuarios();
    }

    public static String getNombre(UsuarioDTO usuario) {
        return usuario.getNombre();
    }

    public static Rol getRol(UsuarioDTO usuario) {
        return usuario.getRol();
    }

    public static String getDni(UsuarioDTO usuario) {
        return String.valueOf(usuario.getDni());
    }

    public static String getDomicilio(UsuarioDTO usuario) {
        return usuario.getDomicilio();
    }

    public static String getEdad(UsuarioDTO usuario) {
        return Integer.toString(usuario.getEdad()) + " años";
    }

    public static String getMail(UsuarioDTO usuario) {
        return usuario.getMail();
    }

    public static String getUsername(UsuarioDTO usuario) {
        return usuario.getUsername();
    }

    public static String getPassword(UsuarioDTO usuario) {
        return usuario.getPassword();
    }

    public static String getFechaNacimiento(UsuarioDTO usuario) {
        return DataUtils.getFechaFromDate(usuario.getFechaNacimiento());
    }

    public static void setNombre(UsuarioDTO usuario, String name) {
        usuario.setNombre(name);
    }

    public static void setDni(UsuarioDTO usuario, int dni) {
        usuario.setDni(dni);
    }

    public static void setDomicilio(UsuarioDTO usuario, String domicilio) {
        usuario.setDomicilio(domicilio);
    }

    public static void setFechaNacimiento(UsuarioDTO usuario, Date fecha) {
        usuario.setFechaNacimiento(fecha);
    }

    public static void setMail(UsuarioDTO usuario, String mail) {
        usuario.setMail(mail);
    }

    public static void setUsername(UsuarioDTO usuario, String username) {
        usuario.setUsername(username);
    }

    public static void setPassword(UsuarioDTO usuario, String password) {
        usuario.setPassword(password);
    }

    public static void setRol(UsuarioDTO usuario, Rol rol) {
        usuario.setRol(rol);
    }

    public static void modifyUsuario(UsuarioDTO usuario) {
        UsuariosTable.modifyUsuario(usuario);
    }

    public static void addUsuario(UsuarioDTO usuario) {
        UsuariosTable.addUsuario(usuario);
    }

    public static void deleteUsuario(UsuarioDTO usuario) {
        UsuariosTable.deleteUsuario(usuario);
    }
}
