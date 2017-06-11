package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class RequisicaoLeitura extends Requisicao {
    @Override
    public void send(CallBack callBack) {
        RestClient.get("http://arrumai.stenioelson.com.br:9000", "/login", callBack);
    }
}
