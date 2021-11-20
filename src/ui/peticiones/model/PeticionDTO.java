package ui.peticiones.model;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.practicas.model.Practica;
import ui.resultados.model.Resultado;
import ui.sucursales.model.Sucursal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PeticionDTO {
    private Long id;
    private PacienteDTO paciente;
    private String obraSocial;
    private Sucursal sucursal;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<Practica> practicas; // Change to DTO
    private List<Resultado> resultados; // Change to DTO

    public PeticionDTO() {
        this.practicas = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    public PeticionDTO(PacienteDTO paciente, String obraSocial, Sucursal sucursal, Date fechaCarga, Date fechaEntrega, List<Practica> practicas, List<Resultado> resultados) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.practicas = practicas;
        this.resultados = resultados;
    }

    public PeticionDTO(PacienteDTO paciente, String obraSocial, Sucursal sucursal, Date fechaCarga, Date fechaEntrega) {
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
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
        for (Practica practica: this.practicas) {
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

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }

    public void addPractica(Practica practica) {
        this.practicas.add(practica);
    }

    public void removePractica(Practica practica) {
        this.practicas.remove(practica);
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    public void removeResultado(Resultado resultado) {
        this.resultados.remove(resultado);
    }

    public void addResultado(Resultado resultado) {
        this.resultados.add(resultado);
    }

    @Override
    public String toString() {
        return "Peticion " + id + " / " + sucursal.getDireccion() + " / " + obraSocial;
    }
}
