package teste;

import java.util.InputMismatchException;
import java.util.*;
import java.util.stream.Collectors;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UITabuleiro {

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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

            char coluna = jogada.toLowerCase().charAt(0);
            int linha = Integer.parseInt(jogada.substring(1));

            return new PosicaoXadrez(coluna, linha);

        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler coordenadas de xadrez: Valores válidos de a1 a h8!");
        }

    }

    public static void imprimirPartida(PartidaXadrez px, List<PecaXadrez> pecasCapturadas) {
        
        imprimirTabuleiro(px.getPecas());
        System.out.println();
        imprimirPecasCapturadas(pecasCapturadas);
        System.out.println();
        System.out.println("----------");
        System.out.println("Turno: " + px.getTurno());
        
        if (!px.getCheckMate()) {
            System.out.println("Esperando jogador: " + px.getJogadorAtual());

            if (px.getCheck()) {
                System.out.println("Em cheque! Jogue para sair do cheque");
            }
        }
        
        else {
            System.out.println("CHECK MATE!");
            System.out.println("Vencedor!: " +px.getJogadorAtual());
        }
    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas) {

        System.out.println();
        for (int i = 0; i < pecas.length; i++) {

            System.out.print(8 - i);
            System.out.print(" ");

            for (int j = 0; j < pecas[i].length; j++) {
                imprimirPeca(pecas[i][j], false);
            }

            System.out.println();
        }

        System.out.print("  a b c d e f g h");

    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {

        System.out.println();
        for (int i = 0; i < pecas.length; i++) {

            System.out.print(8 - i);
            System.out.print(" ");

            for (int j = 0; j < pecas[i].length; j++) {
                imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }

            System.out.println();
        }

        System.out.print("  a b c d e f g h");

    }

    public static void imprimirPeca(PecaXadrez peca, boolean temFundo) {

        //Se tiver peça imprime, senão, imprime "-"
        if (temFundo) {
            System.out.print(ANSI_GREEN_BACKGROUND);
        }

        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (peca.getCor() == Cor.AZUL) {
                System.out.print(ANSI_BLUE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_RED + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");

    }
    
    private static void imprimirPecasCapturadas(List<PecaXadrez> capturadas) {

        List<PecaXadrez> pecasCapturadasAzuis = capturadas.stream()
                .filter(x -> x.getCor() == Cor.AZUL)
                .collect(Collectors.toList());

        List<PecaXadrez> pecasCapturadasVermelhas = capturadas.stream()
                .filter(x -> x.getCor() == Cor.VERMELHO)
                .collect(Collectors.toList());

        System.out.println("");
        System.out.println("Capturadas: ");
        System.out.print("Azul: ");
        System.out.print(ANSI_BLUE);
        System.out.println(Arrays.toString(pecasCapturadasAzuis.toArray()));
        System.out.print(ANSI_RESET);


        System.out.print("Vermelhas: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(pecasCapturadasVermelhas.toArray()));
        System.out.print(ANSI_RESET);

    }

}
