package tabuleiro;

import java.util.Random;

public abstract class Jogador {
    protected String nome;
    protected String cor;
    protected int posicao;
    protected boolean podeJogar;
    protected int jogadas;

    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.posicao = 0;
        this.podeJogar = true;
        this.jogadas = 0;
    }

    public abstract int jogarDados();

    public void mover(int casas) {
        if (podeJogar) {
            posicao += casas;
            jogadas++;
        }
    }

    @Override
    public String toString() {
        return nome + " na casa " + posicao;
    }
}
