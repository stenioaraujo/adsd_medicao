package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class TesteFactory {

    private TesteFactory() {}

    public static void criarTesteDuzentasRequisicoesLeitura(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoLeitura(), 200);
        teste.run(buffer, callBackFinal);
    }

    public static void criarTesteQuinhentasRequisicoesLeitura(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoLeitura(), 500);
        teste.run(buffer, callBackFinal);
    }

    public static void criarTesteDuzentasRequisicoesEscrita(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoEscrita(), 200);
        teste.run(buffer, callBackFinal);
    }

    public static void criarTesteQuinhentasRequisicoesEscrita(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoLeitura(), 500);
        teste.run(buffer, callBackFinal);
    }
}
