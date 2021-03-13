package com.project;

import java.util.ArrayList;

public class ContratoFunc {

    private int meses;
    private int count;
    private ArrayList<ArrayList<Contrato>> contratosMatriz;

    public Contrato menorValorByFornecedor(int fornecedor) {
        ArrayList<Contrato> contratosFornecedores = contratosMatriz.get(fornecedor - 1);

        float menorValor = contratosFornecedores.get(0).getValor();
        int indexMenor = 0;

        for (int i = 0; i < contratosFornecedores.size(); i++) {
            if (menorValor > contratosFornecedores.get(i).getValor()) {
                menorValor = contratosFornecedores.get(i).getValor();
                indexMenor = i;
            }
        }

        return contratosFornecedores.get(indexMenor);
    }

    public Contrato menorValor() {
        int indexFornecedor = 0;
        int indexContrato = 0;
        float menorValor = contratosMatriz.get(0).get(0).getValor();

        for (int i = 0; i < contratosMatriz.size(); i++) {

            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {
                if (menorValor > contratosMatriz.get(i).get(j).getValor()) {
                    menorValor = contratosMatriz.get(i).get(j).getValor();
                    indexFornecedor = i;
                    indexContrato = j;
                }
            }
        }

        return this.contratosMatriz.get(indexFornecedor).get(indexContrato);
    }

    public Contrato menorValorByMes(int mes) {
        int indexFornecedor = 0;
        int indexContrato = 0;
        float menorValor = 0;
        boolean primeiro = true;

        for (int i = 0; i < contratosMatriz.size(); i++) {
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {
                if (mes == contratosMatriz.get(i).get(j).getMesFinal() && contratosMatriz.get(i).get(j).getMesInicio() == 1) {
                    if(primeiro) {
                        menorValor = contratosMatriz.get(i).get(j).getValor();
                        primeiro = false;
                    } else {
                        if (menorValor > contratosMatriz.get(i).get(j).getValor()) {
                            menorValor = contratosMatriz.get(i).get(j).getValor();
                            indexFornecedor = i;
                            indexContrato = j;
                        }
                    }
                }
            }
        }

        return this.contratosMatriz.get(indexFornecedor).get(indexContrato);
    }
}
