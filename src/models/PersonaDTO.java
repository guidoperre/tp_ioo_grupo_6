package models;

public class PersonaDTO {

    private Long id;
    private String nombre;
    private int dni;
    private String domicilio;
    private String mail;

    public PersonaDTO(String nombre, int dni, String domicilio, String mail) {
        super();
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
