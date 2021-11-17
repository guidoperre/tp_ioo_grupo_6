package ui.pacientes.controlador;

import models.Sexo;
import ui.pacientes.models.Paciente;

public class PacienteControler extends Paciente {
    public PacienteControler(String nombre, int dni, String domicilio, String mail, int edad, Sexo sexo) {
        super(nombre, dni, domicilio, mail, edad, sexo);
    }

    
}
