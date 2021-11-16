package ui.peticiones.model;

import ui.practicas.model.Practica;
import models.Resultado;
import ui.pacientes.models.Paciente;
import ui.sucursales.model.Sucursal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Peticion {
    private Long id;
    private Paciente paciente;
    private String obraSocial;
    private Sucursal sucursal;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<Practica> practicas;
    private List<Resultado> resultados;

    public Peticion() {
        this.practicas = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    public Peticion(Paciente paciente, String obraSocial, Sucursal sucursal, Date fechaCarga, Date fechaEntrega, List<Practica> practicas, List<Resultado> resultados) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.practicas = practicas;
        this.resultados = resultados;

        sucursal.addPeticion(this);
    }

    public Peticion(Paciente paciente, String obraSocial, Sucursal sucursal, Date fechaCarga, Date fechaEntrega) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.practicas = new ArrayList<>();
        this.resultados = new ArrayList<>();

        sucursal.addPeticion(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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
        return fechaEntrega;
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

    public void addResultado(Resultado resultado) {
        this.resultados.add(resultado);
    }

    public Boolean isActiva() {
        boolean activa = false;
        for (Resultado resultado: this.resultados) {
            if (resultado.isActivo())
                activa = true;
        }
        return activa;
    }

    public Boolean isFinalizada() {
        boolean finalizada = false;
        for (Resultado resultado: this.resultados) {
            if (resultado.isFinalizada())
                finalizada = true;
        }
        return finalizada;
    }

    private Practica getPratica(int codigo) {
        for (Practica practica: this.practicas) {
            if (practica.getCodigo() == codigo)
                return practica;
        }
        return null;
    }

    public Boolean isCritica() {
        boolean critico = false;
        for (Resultado resultado: this.resultados) {
            Practica practica = getPratica(resultado.getCodigoPractica());
            if (practica != null && practica.isValorCritico(resultado.getValor()))
                critico = true;
        }
        return critico;
    }
}
