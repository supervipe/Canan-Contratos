package com.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<ArrayList<Contrato>> contratosMatriz = new ArrayList<>();
        int meses = 0;
        int cont = 0;
        boolean primeiro = true;
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
        System.out.println(f.menorGeral(2));
    }
}
