package ui.pacientes.models;

import models.PersonaDTO;
import models.Sexo;

public class PacienteDTO extends PersonaDTO {
    private int edad;
    private Sexo sexo;

    public PacienteDTO(String nombre, int dni, String domicilio, String mail, int edad, Sexo sexo) {
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
