package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 * Um contador para executar um callback, quando este chega a zero
 */
public class Contador {
    private CallBack callBack;
    private int limite;

    public Contador(int limite, CallBack callBack) {
        this.callBack = callBack;
        this.limite = limite;
    }

    synchronized public void decrementar() {
        limite--;
        if (limite <= 0) {
            callBack.result();
        }
    }
}
