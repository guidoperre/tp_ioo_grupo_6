package ui.pacientes.controlador;

import java.util.ArrayList;
import java.util.List;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;

public class PacienteControler {
    public PacienteControler() {
        // no-op
    }
    public static List<PacienteDTO> getPacientes(){
        return  PacientesTable.getAllPacientes();
    }

    public List<PeticionDTO> getPeticionesFinalizadas(PacienteDTO paciente) {
        List<PeticionDTO> res = new ArrayList<>();
        List<Peticion> peticiones = new Paciente(
                paciente.getNombre(),
                paciente.getDni(),
                paciente.getDomicilio(),
                paciente.getMail(),
                paciente.getEdad(),
                paciente.getSexo()).getPeticionesFinalizadas();

        for (Peticion peticion: peticiones) {
            ArrayList<ResultadoDTO> resultados = new ArrayList<>();
            for (Resultado resultado: peticion.getResultados()) {
                resultados.add(new ResultadoDTO(
                        resultado.getValor(),
                        resultado.getEstado(),
                        resultado.getCodigoPractica()
                ));
            }
            res.add(new PeticionDTO(paciente, peticion.getObraSocial(), peticion.getSucursal(), peticion.getFechaCarga(), peticion.getFechaEntrega(), peticion.getPracticas(), resultados));
        }
        return res;
    }
}
