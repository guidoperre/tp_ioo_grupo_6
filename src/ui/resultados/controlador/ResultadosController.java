package ui.resultados.controlador;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.resultados.model.Resultado;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.model.Usuario;

import java.util.List;

public class ResultadosController extends Resultado {


    public static List<Paciente> getAllPacientes() {
        return PacientesTable.getAllPacientes();
    }
}
