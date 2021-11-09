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
        super();
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.practicas = practicas;
        this.resultados = resultados;
        this.paciente.agregarPeticion(this);
    }

    public Boolean update() {
        return super.update();
    }

    public Boolean delete() {
        return super.delete();
    }
    
    public Boolean isActiva() {
        Boolean activa = false;
        for (Resultado resultado: this.resultados) {
            if (resultado.isActivo())
                activa = true;
        }
        return activa;
    }

    private Practica getPratica(int codigo) {
        for (Practica practica: this.practicas) {
            if (practica.getCodigo() == codigo)
                return practica;
        }
        return this.practicas.get(0);
    }

    public Boolean isCritica() {
        Boolean critico = false;
        for (Resultado resultado: this.resultados) {
            if (this.getPratica(resultado.getCodigoPractica()).isValorCritico(resultado.getValor()))
                critico = true;
        }
        return critico;
    }

}
