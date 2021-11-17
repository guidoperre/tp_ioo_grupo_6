package ui.peticiones.controlador;

import ui.pacientes.models.Paciente;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.resultados.model.Resultado;
import ui.sucursales.model.Sucursal;

import java.util.Date;
import java.util.List;

public class PeticionControler extends Peticion {
    public PeticionControler(Paciente paciente, String obraSocial, Sucursal sucursal, Date fechaCarga, Date fechaEntrega, List<Practica> practicas, List<Resultado> resultados) {
        super(paciente, obraSocial, sucursal, fechaCarga, fechaEntrega, practicas, resultados);
    }

    public static List<Peticion> getPeticionesCriticas(){
        return PeticionesTable.getPeticionesCriticas();
    }

    public static List<Peticion> getPeticiones(){
        return PeticionesTable.getAllPeticiones();
    }
}
