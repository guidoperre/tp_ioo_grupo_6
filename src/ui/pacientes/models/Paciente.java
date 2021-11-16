package ui.pacientes.models;

import models.Persona;
import models.Sexo;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paciente extends Persona {

    private int edad;
    private Sexo sexo;

    public Paciente(String nombre, int dni, String domicilio, String mail, int edad, Sexo sexo) {
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

    public List<Peticion> getPeticionesFinalizadas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (Peticion peticion: PeticionesTable.getAllPeticiones()) {
            if (peticion.getPaciente().getId() == this.getId()) {
                if (peticion.isFinalizada())
                    peticiones.add(peticion);
            }
        }
        return peticiones;
    }
}
