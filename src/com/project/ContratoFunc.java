package com.project;                                        //n = meses
import java.util.ArrayList;                                 //m = fornecesores

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

        for (int i = 0; i < contratos.size(); i++) {                                        //m + 1
            if (valor > contratos.get(i).getValor()) {                                      //m
                valor = contratos.get(i).getValor();                                        //O(m) - "A complexidade não é mais que Linear"
                fornecedor = i;                                                             //O melhor caso = 0 / O pior caso = m
            }
        }
        return contratos.get(fornecedor);
    }

    public Contrato menorValor() {
        int fornecedor = 0;
        int contrato = 0;
        float valor = contratosMatriz.get(0).get(0).getValor();

        for (int i = 0; i < contratosMatriz.size(); i++) {                                  //m + 1
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {                       //m(n+1)
                if (valor > contratosMatriz.get(i).get(j).getValor()) {                     //m*n
                    valor = contratosMatriz.get(i).get(j).getValor();                       //O melhor caso = 0 / O pior caso = m*n
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

        for (int i = 0; i < contratosMatriz.size(); i++) {                                                                        //m + 1
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {                                                             //m(n +1)
                if (mes == contratosMatriz.get(i).get(j).getMesFinal() && contratosMatriz.get(i).get(j).getMesInicio() == 1) {    //m*n
                    if(primeiro) {                                                                                                //O melhor caso = 1 / O pior caso = m*n
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
        //int cont = 1;
        float valor = 0;
        float[][] valorMatriz = new float[contratosMatriz.size()][mes + 1];

        for (int i = 0; i < contratosMatriz.size(); i++) {                                 //m + 1
            for (int j = 0; j < contratosMatriz.get(i).size(); j++) {                      //m(n +1)
                //QuickSort
                /*
                    Contrato[] array = contratosMatriz.get(i).toArray(new Contrato[contratosMatriz.get(i).size()]);
                    quickSort(array,0,contratosMatriz.size());


                    if (mes >= contratosMatriz.get(i).get(j).getMesFinal()) {
                    index = contratosMatriz.get(i).get(j).getMesFinal() - contratosMatriz.get(i).get(j).getMesInicio();
                    valorMatriz[i][index] = valorMatriz[i][index] + contratosMatriz.get(i).get(j).getValor();
                    valores[cont] = contratosMatriz.get(i).get(j).getValor()/(index +1);
                    cont++;
                 */
                if (mes >= contratosMatriz.get(i).get(j).getMesFinal()) {                  //m*n
                    index = contratosMatriz.get(i).get(j).getMesFinal() - contratosMatriz.get(i).get(j).getMesInicio();       //O melhor caso = 1 / O pior caso = m*n
                    valorMatriz[i][index] = valorMatriz[i][index] + contratosMatriz.get(i).get(j).getValor();

                    if(contratosMatriz.get(i).get(j).getMesFinal() == mes) {

                        if(contratosMatriz.get(i).get(j).getMesFinal() == contratosMatriz.get(i).get(j).getMesInicio() && i == 0) {
                            valor = valorMatriz[i][index];
                        } else if (valor > valorMatriz[i][index]) {
                            valor = valorMatriz[i][index];
                            fornecedor = i;
                            contrato = j;
                        }
                    }
                }

            }
        }
        return this.contratosMatriz.get(fornecedor).get(contrato);
    }

    //F(n^4 * m²) -> Pior caso
    //F(n² * m log(n² * m)) -> Melhor caso
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
