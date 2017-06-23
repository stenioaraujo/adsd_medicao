package br.com.stenioelson.ufcgsports.models;

/**
 * Created by stenio on 10/06/17.
 */
public class Teste {
    private Requisicao requisicao;
    private int numeroDeRequisicoes;

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

    public void run(StringBuffer buffer, CallBack callback) {
        Contador contador = new Contador(numeroDeRequisicoes, callback);

        for (int i = 0; i < numeroDeRequisicoes; i++) {
            requisicao.setRandomParametro();
            requisicao.send(new CallBack() {
                @Override
                public void result() {
                    buffer.append(getResultado() + System.lineSeparator());
                    contador.decrementar();
                }
            });
        }
    }
}
