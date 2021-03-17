package com.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<ArrayList<Contrato>> contratosMatriz = new ArrayList<>();
        int meses = 0;
        int cont = 0;
        boolean primeiro = true;
        Scanner prompt = new Scanner(System.in);
        BufferedReader objReader = null;
        try {
            String linha;

            objReader = new BufferedReader(new FileReader("entrada_120_10.txt"));

            while ((linha = objReader.readLine()) != null) {
                if(primeiro){
                    meses = Integer.parseInt(linha.split(" ")[0]);
                    cont = Integer.parseInt(linha.split(" ")[1]);
                    for (int i = 0; i < cont; i++) {
                        contratosMatriz.add(new ArrayList<>());
                    }
                    primeiro = false;
                } else {
                    Contrato c = new Contrato(linha.split(" "));
                    contratosMatriz.get(c.getFornecedor() - 1).add(c);
                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        ContratoFunc f = new ContratoFunc( meses, cont,contratosMatriz);
        System.out.println(f.menorGeral(120));

        //handleContrato = new HandleContrato(fornecedores, meses, fornecedoresCount);

        while (true) {

            System.out.println(" Digite um comando: \n 1 Para contrato de menor valor de um dado fornecedor,\n 2 para contrato de menor valor do mercado,\n 3 para contrato de menor valor referente ao período completo do mês 1 ao mês n,\n 4 para contratos de energia devem ser contratados para os próximos n meses,\n 5 para sair.");

            int command = prompt.nextInt();

            if (command == 1) {

                System.out.println("Digite o numero do fornecedor:");
                int fornecedor = prompt.nextInt();

                System.out.println("****** C ******");
                long tempoInicial = System.currentTimeMillis();
                System.out.println(f.menorFornecedor(fornecedor));

                long tempoFinal = System.currentTimeMillis();
                System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) / 1000f);
                System.out.println("********************************************");

            } else if (command == 2) {

                System.out.println("****** E ******");
                long tempoInicial = System.currentTimeMillis();
                System.out.println(f.menorValor());

                long tempoFinal = System.currentTimeMillis();
                System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) / 1000f);
                System.out.println("********************************************");

            } else if (command == 3) {

                System.out.println("Digite o numero n de meses: ");
                int n = prompt.nextInt();

                System.out.println("****** G ******");
                long tempoInicial = System.currentTimeMillis();
                System.out.println(f.menorMes(n));

                long tempoFinal = System.currentTimeMillis();
                System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) / 1000f);
                System.out.println("********************************************");

            } else if (command == 4) {

                System.out.println("Digite o numero n de meses: ");
                int n = prompt.nextInt();

                System.out.println("****** I ******");
                long tempoInicial = System.currentTimeMillis();
                System.out.println(f.menorGeral(n));

                long tempoFinal = System.currentTimeMillis();
                System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) / 1000f);
                System.out.println("********************************************");

            } else if (command == 5) break;
        }
    }
}
