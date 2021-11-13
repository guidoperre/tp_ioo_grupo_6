package models;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {

    private int edad;
    private SexoEnum sexo;

    public Paciente(String nombre, int dni, String domicilio, String mail, int edad, SexoEnum sexo) {
        super(nombre, dni, domicilio, mail);
        this.edad = edad;
        this.sexo = sexo;
    }

    public boolean update(String nombre, int dni, String domicilio, String mail, int edad, SexoEnum sexo) {
        this.edad = edad;
        this.sexo = sexo;
        return super.update(nombre, dni, domicilio, mail);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "edad=" + edad +
                ", sexo=" + sexo +
                '}';
    }
}
