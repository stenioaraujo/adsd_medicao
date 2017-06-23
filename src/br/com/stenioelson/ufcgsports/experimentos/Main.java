package br.com.stenioelson.ufcgsports.experimentos;

import br.com.stenioelson.ufcgsports.models.CallBack;
import br.com.stenioelson.ufcgsports.models.Contador;
import br.com.stenioelson.ufcgsports.models.Credential;
import br.com.stenioelson.ufcgsports.models.TesteFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by stenio on 11/06/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Credential.setHost(Credential.REMOTE_HOST);

        File resultFile = createResultFile();

        StringBuffer buffer = new StringBuffer();
        Contador contador = new Contador(4, new CallBack() {
            @Override
            public void result() {
                try {
                    addToFile(resultFile, buffer.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("All the tests were finished.");
            }
        });


        CallBack callBackMetodo = new CallBack() {
            @Override
            public void result() {
                System.out.println("One step done.");
                contador.decrementar();
            }
        };

        TesteFactory.criarTesteDuzentasRequisicoesLeitura(buffer, callBackMetodo);
        TesteFactory.criarTesteQuinhentasRequisicoesLeitura(buffer, callBackMetodo);
        TesteFactory.criarTesteDuzentasRequisicoesEscrita(buffer, callBackMetodo);
        TesteFactory.criarTesteQuinhentasRequisicoesEscrita(buffer, callBackMetodo);
    }

    /**
     * This will create the file within the root of the project
     * @return A reference to the file
     * @throws IOException If any problem happens
     */
    public static File createResultFile() throws IOException {
        File file = new File("result.txt");

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
