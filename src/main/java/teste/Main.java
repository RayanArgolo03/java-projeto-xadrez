package teste;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Main {

    public static void main(String[] args) {
        
        PartidaXadrez px = new PartidaXadrez();
        Scanner sc = new Scanner(System.in);
        
        while (true) {            
            
            UITabuleiro.imprimirTabuleiro(px.getTabuleiro());
            System.out.println();
            
            System.out.print("Posição de origem:");
            PosicaoXadrez p = UITabuleiro.lerPosicao(sc);
            
            System.out.println();
            System.out.print("Posição de destino: ");
            PosicaoXadrez p1 = UITabuleiro.lerPosicao(sc);
            
            PecaXadrez pecaCapturada = px.executarMovimento(p, p1);
        }
        
        
    }
}
