package models;

public class Persona extends Base {
    private String nombre;
    private int dni;
    private String domicilio;
    private String mail;

    public Persona(String nombre, int dni, String domicilio, String mail) {
        super();
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    public boolean update(String nombre, int dni, String domicilio, String mail) {
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.mail = mail;
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }
}
