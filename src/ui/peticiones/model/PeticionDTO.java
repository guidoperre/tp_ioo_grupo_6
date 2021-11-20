package ui.peticiones.model;

import ui.pacientes.models.Paciente;
import ui.practicas.model.Practica;
import ui.resultados.model.Resultado;
import ui.sucursales.model.Sucursal;

import java.util.Date;
import java.util.List;

public class PeticionDTO {
    private Long id;
    private Paciente paciente;
    private String obraSocial;
    private Sucursal sucursal;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<Practica> practicas; // Change to DTO
    private List<Resultado> resultados; // Change to DTO
}
