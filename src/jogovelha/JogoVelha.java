package jogovelha;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JogoVelha {

    private static char jogador01Tipo, jogador02Tipo, simboloAtual = 'X';
    private static int jogadorAtual = 1, tipoMensagem = 0, linha, coluna;
    private static boolean jogo = true;
    private static String nomeJogador01, nomeJogador02;
    private static char[][] grade = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        iniciar();
        do {
            jogada();
            tabuleiro();
        } while (!fimJogo());
        finalizar();
    }

    private static void iniciar() {
        System.out.println("Jogo Da Velha");
        System.out.println("------------------------------");

        System.out.print("\nJogador( X ), (H)omem ou (M)aquina? ");
        jogador01Tipo = scanner.next().trim().toUpperCase().charAt(0);
        if (jogador01Tipo == 'H') {
            System.out.print("Nome do jogador(X): ");
            nomeJogador01 = scanner.next();
        } else {
            jogador01Tipo = 'M';
            nomeJogador01 = "Maquina X";
        }

        System.out.print("\nJogador( O ), (H)omem ou (M)aquina? ");
        jogador02Tipo = scanner.next().trim().toUpperCase().charAt(0);
        if (jogador02Tipo == 'H') {
            System.out.print("Nome do jogador(O): ");
            nomeJogador02 = scanner.next();
        } else {
            jogador02Tipo = 'M';
            nomeJogador02 = "Maquina O";
        }
        tabuleiro();
    }

    private static void tabuleiro() {
        limpaTela();
        System.out.println("\n    1   2   3  ");
        System.out.printf("1   %c | %c | %c  %n", grade[0][0], grade[0][1],
                grade[0][2]);
        System.out.println("   ---+---+---");
        System.out.printf("2   %c | %c | %c  %n", grade[1][0], grade[1][1],
                grade[1][2]);
        System.out.println("   ---+---+---");
        System.out.printf("3   %c | %c | %c %n", grade[2][0], grade[2][1],
                grade[2][2]);
    }

    private static void limpaTela() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    private static void jogada() {
        if (jogadorAtual == 1) {
            if (jogador01Tipo == 'H') {
                jogadaHomem();
            } else {
                jogadaMaquina();
            }
        } else {
            if (jogador02Tipo == 'H') {
                jogadaHomem();
            } else {
                jogadaMaquina();
            }
        }
    }

    private static String validaJogadorAtual() {
        switch (jogadorAtual) {
            case 1:
                simboloAtual = 'X';
                jogadorAtual++;
                return nomeJogador01;
            case 2:
                simboloAtual = 'O';
                jogadorAtual--;
                return nomeJogador02;
            default:
                return "Jogador Inexistente! ! !";
        }
    }

    private static boolean fimJogo() {
        verificaColuna();
        verificaLinha();
        verificaDiagonalP();
        verificaDiagonalS();
        verificaEmpate();
        return !jogo;
    }

    private static boolean jogadaHomem() {
        System.out.printf("%n%s, jogue(Linha e Coluna): ", validaJogadorAtual());
        linha = scanner.nextInt() - 1;
        coluna = scanner.nextInt() - 1;
        if (linha >= 0 && linha <= 2 && (coluna > -1 && coluna < 3) && grade[linha][coluna] == ' ') {
            grade[linha][coluna] = simboloAtual;
            return jogo;
        } else {
            tipoMensagem = 1;
            return jogo = false;
        }
    }

    private static boolean jogadaMaquina() {
        validaJogadorAtual();
        try {
            System.out.print("\nMaquina jogando...");
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {

        }
        while (true) {
            linha = random.nextInt(3);
            coluna = random.nextInt(3);
            if (grade[linha][coluna] == ' ') {
                grade[linha][coluna] = simboloAtual;
                break;
            }
        }
        return jogo;
    }

    private static boolean verificaColuna() {
        for (int col = 0; col < 3; col++) {
            int ponto = 0;
            for (int lin = 0; lin < 3; lin++) {
                if (grade[lin][col] == simboloAtual) {
                    ponto++;
                }
            }
            if (ponto == 3) {
                jogo = false;
                tipoMensagem = 1;

                if (jogadorAtual == 1) {
                    jogadorAtual++;
                } else {
                    jogadorAtual--;
                }
                break;
            }
        }
        return !jogo;
    }

    private static boolean verificaLinha() {
        for (int lin = 0; lin < 3; lin++) {
            int ponto = 0;
            for (int col = 0; col < 3; col++) {
                if (grade[lin][col] == simboloAtual) {
                    ponto++;
                }
            }
            if (ponto == 3) {
                jogo = false;
                tipoMensagem = 1;

                if (jogadorAtual == 1) {
                    jogadorAtual++;
                } else {
                    jogadorAtual--;
                }
                break;
            }
        }
        return !jogo;
    }

    //verifica diagonal principal da grade
    private static boolean verificaDiagonalP() {
        int ponto = 0;
        for (int diagonalP = 0; diagonalP < 3; diagonalP++) {
            if (grade[diagonalP][diagonalP] == simboloAtual) {
                ponto++;
            }
            if (ponto == 3) {
                jogo = false;
                tipoMensagem = 1;

                if (jogadorAtual == 1) {
                    jogadorAtual++;
                } else {
                    jogadorAtual--;
                }
                break;
            }
        }
        return !jogo;
    }

    //verifica diagonal secundaria da grade
    private static boolean verificaDiagonalS() {
        int l = 0, ponto = 0;
        for (int c = 2; c > -1; c--) {
            if (grade[l][c] == simboloAtual) {
                ponto++;
            }
            if (ponto == 3) {
                jogo = false;
                tipoMensagem = 1;
                if (jogadorAtual == 1) {
                    jogadorAtual++;
                } else {
                    jogadorAtual--;
                }
                break;
            }
            l++;
        }
        return !jogo;
    }

    private static boolean verificaEmpate() {
        int ponto = 0;
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                if (grade[lin][col] != ' ') {
                    ponto++;
                }
            }
            if (ponto == 9) {
                tipoMensagem = 2;
                jogo = false;
                break;
            }
        }
        return !jogo;
    }

    private static void finalizar() {
        if (tipoMensagem == 1) {
            System.out.printf("%n%n%s ganhou o jogo ! ! !%n", validaJogadorAtual());
        } else if (tipoMensagem == 2) {
            System.out.println("\n\nEmpate ! ! ! ");
            System.out.print("Empate.");
        }
    }
}
