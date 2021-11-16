package ui.resultados.model;

import models.EstadoResultado;

public class Resultado {

    public static Long indexCount = 0L;

    private Long id;
    private float valor;
    private EstadoResultado estado;
    private int codigoPractica;

    public Resultado() {
        this.id = indexCount++;
    }

    public Resultado(float valor, EstadoResultado estado, int codigoPractica) {
        super();
        this.id = indexCount++;
        this.valor = valor;
        this.estado = estado;
        this.codigoPractica = codigoPractica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public EstadoResultado getEstado() {
        return estado;
    }

    public void setEstado(EstadoResultado estado) {
        this.estado = estado;
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = codigoPractica;
    }

    public Boolean isActivo () {
        return this.estado != EstadoResultado.FINALIZADO;
    }

    public Boolean isFinalizada () {
        return this.estado == EstadoResultado.FINALIZADO;
    }

    public float getValor() {
        return valor;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }
}
