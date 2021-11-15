package ui.pacientes.models;

import models.Persona;
import models.SexoEnum;

import java.util.Objects;

public class Paciente extends Persona {

    private int edad;
    private SexoEnum sexo;

    public Paciente(String nombre, int dni, String domicilio, String mail, int edad, SexoEnum sexo) {
        super(nombre, dni, domicilio, mail);
        this.edad = edad;
        this.sexo = sexo;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return edad == paciente.edad &&
                sexo == paciente.sexo &&
                paciente.getNombre().equals(getNombre()) &&
                paciente.getDni() == getDni() &&
                paciente.getDomicilio().equals(getDomicilio()) &&
                paciente.getMail().equals(getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(edad, sexo, getNombre(), getDni(), getDomicilio(), getMail());
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
