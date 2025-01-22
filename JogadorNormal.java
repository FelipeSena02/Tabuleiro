package tabuleiro;

import java.util.Random;

public class JogadorNormal extends Jogador {
    private Random random = new Random();

    public JogadorNormal(String nome, String cor) {
        super(nome, cor);
    }

    @Override
    public int jogarDados() {
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        System.out.println(nome + " jogou os dados: " + dado1 + " e " + dado2);
        return dado1 + dado2;
    }
}
