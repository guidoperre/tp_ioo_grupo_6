package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario extends Persona {
    private String username;
    private String password;
    private Date fecnac;
    public enum rolEnum {
        recepcion,
        laboratorio,
        administrador
    }
    private rolEnum rol;
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String nombre, int dni, String domicilio, String mail, String username, String password, Date fecnac, rolEnum rol) {
        super(nombre, dni, domicilio, mail);
        this.username = username;
        this.password = password;
        this.fecnac = fecnac;
        this.rol = rol;
        this.usuarios.add(this);
    }

    public static Usuario login(String username, String password) {
        for (Usuario usuario: usuarios) {
            if (usuario.username.equals(username) && usuario.password.equals(password))
                return usuario;
        }
        return null;
    }

    public List<Usuario> getUsuarios() { return usuarios; }

    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public rolEnum getRol() {
        return rol;
    }
}
