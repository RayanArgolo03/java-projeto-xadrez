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

        System.out.println("        --- XADREZ GAME --- APERTE ENTER PARA COMEÇAR!");
        while (true) {

            try {
                UITabuleiro.clearScreen();
                UITabuleiro.imprimirTabuleiro(px.getPecas());

                System.out.println();

                System.out.print("Posição de origem:");
                PosicaoXadrez p = UITabuleiro.lerPosicao(sc);

                System.out.println();
                System.out.print("Posição de destino: ");
                PosicaoXadrez p1 = UITabuleiro.lerPosicao(sc);

                PecaXadrez pecaCapturada = px.executarMovimento(p, p1);
            } 
            
            
            catch (TabuleiroException | InputMismatchException e) {
                System.out.println(e.getMessage());
            } finally {
                sc.close();
            }
        }

    }

}
