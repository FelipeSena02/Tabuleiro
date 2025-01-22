package tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    private List<Jogador> jogadores;
    private static final int[] CASAS_ESPECIAIS = {10, 25, 38, 13, 5, 15, 30, 17, 27, 20, 35};
    private boolean modoDebug;
    private Random random = new Random();

    public Tabuleiro(List<Jogador> jogadores, boolean modoDebug) {
        this.jogadores = jogadores;
        this.modoDebug = modoDebug;
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        while (!verificarVencedor()) {
            for (Jogador jogador : jogadores) {
                if (jogador.podeJogar) {
                    System.out.println("Vez de " + jogador.nome + " (" + jogador.cor + ")");
                    int casas;
                    if (modoDebug) {
                        System.out.println("Insira o número da casa que " + jogador.nome + " deve ir: ");
                        casas = scanner.nextInt() - jogador.posicao;
                    } else {
                        casas = jogador.jogarDados();
                        System.out.println(jogador.nome + " jogou os dados e tirou " + casas);
                    }
                    jogador.mover(casas);
                    verificarCasasEspeciais(jogador);
                } else {
                    jogador.podeJogar = true;
                }
                mostrarPosicoes();
            }
        }
        scanner.close();
    }

    private void verificarCasasEspeciais(Jogador jogador) {
        for (int casa : CASAS_ESPECIAIS) {
            if (jogador.posicao == casa) {
                switch (casa) {
                    case 10: case 25: case 38:
                        System.out.println(jogador.nome + " caiu na casa " + casa + " e não joga a próxima rodada.");
                        jogador.podeJogar = false;
                        break;
                    case 13:
                        System.out.println(jogador.nome + " caiu na casa surpresa " + casa);
                        mudarTipoJogador(jogador);
                        break;
                    case 5: case 15: case 30:
                        if (!(jogador instanceof JogadorAzarado)) {
                            jogador.mover(3);
                            System.out.println(jogador.nome + " ganhou 3 casas extras por estar na casa da sorte.");
                        }
                        break;
                    case 17: case 27:
                        escolherJogadorParaVoltarAoInicio(jogador);
                        break;
                    case 20: case 35:
                        trocarComJogadorAtras(jogador);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void mudarTipoJogador(Jogador jogador) {
        int tipo = random.nextInt(3);
        switch (tipo) {
            case 0:
                System.out.println(jogador.nome + " virou Jogador Com Sorte!");
                jogadores.set(jogadores.indexOf(jogador), new JogadorComSorte(jogador.nome, jogador.cor));
                break;
            case 1:
                System.out.println(jogador.nome + " virou Jogador Azarado!");
                jogadores.set(jogadores.indexOf(jogador), new JogadorAzarado(jogador.nome, jogador.cor));
                break;
            case 2:
                System.out.println(jogador.nome + " virou Jogador Normal!");
                jogadores.set(jogadores.indexOf(jogador), new JogadorNormal(jogador.nome, jogador.cor));
                break;
        }
    }

    private void escolherJogadorParaVoltarAoInicio(Jogador jogadorAtual) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(jogadorAtual.nome + " caiu na casa especial! Escolha um jogador para voltar ao início:");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            if (!jogador.equals(jogadorAtual)) {
                System.out.println((i + 1) + ". " + jogador.nome);
            }
        }
        int escolha = scanner.nextInt() - 1;
        while (escolha < 0 || escolha >= jogadores.size() || jogadores.get(escolha).equals(jogadorAtual)) {
            System.out.println("Escolha inválida. Tente novamente:");
            escolha = scanner.nextInt() - 1;
        }
        Jogador escolhido = jogadores.get(escolha);
        escolhido.posicao = 0;
        System.out.println("O jogador " + escolhido.nome + " foi enviado de volta ao início!");
    }

    private void trocarComJogadorAtras(Jogador jogadorAtual) {
        Jogador jogadorMaisAtras = null;
        for (Jogador jogador : jogadores) {
            if (jogador != jogadorAtual && (jogadorMaisAtras == null || jogador.posicao < jogadorMaisAtras.posicao)) {
                jogadorMaisAtras = jogador;
            }
        }
        
        if (jogadorMaisAtras != null && jogadorAtual.posicao > jogadorMaisAtras.posicao) {
            int tempPosicao = jogadorAtual.posicao;
            jogadorAtual.posicao = jogadorMaisAtras.posicao;
            jogadorMaisAtras.posicao = tempPosicao;
            System.out.println(jogadorAtual.nome + " trocou de lugar com " + jogadorMaisAtras.nome);
        } else {
            System.out.println(jogadorAtual.nome + " já é o último, então não troca de lugar.");
        }
    }

    private void mostrarPosicoes() {
        for (Jogador jogador : jogadores) {
            System.out.println(jogador);
        }
    }

    private boolean verificarVencedor() {
        for (Jogador jogador : jogadores) {
            if (jogador.posicao >= 40) {
                System.out.println(jogador.nome + " venceu o jogo!");
                return true;
            }
        }
        return false;
    }
}
