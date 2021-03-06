package ui.usuarios.model;

import models.Persona;
import models.Rol;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Usuario extends Persona {

    private String username;
    private String password;
    private Date fechaNacimiento;
    private Rol rol;

    public Usuario(String nombre, int dni, String domicilio, String mail, String username, String password, Date fecnac, Rol rol) {
        super(nombre, dni, domicilio, mail);
        this.username = username;
        this.password = password;
        this.fechaNacimiento = fecnac;
        this.rol = rol;
    }

    public static Usuario login(String username, String password) {
        List<Usuario> usuarios = UsuariosTable.getAllUsuarios();
        for (Usuario usuario: usuarios) {
            if (usuario.username.equals(username) && usuario.password.equals(password))
                return usuario;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaNacimiento);
        return 2021 - calendar.get(Calendar.YEAR);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
