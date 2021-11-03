package models;

import java.util.Date;

public class Usuario extends Persona {
    private String username;
    private String password;
    private Date fecnac;
    private enum rolEnum {
        recepcion,
        laboratorio,
        administrador
    }
    private rolEnum rol;

    public Usuario(String nombre, int dni, String domicilio, String mail, String username, String password, Date fecnac, rolEnum rol) {
        super(nombre, dni, domicilio, mail);
        this.username = username;
        this.password = password;
        this.fecnac = fecnac;
        this.rol = rol;
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public rolEnum getRol() {
        return rol;
    }
}
