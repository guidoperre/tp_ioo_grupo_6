package ui.usuarios.controlador;

import models.Rol;

import java.util.Calendar;
import java.util.Date;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private int dni;
    private String domicilio;
    private String mail;
    private String username;
    private String password;
    private Date fechaNacimiento;
    private Rol rol;

    public UsuarioDTO(String nombre, int dni, String domicilio, String mail, String username, String password, Date fechaNacimiento, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
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
