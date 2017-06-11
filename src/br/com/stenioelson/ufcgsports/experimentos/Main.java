package br.com.stenioelson.ufcgsports.experimentos;

import br.com.stenioelson.ufcgsports.models.CallBack;
import br.com.stenioelson.ufcgsports.models.TesteFactory;

/**
 * Created by stenio on 11/06/17.
 */
public class Main {

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        CallBack callBackFinal = new CallBack() {
            @Override
            public void result() {
                System.out.println(buffer);
            }
        };

        TesteFactory.criarTesteDuzentasRequisicoesLeitura(buffer, callBackFinal);
    }
}
