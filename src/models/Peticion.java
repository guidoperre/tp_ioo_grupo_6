package models;

import java.util.Date;
import java.util.List;

public class Peticion extends Base {
    private Paciente paciente;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<Practica> practicas;
    private List<Resultado> resultados;

    public Peticion(Paciente paciente, String obraSocial, Date fechaCarga, Date fechaEntrega, List<Practica> practicas, List<Resultado> resultados) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.practicas = practicas;
        this.resultados = resultados;
    }

    @Override
    public void update() {}

    @Override
    public void delete() {}
}
