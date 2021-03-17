package com.project;

import java.util.ArrayList;

public class ContratoFunc {

    private int meses;
    private int count;
    private ArrayList<ArrayList<Contrato>> contratosMatriz;

    public ContratoFunc(int meses, int count, ArrayList<ArrayList<Contrato>> contratosMatriz) {
        this.meses = meses;
        this.count = count;
        this.contratosMatriz = contratosMatriz;
    }

    public Contrato menorFornecedor(int index) {

        int fornecedor = 0;
        ArrayList<Contrato> contratos = contratosMatriz.get(index - 1);
        float valor = contratos.get(0).getValor();

        for (int i = 0; i < contratos.size(); i++) {
            if (valor > contratos.get(i).getValor()) {
                valor = contratos.get(i).getValor();
                fornecedor = i;
            }
        }
        return contratos.get(fornecedor);
    }

    public Contrato menorValor() {
        int fornecedor = 0;
        int contrato = 0;
        float valor = contratosMatriz.get(0).get(0).getValor();

        for (int i = 0; i < contratosMatriz.size(); i++) {
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {
                if (valor > contratosMatriz.get(i).get(j).getValor()) {
                    valor = contratosMatriz.get(i).get(j).getValor();
                    fornecedor = i;
                    contrato = j;
                }
            }
        }
        return this.contratosMatriz.get(fornecedor).get(contrato);
    }

    public Contrato menorMes(int mes) {
        int fornecedor = 0;
        int contrato = 0;
        float valor = 0;
        boolean primeiro = true;

        for (int i = 0; i < contratosMatriz.size(); i++) {
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {
                if (mes == contratosMatriz.get(i).get(j).getMesFinal() && contratosMatriz.get(i).get(j).getMesInicio() == 1) {
                    if(primeiro) {
                        valor = contratosMatriz.get(i).get(j).getValor();
                        primeiro = false;
                    } else {
                        if (valor > contratosMatriz.get(i).get(j).getValor()) {
                            valor = contratosMatriz.get(i).get(j).getValor();
                            fornecedor = i;
                            contrato = j;
                        }
                    }
                }
            }
        }

        return this.contratosMatriz.get(fornecedor).get(contrato);
    }

    public Contrato menorGeral(int mes) {
        int fornecedor = 0;
        int contrato = 0;
        int index = 0;
        int cont = 0;
        float valor = 0;
        float[][] valorMatriz = new float[contratosMatriz.size()][mes + 1];
        float[] valores = new float[72600];

        for (int i = 0; i < contratosMatriz.size(); i++) {
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {
                //QuickSort
                //Contrato[] array = contratosMatriz.get(i).toArray(new Contrato[contratosMatriz.get(i).size()]);
                //quickSort(array,0,contratosMatriz.size());

                if (mes >= contratosMatriz.get(i).get(j).getMesFinal()) {
                    index = contratosMatriz.get(i).get(j).getMesFinal() - contratosMatriz.get(i).get(j).getMesInicio();
                    valorMatriz[i][index] = valorMatriz[i][index] + contratosMatriz.get(i).get(j).getValor();
                    valores[cont] = contratosMatriz.get(i).get(j).getValor()/(index +1);
                    if(contratosMatriz.get(i).get(j).getMesFinal() - contratosMatriz.get(i).get(j).getMesInicio() == 76){
                        System.out.println(valores[cont] +" "+ contratosMatriz.get(i).get(j).getValor());
                    }
                    cont++;
                    if(contratosMatriz.get(i).get(j).getMesFinal() == mes) {

                        if(contratosMatriz.get(i).get(j).getMesFinal() == contratosMatriz.get(i).get(j).getMesInicio() && i == 0) {
                            valor = valorMatriz[i][index];
                        } else if (valor > valorMatriz[i][index]) {
                            valor = valorMatriz[i][index];
                            fornecedor = i;
                            contrato = j;
                            System.out.print(contratosMatriz.get(i).get(j).getMesInicio() + " ");
                            System.out.print(contratosMatriz.get(i).get(j).getMesFinal() + " ");
                            System.out.println(contratosMatriz.get(i).get(j).getValor());
                        }
                    }
                }

            }

        }
        quickSort(valores,0,72599);
        System.out.println(valores[0] +" "+ valores[1] +" "+ valores[2] + " " + valores[3]);
        return this.contratosMatriz.get(fornecedor).get(contrato);
    }

    private static float[] quickSort(float[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
        return vetor;
    }

    private static int separar(float[] vetor, int inicio, int fim) {
        float pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[f])
                f--;
            else {
                float troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }
}
