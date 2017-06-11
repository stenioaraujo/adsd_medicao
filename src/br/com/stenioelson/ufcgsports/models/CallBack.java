package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public abstract class CallBack {
    private String resultado;

    public abstract void result();

    public String getResultado() {
        return this.resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
