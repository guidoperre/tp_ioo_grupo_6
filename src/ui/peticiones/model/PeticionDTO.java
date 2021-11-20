package ui.peticiones.model;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;
import ui.sucursales.model.Sucursal;
import ui.sucursales.model.SucursalDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PeticionDTO {
    private Long id;
    private PacienteDTO paciente;
    private String obraSocial;
    private SucursalDTO sucursal;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<PracticaDTO> practicas; // Change to DTO
    private List<ResultadoDTO> resultados; // Change to DTO

    public PeticionDTO() {
        this.practicas = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    public PeticionDTO(PacienteDTO paciente, String obraSocial, SucursalDTO sucursal, Date fechaCarga, Date fechaEntrega, List<PracticaDTO> practicas, List<ResultadoDTO> resultados) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.practicas = practicas;
        this.resultados = resultados;
    }

    public PeticionDTO(PacienteDTO paciente, String obraSocial, SucursalDTO sucursal, Date fechaCarga, Date fechaEntrega) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.practicas = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaEntrega() {
        int max = 0;
        for (PracticaDTO practica: this.practicas) {
            if (practica.getHoras() > max)
                max = practica.getHoras();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(fechaEntrega);

        c.add(Calendar.HOUR, max);
        Date fecha = c.getTime();
        return fecha;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public List<PracticaDTO> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<PracticaDTO> practicas) {
        this.practicas = practicas;
    }

    public void addPractica(PracticaDTO practica) {
        this.practicas.add(practica);
    }

    public void removePractica(PracticaDTO practica) {
        this.practicas.remove(practica);
    }

    public List<ResultadoDTO> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoDTO> resultados) {
        this.resultados = resultados;
    }

    public void removeResultado(ResultadoDTO resultado) {
        this.resultados.remove(resultado);
    }

    public void addResultado(ResultadoDTO resultado) {
        this.resultados.add(resultado);
    }

    @Override
    public String toString() {
        return "Peticion " + id + " / " + sucursal.getDireccion() + " / " + obraSocial;
    }
}