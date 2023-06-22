package teste;

import java.util.*;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.exception.XadrezException;

public class Main {
    
    public static void main(String[] args) {
        
        PartidaXadrez px = new PartidaXadrez();
        Scanner sc = new Scanner(System.in);
        List<PecaXadrez> capturadas = new ArrayList<>();
        
        while (!px.getCheckMate()) {
            
            try {
                UITabuleiro.limparTela();
                UITabuleiro.imprimirPartida(px, capturadas);
                
                System.out.println();
                
                System.out.print("Coluna e Linha:");
                PosicaoXadrez p = UITabuleiro.lerPosicao(sc);

                ///Imprimir movimentos possíveis para a peça escolhida
                boolean[][] movimentosPossiveis = px.movimentosPossiveis(p);
                UITabuleiro.limparTela();
                
                UITabuleiro.imprimirTabuleiro(px.getPecas(), movimentosPossiveis);
                
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez p1 = UITabuleiro.lerPosicao(sc);
                
                PecaXadrez pecaCapturada = px.executarMovimento(p, p1);
                
                if (pecaCapturada != null) {
                    capturadas.add(pecaCapturada);
                }
                
            } catch (XadrezException e) {
                System.out.println();
                System.out.print(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println();
                System.out.print(e.getMessage());
                
            }

//            
            sc.nextLine();
            sc.nextLine();
        }
        
        UITabuleiro.limparTela();
        UITabuleiro.imprimirPartida(px, capturadas);
        
    }
    
}
