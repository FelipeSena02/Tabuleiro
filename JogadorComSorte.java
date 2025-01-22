package tabuleiro;

import java.util.Random;

public class JogadorComSorte extends Jogador {
    private Random random = new Random();

    public JogadorComSorte(String nome, String cor) {
        super(nome, cor);
    }

    @Override
    public int jogarDados() {
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        System.out.println(nome + " jogou os dados: " + dado1 + " e " + dado2); 
        int soma = dado1 + dado2;
        return (soma < 7) ? 7 : soma;
    }
}