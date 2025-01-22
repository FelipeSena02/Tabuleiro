package tabuleiro;

import java.util.Random;

public class JogadorAzarado extends Jogador {
    private Random random = new Random();

    public JogadorAzarado(String nome, String cor) {
        super(nome, cor);
    }

    @Override
    public int jogarDados() {
        int dado1 = random.nextInt(3) + 1;
        int dado2 = random.nextInt(3) + 1;
        System.out.println(nome + " jogou os dados: " + dado1 + " e " + dado2);
        int soma = dado1 + dado2;
        return (soma > 6) ? 6 : soma;
    }
}
