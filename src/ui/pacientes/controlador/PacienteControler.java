package ui.pacientes.controlador;

import java.util.ArrayList;
import java.util.List;

import models.Sexo;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.pacientes.models.PacientesTable;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;
import ui.sucursales.model.SucursalDTO;
import ui.sucursales.model.SucursalesTable;
import ui.usuarios.model.UsuarioDTO;

public class PacienteControler {
    private static PacienteControler instance;
    PacienteDTO paciente;

    private PacienteControler() {
        // no-op
    }

    public static PacienteControler getInstance() {
        if (instance == null) {
            instance = new PacienteControler();
        }
        return instance;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public static List<PacienteDTO> getPacientes(){
        return  PacientesTable.getAllPacientes();
    }

    public List<PeticionDTO> getPeticionesFinalizadas() {
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
            ArrayList<PracticaDTO> practicas = new ArrayList<>();
            for (Practica practica: peticion.getPracticas()) {
                practicas.add(new PracticaDTO(
                        practica.getCodigo(),
                        practica.getActivo(),
                        practica.getNombre(),
                        practica.getHoras(),
                        practica.getValoresCriticos(),
                        practica.getValoresReservados()
                ));
            }
            res.add(new PeticionDTO(paciente, peticion.getObraSocial(), peticion.getSucursal(), peticion.getFechaCarga(), peticion.getFechaEntrega(), practicas, resultados));
        }
        return res;
    }

    public void addPaciente(String nombre, int dni, String domicilio, String mail, int edad, Sexo sexo) {
        if (paciente != null) {
            paciente.setNombre(nombre);
            paciente.setDni(dni);
            paciente.setDomicilio(domicilio);
            paciente.setMail(mail);
            paciente.setEdad(edad);
            paciente.setSexo(sexo);
            PacientesTable.modifyPaciente(paciente);
        } else {
            PacientesTable.addPaciente(new PacienteDTO(nombre, dni, domicilio, mail, edad, sexo));
        }
    }

    public void deletePaciente() {
        PacientesTable.deletePaciente(paciente);
    }
}
