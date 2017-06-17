package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class RequisicaoEscrita extends Requisicao {
    @Override
    public void send(CallBack callBack) {
        RestClient.post("http://localhost:9000", "/api/recursos/", callBack);
    }
}
