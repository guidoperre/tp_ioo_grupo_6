package ui.resultados.controlador;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.resultados.model.ResultadoDTO;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.controlador.UsuarioController;
import ui.usuarios.model.Usuario;

import java.util.List;

public class ResultadosController {

    private static ResultadosController instance;

    public static List<PacienteDTO> getAllPacientes() {
        return PacientesTable.getAllPacientes();
    }

    public Long getId(ResultadoDTO resultado) {
        return resultado.getId();
    }

    public static ResultadosController getInstance() {
        if (instance == null) {
            instance = new ResultadosController();
        }
        return instance;
    }
}
