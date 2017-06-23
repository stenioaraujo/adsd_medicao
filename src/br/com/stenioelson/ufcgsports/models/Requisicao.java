package br.com.stenioelson.ufcgsports.models;

import java.util.Date;
import java.util.Random;

/**
 * Created by stenio on 10/06/17.
 */
public class Requisicao {
    private String randomParametro;
    private long startTime = new Date().getTime();
    private String categoryRequest = "";

    public Requisicao() {}

    public Requisicao(String categoryRequest) {
        setCategoryRequest(categoryRequest);
    }

    public void send(CallBack callBack) {
        callBack.setResultado("{success: true, randomParametro: " + randomParametro + "}");
        callBack.result();
    }

    public String getRandomParametro() {
        return this.randomParametro;
    }

    public String getCategoryRequest() {
        return this.categoryRequest;
    }

    public void setCategoryRequest(String categoryRequest) {
        this.categoryRequest = categoryRequest;
    }

    public void setRandomParametro() {
        Random random = new Random();
        this.randomParametro =
                Math.abs(random.nextInt()) + "_" +
                Math.abs(random.nextInt()) + "_" +
                Math.abs(random.nextInt());
    }
}
