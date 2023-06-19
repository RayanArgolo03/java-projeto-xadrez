package teste;

import java.util.InputMismatchException;
import java.util.Scanner;
import tabuleiro.Peca;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PosicaoXadrez;

public class UITabuleiro {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static PosicaoXadrez lerPosicao(Scanner sc) {

        try {
            String jogada = sc.next();
            
            char coluna = jogada.charAt(0);
            int linha = Integer.parseInt(jogada.substring(1));
            
            return new PosicaoXadrez(coluna, linha);
        
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler posição de xadrez: Valores válidos de a1 a h8!");
        }

    }

    public static void imprimirTabuleiro(Tabuleiro tabuleiro) {

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {

            System.out.print(8 - i);
            System.out.print(" ");

            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                imprimirPeca(tabuleiro.getPeca(i, j));
            }

            System.out.println();
        }

        System.out.print("  a b c d e f g h");
    }

    public static void imprimirPeca(Peca peca) {

        //Se tiver peça imprime, senão, imprime "-"
        if (peca == null) {
            System.out.print("-");
        } else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_BLUE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_RED + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");

    }

}
