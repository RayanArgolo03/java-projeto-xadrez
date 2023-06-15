package teste;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class UITabuleiro {

    public void imprimirTabuleiro(Tabuleiro tabuleiro) {

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

    public void imprimirPeca(Peca peca) {

        //Se tiver peça imprime, senão, imprime "-"
        if (peca != null) {
            System.out.print(peca);
        } else {
            System.out.print("-");
        }
        System.out.print(" ");

    }

}
