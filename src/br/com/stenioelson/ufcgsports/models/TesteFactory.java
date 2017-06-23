package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class TesteFactory {

    private TesteFactory() {}

    public static Teste criarTesteDuzentasRequisicoesLeitura(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoLeitura(), 200);
        teste.setBuffer(buffer);
        teste.setCallback(callBackFinal);

        return teste;
    }

    public static Teste criarTesteQuinhentasRequisicoesLeitura(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoLeitura(), 500);
        teste.setBuffer(buffer);
        teste.setCallback(callBackFinal);

        return teste;
    }

    public static Teste criarTesteDuzentasRequisicoesEscrita(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoEscrita(), 200);
        teste.setBuffer(buffer);
        teste.setCallback(callBackFinal);

        return teste;
    }

    public static Teste criarTesteQuinhentasRequisicoesEscrita(StringBuffer buffer, CallBack callBackFinal) {
        Teste teste = new Teste(new RequisicaoEscrita(), 500);
        teste.setBuffer(buffer);
        teste.setCallback(callBackFinal);

        return teste;
    }
}
