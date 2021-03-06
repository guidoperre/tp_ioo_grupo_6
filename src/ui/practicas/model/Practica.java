package ui.practicas.model;

import models.Regla;

import java.util.ArrayList;
import java.util.List;

public class Practica {

    private Long id;
    private int codigo;
    private Boolean activo;
    private String nombre;
    private int horas;
    private List<Regla> valoresCriticos;
    private List<Regla> valoresReservados;

    public Practica() {
        this.valoresCriticos = new ArrayList<>();
        this.valoresReservados = new ArrayList<>();
    }

    public Practica(int codigo, Boolean activo, String nombre, int horas, List<Regla> valoresCriticos, List<Regla> valoresReservados) {
        super();
        this.codigo = codigo;
        this.activo = activo;
        this.nombre = nombre;
        this.horas = horas;
        this.valoresCriticos = valoresCriticos;
        this.valoresReservados = valoresReservados;
        this.horas = horas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Regla> getValoresCriticos() {
        return valoresCriticos;
    }

    public void addValorCritico(Regla valorCritico) {
        this.valoresCriticos.add(valorCritico);
    }

    public void removeValorCritico(Regla valorCritico) {
        this.valoresCriticos.remove(valorCritico);
    }


    public void setValoresCriticos(List<Regla> valoresCriticos) {
        this.valoresCriticos = valoresCriticos;
    }

    public List<Regla> getValoresReservados() {
        return valoresReservados;
    }

    public void addValorReservado(Regla valorReservado) {
        this.valoresReservados.add(valorReservado);
    }

    public void removeValorReservado(Regla valorReservado) {
        this.valoresReservados.remove(valorReservado);
    }

    public void setValoresReservados(List<Regla> valoresReservados) {
        this.valoresReservados = valoresReservados;
    }

    public Boolean isValorCritico(float valor) {
        boolean critico = false;
        for (Regla regla: this.valoresCriticos) {
            if (regla.isValorCritico(valor))
                critico = true;
        }
        return critico;
    }

    public Boolean isValorReservado(float valor) {
        boolean reservado = false;
        for (Regla regla: this.valoresReservados) {
            if (regla.isValorCritico(valor))
                reservado = true;
        }
        return reservado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
