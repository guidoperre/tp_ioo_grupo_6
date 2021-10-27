package models;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {
    private int edad;
    private enum sexoEnum {
        masculino,
        femenino
    }
    private sexoEnum sexo;
    private List<Peticion> peticiones;

    public Paciente(String nombre, int dni, String domicilio, String mail, int edad, sexoEnum sexo) {
        super(nombre, dni, domicilio, mail);
        this.edad = edad;
        this.sexo = sexo;
    }

    public boolean update(String nombre, int dni, String domicilio, String mail, int edad, sexoEnum sexo) {
        this.edad = edad;
        this.sexo = sexo;
        return super.update(nombre, dni, domicilio, mail);
    }

    public Boolean delete() {
        if (this.getPeticionesFinalizadas().size() > 0)
            return false;
        return super.delete();
    }

    public void agregarPeticion(Peticion peticion) {
        this.peticiones.add(peticion);
    }

    private List<Peticion> getPeticionesFinalizadas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (Peticion peticion: this.peticiones) {
            if (peticion.isActiva())
                peticiones.add(peticion);
        }
        return peticiones;
    }
}
