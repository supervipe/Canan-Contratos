package com.project;

public class Contrato {

    private int mesInicio;
    private int mesFinal;
    private float valor;
    private int fornecedor;

    public Contrato(String[] linhas) {
        this.fornecedor = Integer.parseInt(linhas[0]);
        this.mesInicio = Integer.parseInt(linhas[1]);
        this.mesFinal = Integer.parseInt(linhas[2]);
        this.valor = Float.parseFloat(linhas[3].replace(',', '.'));
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public int getMesFinal() {
        return mesFinal;
    }

    public float getValor() {
        return valor;
    }

    public int getFornecedor() {
        return fornecedor;
    }
}
