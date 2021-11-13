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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
