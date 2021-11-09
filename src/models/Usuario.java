package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<Usuario> usuarios;

    public Usuario(String nombre, int dni, String domicilio, String mail, String username, String password, Date fecnac, rolEnum rol) {
        super(nombre, dni, domicilio, mail);
        this.username = username;
        this.password = password;
        this.fecnac = fecnac;
        this.rol = rol;
        this.usuarios.add(this);
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        this.deleteUsuarioPorId(this.getId());
        return super.delete();
    }

    public Usuario login(String username, String password) {
        for (Usuario usuario: this.usuarios) {
            if (usuario.username == username && usuario.password == password)
                return usuario;
        }
        return null;
    }

    public Usuario deleteUsuarioPorId(Integer id) {
        for (Usuario usuario: new ArrayList<Usuario>(usuarios)) {
            if (usuario.getId() == id)
                usuarios.remove(usuario);
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
