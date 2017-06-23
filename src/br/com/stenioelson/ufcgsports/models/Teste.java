package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class Teste {
    private Requisicao requisicao;
    private int numeroDeRequisicoes;
    private StringBuffer buffer;
    private CallBack callback;

    public Teste() {}

    public Teste(Requisicao requisicao, int numeroDeRequisicoes) {
        setRequisicao(requisicao);
        setNumeroDeRequisicoes(numeroDeRequisicoes);
        setCategoryRequest();
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    private void setCategoryRequest() {
        this.requisicao.setCategoryRequest(this.numeroDeRequisicoes + "");
    }

    public int getNumeroDeRequisicoes() {
        return numeroDeRequisicoes;
    }

    public void setNumeroDeRequisicoes(int numeroDeRequisicoes) {
        this.numeroDeRequisicoes = numeroDeRequisicoes;
    }

    public void run() {
        Contador contador = new Contador(numeroDeRequisicoes, getCallback());

        for (int i = 0; i < numeroDeRequisicoes; i++) {
            requisicao.setRandomParametro();
            requisicao.send(new CallBack() {
                @Override
                public void result() {
                    getBuffer().append(getResultado() + System.lineSeparator());
                    contador.decrementar();
                }
            });
        }
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }

    public CallBack getCallback() {
        return callback;
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }
}
