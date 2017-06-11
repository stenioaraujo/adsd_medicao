package br.com.stenioelson.ufcgsports.models;

import java.util.Random;

/**
 * Created by stenio on 10/06/17.
 */
public class Requisicao {
    private String randomParametro;

    public void send(CallBack callBack) {
        callBack.setResultado("{success: true, randomParametro: " + randomParametro + "}");
        callBack.result();
    }

    public void setRandomParametro() {
        Random random = new Random();
        this.randomParametro = random.nextInt() + "" + random.nextInt() + "" + random.nextInt();
    }
}
