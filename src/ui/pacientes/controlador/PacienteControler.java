package ui.pacientes.controlador;

import models.Sexo;
import ui.pacientes.models.Paciente;

import java.util.List;
import ui.pacientes.models.PacientesTable
public class PacienteControler extends Paciente {
    public PacienteControler(String nombre, int dni, String domicilio, String mail, int edad, Sexo sexo) {
        super(nombre, dni, domicilio, mail, edad, sexo);
    }
    public static List<Paciente> getPacientes(){
        return  PacientesTable.getAllPacientes();
    }
}
