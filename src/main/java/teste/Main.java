package teste;

import java.util.InputMismatchException;
import java.util.Scanner;
import tabuleiro.exceptions.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Main {

    public static void main(String[] args) {

        PartidaXadrez px = new PartidaXadrez();
        Scanner sc = new Scanner(System.in);

        System.out.print("--- XADREZ GAME --- ");
        try {
            UITabuleiro.limparTela();
            UITabuleiro.imprimirTabuleiro(px.getPecas());

            System.out.println();

            System.out.print("Posição de origem:");
            PosicaoXadrez p = UITabuleiro.lerPosicao(sc);

            ///Imprimir movimentos possíveis para a peça escolhida
            boolean[][] movimentosPossiveis = px.movimentosPossiveis(p);
            UITabuleiro.limparTela();

            UITabuleiro.imprimirTabuleiro(px.getPecas(), movimentosPossiveis);

            System.out.println();
            System.out.print("Posição de destino: ");
            PosicaoXadrez p1 = UITabuleiro.lerPosicao(sc);

            PecaXadrez pecaCapturada = px.executarMovimento(p, p1);
        } catch (TabuleiroException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

    }

}
