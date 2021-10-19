package models;

public class Paciente extends Persona {
    private int edad;
    private enum sexoEnum {
        masculino,
        femenino
    }
    private sexoEnum sexo;

    public Paciente(String nombre, int dni, String domicilio, String mail, int edad, sexoEnum sexo) {
        super(nombre, dni, domicilio, mail);
        this.edad = edad;
        this.sexo = sexo;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
