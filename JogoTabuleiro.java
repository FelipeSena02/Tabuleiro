package tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoTabuleiro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Jogador> jogadores = new ArrayList<>();

        System.out.println("Selecione os jogadores:");
        System.out.println("1. Jogador Com Sorte");
        System.out.println("2. Jogador Azarado");
        System.out.println("3. Jogador Normal");

        while (jogadores.size() < 6) {
            System.out.print("Escolha o tipo de jogador (1-3) ou 0 para iniciar o jogo: ");
            int escolha = scanner.nextInt();
            if (escolha == 0 && jogadores.size() >= 2) {
                break;
            }
            if (escolha < 1 || escolha > 3) {
                System.out.println("Escolha inválida. Tente novamente.");
                continue;
            }

            System.out.print("Digite o nome do jogador: ");
            String nome = scanner.next();
            System.out.print("Digite a cor do jogador: ");
            String cor = scanner.next();

            switch (escolha) {
                case 1:
                    jogadores.add(new JogadorComSorte(nome, cor));
                    break;
                case 2:
                    jogadores.add(new JogadorAzarado(nome, cor));
                    break;
                case 3:
                    jogadores.add(new JogadorNormal(nome, cor));
                    break;
            }
        }


        System.out.print("Deseja ativar o modo Debug? (true/false): ");
        boolean modoDebug = scanner.nextBoolean();

        Tabuleiro tabuleiro = new Tabuleiro(jogadores, modoDebug);
        tabuleiro.jogar();

        scanner.close();
    }
}
