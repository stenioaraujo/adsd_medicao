package br.com.stenioelson.ufcgsports.experimentos;

import br.com.stenioelson.ufcgsports.models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stenio on 11/06/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Credential.setHost(Credential.REMOTE_HOST);
        File resultFile = createResultFile();
        StringBuffer buffer = new StringBuffer();
        List<Teste> testsToRun = new ArrayList<>();

        addToFile(resultFile, "success,path,method,categoryRequests,requestStart,requestEnd,dbTime" +
            System.lineSeparator());

        Contador contador = new Contador(4, new CallBack() {
            @Override
            public void result() {
                try {
                    addToFile(resultFile, buffer.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("All the tests are finished.");
            }
        });

        CallBack callBackMetodo = new CallBack() {
            @Override
            public void result() {
                contador.decrementar();
                if (!testsToRun.isEmpty()) {
                    Teste runTest = testsToRun.get(0);
                    testsToRun.remove(0);
                    System.out.println("Running: " + runTest.getRequisicao().getClass().toString() + " " +
                            runTest.getNumeroDeRequisicoes());
                    runTest.run();
                }
            }
        };

        testsToRun.add(TesteFactory.criarTesteDuzentasRequisicoesLeitura(buffer, callBackMetodo));
        testsToRun.add(TesteFactory.criarTesteDuzentasRequisicoesEscrita(buffer, callBackMetodo));
        testsToRun.add(TesteFactory.criarTesteQuinhentasRequisicoesLeitura(buffer, callBackMetodo));
        testsToRun.add(TesteFactory.criarTesteQuinhentasRequisicoesEscrita(buffer, callBackMetodo));

        Collections.shuffle(testsToRun);

        Teste firstTestToRun = testsToRun.get(0);
        testsToRun.remove(0);
        System.out.println("Running: " + firstTestToRun.getRequisicao().getClass().toString() + " " +
                firstTestToRun.getNumeroDeRequisicoes());
        firstTestToRun.run();
    }

    /**
     * This will create the file within the root of the project
     * @return A reference to the file
     * @throws IOException If any problem happens
     */
    public static File createResultFile() throws IOException {
        File file = new File("result.csv");

        if (file.createNewFile()){
            return file;
        }

        throw new IOException("File already exists or there was a problem. Please delete or move the existing one.");
    }

    public static void addToFile(File file, String text) throws IOException {
        Writer fw = new FileWriter(file, true);
        fw.append(text);
        fw.close();
    }
}
