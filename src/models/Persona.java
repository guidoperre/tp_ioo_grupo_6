package models;

public class Persona extends Base {
    private String nombre;
    private int dni;
    private String domicilio;
    private String mail;

    public Persona(String nombre, int dni, String domicilio, String mail) {
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
