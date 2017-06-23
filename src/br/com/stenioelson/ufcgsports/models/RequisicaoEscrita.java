package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class RequisicaoEscrita extends Requisicao {
    @Override
    public void send(CallBack callBack) {
        String input = "{\"nome\": \"sauna\", "
                + "\"minPessoas\":\"2\"}";

        RestClient.post(Credential.getHost(), Credential.RECURSOS_PATH, input, getCategoryRequest(), callBack);
    }
}
